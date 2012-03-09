package ro.codecamp.pubsub.config;

import org.apache.commons.lang.StringUtils;
import org.atmosphere.cpr.BroadcasterFactory;
import ro.codecamp.pubsub.config.converters.ObjectMarshaller;
import ro.codecamp.pubsub.config.converters.impl.JsonMarshaller;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.springframework.context.annotation.*;

@Configuration
@Import(WebAppConfiguration.class)
@ComponentScan(basePackages = "ro.codecamp.pubsub", excludeFilters = @ComponentScan.Filter(Configuration.class))
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ComponentConfiguration {

    @Bean
    public BroadcasterFactory broadcasterFactory() {
        return BroadcasterFactory.getDefault();
    }

    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
            @Override
            public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
                return StringUtils.uncapitalize(field.getName());
            }
        });
        return mapper;
    }

    @Bean
    public ObjectMarshaller objectConverter() {
        return new JsonMarshaller(objectMapper());
    }

}
