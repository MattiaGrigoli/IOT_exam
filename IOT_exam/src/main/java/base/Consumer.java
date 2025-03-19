package base;

import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Consumer {
    protected final static Logger logger = LoggerFactory.getLogger(Consumer.class);

    protected static String BROKER_ADDRESS = "155.185.4.4";
    protected static String BROKER_PORT = "7883";
    protected static final String MQTT_USERNAME = "298884@studenti.unimore.it";
    protected static final String MQTT_PASSWORD = "lgkuqugjkdwzrrjj";
    protected static final String MQTT_BASIC_TOPIC = "/iot/user/" + MQTT_USERNAME + "/";
    protected static final String BROKER_URL = "tcp://" + BROKER_ADDRESS + ":" + BROKER_PORT;

    public static void main(String[] args) {}


}
