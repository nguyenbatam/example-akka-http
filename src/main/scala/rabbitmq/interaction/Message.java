package rabbitmq.interaction;

import net.arnx.jsonic.JSON;

public class Message {
    public long authorActionId;
    public long timeStamp;
    public String content;
    public EInteract interaction;


    public String toString() {
        String result = "{}";
        try {
            result = JSON.encode(this);
        } catch (Exception e) {
        }

        return result;
    }
}

/*
 * Location:
 * /home/tamnb/Desktop/xkeam-service-db-0.5-jar-with-dependencies.jar!/com/xkeam
 * /event_bus/interaction/Message.class Java compiler version: 7 (51.0) JD-Core
 * Version: 0.7.1
 */