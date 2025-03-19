package base;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {
    protected final static Logger logger = LoggerFactory.getLogger(Producer.class);

    protected static String BROKER_ADDRESS = "155.185.4.4";
    protected static String BROKER_PORT = "7883";
    protected static final int MESSAGE_COUNT = 1000;
    protected static final String USERNAME = "298884@studenti.unimore.it";
    protected static final String PASSWORD = "lgkuqugjkdwzrrjj";
    protected static final String BASE_TOPIC = "/iot/user/" + USERNAME + "/";
    protected static final String BROKER_URL = "tcp://" + BROKER_ADDRESS + ":" + BROKER_PORT;

    public static void main(String[] args) {}

    public static String buildJsonMessage(double sensorValue) {
        return "";
    }

    public static void publishData(IMqttClient mqttClient, String topic, String msgString) throws MqttException {}
}
