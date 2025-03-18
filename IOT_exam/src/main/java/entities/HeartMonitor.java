package entities;

import base.Producer;
import model.HeartSensor;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Arrays;
import java.util.UUID;

public class HeartMonitor extends Producer {
    private static final String IDuser = "1"; // just for testing purpose
    private static final String TOPIC = IDuser + "/sensor/heartMonitor";
    private static final String BATTERY_TOPIC = TOPIC + "/battery" ;

    public static void main(String[] args) {
        logger.info("Auth SimpleProducer started ...");

        try{

            //Generate a random MQTT client ID using the UUID class
            String publisherId = UUID.randomUUID().toString();

            //Represents a persistent data store, used to store outbound and inbound messages while they
            //are in flight, enabling delivery to the QoS specified. In that case use a memory persistence.
            //When the application stops all the temporary data will be deleted.
            MqttClientPersistence persistence = new MemoryPersistence();

            //The persistence is not passed to the constructor the default file persistence is used.
            //In case of a file-based storage the same MQTT client UUID should be used
            IMqttClient client = new MqttClient(BROKER_URL, publisherId, persistence);

            //Define MQTT Connection Options such as reconnection, persistent/clean session and connection timeout
            //Authentication option can be added -> See AuthProducer example
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(USERNAME);
            options.setPassword(new String(PASSWORD).toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            //Connect to the target broker
            client.connect(options);

            logger.info("Connected !");

            //Create an instance of the Sensor
            HeartSensor heartSensor = new HeartSensor();

            //Start to publish MESSAGE_COUNT messages
            for(int i = 0; i < MESSAGE_COUNT; i++) {

                //Send data as simple numeric value
                double sensorValue = heartSensor.getHeartRate();
                double battery = heartSensor.getBattery();
                String payloadStringGPS = Double.toString(sensorValue);
                String payloadStringBat = Double.toString(battery);

                // TODO: make publish data, look into doing two sensor values...

                //Internal Method to publish MQTT data using the created MQTT Client
                //The final topic is obtained merging the MQTT_BASIC_TOPIC and TOPIC in order to send the messages
                //to the correct topic root associated to the authenticated user
                //Eg. /iot/user/000001/sensor/temperature
                publishData(client, BASE_TOPIC + TOPIC, payloadStringGPS);
                publishData(client, BASE_TOPIC + BATTERY_TOPIC, payloadStringBat);

                //Sleep for 1 Second
                Thread.sleep(1000);
            }

            //Disconnect from the broker and close the connection
            client.disconnect();
            client.close();

            logger.info("Disconnected !");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void publishData(IMqttClient mqttClient, String topic, String msgString) throws MqttException {
        logger.debug("Publishing to Topic: {} Data: {}", topic, msgString);

        if (mqttClient.isConnected() && msgString != null && topic != null) {

            MqttMessage msg = new MqttMessage(msgString.getBytes());
            msg.setQos(0);
            msg.setRetained(topic.contains("battery"));
            mqttClient.publish(topic,msg);

            logger.debug("(If Authorized by Broker ACL) Data Correctly Published !");
        }
        else{
            logger.error("Error: Topic or Msg = Null or MQTT Client is not Connected !");
        }

    }
}
