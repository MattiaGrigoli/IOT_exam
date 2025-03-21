package entities;

import base.Consumer;
import com.google.gson.Gson;
import model.*;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

public class DataManager extends Consumer {
    private static final String TOPIC = "/+/sensor/#"; // the topic to receive from
    private static final String PUBLISH_TOPIC = "/datamanager";
    private static double sumheart = 0, sumacc = 0, sumstep = 0;
    private static int countheart = 0 , countstep = 0, countacc = 0;

    public static void main (String[] args) {
        logger.info("data manager started ...");
        try{
            String clientId = UUID.randomUUID().toString();

            //Represents a persistent data store, used to store outbound and inbound messages while they
            //are in flight, enabling delivery to the QoS specified. In that case use a memory persistence.
            //When the application stops all the temporary data will be deleted.
            MqttClientPersistence persistence = new MemoryPersistence();

            //the persistence is not passed to the constructor the default file persistence is used.
            //In case of a file-based storage the same MQTT client UUID should be used
            IMqttClient client = new MqttClient(
                    BROKER_URL,
                    clientId,
                    persistence);

            //Define MQTT Connection Options such as reconnection, persistent/clean session and connection timeout
            //Authentication option can be added -> See AuthProducer example
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(MQTT_USERNAME);
            options.setPassword(new String(MQTT_PASSWORD).toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            //Connect to the target broker
            client.connect(options);

            logger.info("Connected !");


            Dictionary<String, Statitics> stat =  new Hashtable<String, Statitics>();

            //Subscribe to the target topic #. In that case the consumer will receive (if authorized) all the message
            //passing through the broker
            logger.info(MQTT_BASIC_TOPIC + TOPIC);
            client.subscribe(MQTT_BASIC_TOPIC + TOPIC, (topic, msg) -> {

                Gson gson = new Gson();
                logger.info("Message received");
                //The topic variable contain the specific topic associated to the received message. Using MQTT wildcards
                //messaged from multiple and different topic can be received with the same subscription
                //The msg variable is a MqttMessage object containing all the information about the received message
                String json = new String(msg.getPayload());
                String ID = topic.split("/")[4];

                if(stat.get(ID) == null) {
                    stat.put(ID, new Statitics());
                }

                if(topic.contains("GPS"))
                {
                    GPSSensor gps = gson.fromJson(json,GPSSensor.class);
                    stat.get(ID).setCoordinates(gps.getCoordinates());
                }else if(topic.contains("heartMonitor"))
                {
                    HeartSensor heartMonitor = gson.fromJson(json,HeartSensor.class);
                    stat.get(ID).setHeartRate(heartMonitor.getHeartRate());
                    sumheart += heartMonitor.getHeartRate();
                    countheart++;
                    stat.get(ID).setMeansbeats(sumheart / countheart);
                }else if(topic.contains("accelerometer"))
                {
                    AccSensor  accSensor = gson.fromJson(json,AccSensor.class);
                    stat.get(ID).setAcceleration(accSensor.getAcceleration());
                    sumacc += accSensor.getAcceleration();
                    countacc++;
                    stat.get(ID).setMeansbeats(sumacc / countacc);
                }else if(topic.contains("movement"))
                {
                    MovementSensor movementSensor = gson.fromJson(json,MovementSensor.class);
                    stat.get(ID).setSteps(movementSensor.getSteps());
                    stat.get(ID).setTechnique(movementSensor.isTechnique());
                    sumstep += movementSensor.getSteps();
                    countstep++;
                    stat.get(ID).setMeansbeats(sumstep / countstep);
                } else{
                    logger.error("invalid subscribe topic, sensor not found {}", topic );
                }
                // TODO: populate battery stat
                String jsonSend = gson.toJson(stat.get(ID));
                client.publish(MQTT_BASIC_TOPIC+ "/" + ID + PUBLISH_TOPIC, new MqttMessage(jsonSend.getBytes()));
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
