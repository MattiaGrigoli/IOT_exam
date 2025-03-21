package entities;

import base.Consumer;
import com.google.gson.Gson;
import model.BatterySensor;
import model.Statitics;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Enumeration;
import java.util.UUID;

public class Wearable extends Consumer {
    private final String ID = "1";
    private static final String TOPIC = "/datamanager";
    private BatterySensor battery;
    // TODO: add battery (change everything in non static)

    public static void main(String[] args) {
        new  Wearable().run();
    }

    private void run()
    {
        logger.info("Wearable starting...");
        battery = new BatterySensor();
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
            logger.info(MQTT_BASIC_TOPIC + "/" + ID + TOPIC);
            client.subscribe(MQTT_BASIC_TOPIC + "/" + ID + TOPIC, (topic, msg) -> {
                Gson gson = new Gson();
                battery.generateCharge();
                double charge = battery.getCharge();
                logger.info("message received ");
                logger.info(msg.toString());
                Statitics stat =  gson.fromJson(msg.toString(), Statitics.class); // gets stuck here
                logger.info(stat.toString());
                if(stat.getHeartRate() > 150 && stat.getSteps() > 100){
                    logger.warn("BEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEP");
                    logger.warn("heart rate too high, take a break!");
                }
                // iteration for the batteries
                Enumeration<String> keys = stat.getBatteries().keys();
                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    if(stat.getBatteries().get(key) <= 20)
                    {
                        logger.warn("BEEP");
                        logger.warn("the sensor {} has low battery!", key);
                    }
                }
                //for the wearable
                if(charge <= 20)
                {
                    logger.warn("BEEP");
                    logger.warn("the wearable has low battery");
                }

                if(!stat.isTechnique())
                {
                    logger.warn("BEEP");
                    logger.warn("your technique is wrong!");
                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
