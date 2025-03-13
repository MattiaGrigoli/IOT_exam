package base;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.MessageDescriptor;

public class Producer {
    private final static Logger logger = LoggerFactory.getLogger(Producer.class);

    private static String BROKER_ADDRESS = "155.185.4.4";
    private static String BROKER_PORT = "7883";
    private static final int MESSAGE_COUNT = 1000;
    private static final String USERNAME = "298884@studenti.unimore.it";
    private static final String PASSWORD = "lgkuqugjkdwzrrjj";
    private static final String BASE_TOPIC = "/iot/user/" + USERNAME + "/";
    private static final String BROKER_URL = "tcp://" + BROKER_ADDRESS + ":" + BROKER_PORT;
    public static void main(String[] args) {}

    public static MessageDescriptor parseJsonMessage(byte[] payload) {

        try {

            Gson gson = new Gson();
            return (MessageDescriptor)gson.fromJson(new String(payload), MessageDescriptor.class);

        }catch(Exception e) {
            return null;
        }

    }
    public static String buildJsonMessage(double sensorValue) {
        return "";
    }

    public static void publishData(IMqttClient mqttClient, String topic, String msgString) throws MqttException {}
}
