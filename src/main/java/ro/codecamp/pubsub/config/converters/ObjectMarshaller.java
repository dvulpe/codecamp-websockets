package ro.codecamp.pubsub.config.converters;

public interface ObjectMarshaller {
    <T> T unmarshall(String payload, Class<? extends T> targetClass, T defaultValue);

    String marshall(Object payload);
}
