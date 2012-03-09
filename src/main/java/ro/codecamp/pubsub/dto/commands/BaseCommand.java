package ro.codecamp.pubsub.dto.commands;

import org.atmosphere.cpr.AtmosphereResource;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UnsubscribeCommand.class, name = "unsubscribe"),
        @JsonSubTypes.Type(value = JoinChannelCommand.class, name = "subscribe"),
        @JsonSubTypes.Type(value = BroadcastCommand.class, name = "broadcastMessage")})
public abstract class BaseCommand implements Command {
    protected AtmosphereResource resource;
    protected String channel;
    protected String party;

    @Override
    public void setResource(AtmosphereResource resource) {
        this.resource = resource;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
}
