package ro.codecamp.pubsub.config.atmosphere;

import org.atmosphere.cpr.BroadcastFilter;
import ro.codecamp.pubsub.config.SpringApplicationContext;
import ro.codecamp.pubsub.config.converters.ObjectMarshaller;
import ro.codecamp.pubsub.config.converters.impl.JsonMarshaller;

public class JSONOutputFilter implements BroadcastFilter {

    @Override
    public BroadcastAction filter(Object originalMessage, Object message) {
        if (message instanceof String) {
            return new BroadcastAction(message);
        }
        ObjectMarshaller marshaller = SpringApplicationContext.getBean(JsonMarshaller.class);
        return new BroadcastAction(marshaller.marshall(originalMessage));
    }

}
