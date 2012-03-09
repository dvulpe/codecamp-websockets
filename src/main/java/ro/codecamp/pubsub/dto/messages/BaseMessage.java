package ro.codecamp.pubsub.dto.messages;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChatMessage.class, name = "receive")})
public abstract class BaseMessage<T> {
    protected String party;
    public abstract T getPayload();

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
}
