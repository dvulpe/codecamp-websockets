package ro.codecamp.pubsub.config.converters.impl;

import ro.codecamp.pubsub.config.converters.ObjectMarshaller;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;

public class JsonMarshaller implements ObjectMarshaller {

    private final ObjectMapper mapper;
    private static final Logger LOG = LoggerFactory.getLogger(JsonMarshaller.class);

    public JsonMarshaller(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public <T> T unmarshall(String payload, Class<? extends T> targetClass, T defaultValue) {
        T result = defaultValue;
        try {
            result = mapper.readValue(payload, targetClass);
        } catch (IOException e) {
            LOG.error("Exception converting JSON:", e);
        }
        return result;
    }

    @Override
    public String marshall(Object payload) {
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, payload);
        } catch (IOException e) {
            LOG.error("Exception converting to JSON:", e);
        }
        return sw.toString();
    }

}
