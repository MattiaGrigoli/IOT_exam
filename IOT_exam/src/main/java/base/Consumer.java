package base;

import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import model.MessageDescriptor;

public class Consumer {
    private final static Logger logger = LoggerFactory.getLogger(Consumer.class);

    private static String BROKER_ADDRESS = "155.185.4.4";
    private static String BROKER_PORT = "7883";

    public static void main(String[] args) {}

    public static MessageDescriptor parseJsonMessage(byte[] payload) {

        try {

            Gson gson = new Gson();
            return (MessageDescriptor)gson.fromJson(new String(payload), MessageDescriptor.class);

        }catch(Exception e) {
            return null;
        }

    }
}
