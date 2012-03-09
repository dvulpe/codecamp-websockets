package ro.codecamp.pubsub.dto.commands;

import org.atmosphere.cpr.Broadcaster;
import ro.codecamp.pubsub.dto.messages.ChatMessage;

public class BroadcastCommand extends BaseCommand {
    private String payload;

    @Override
    public void execute(Broadcaster broadcaster) {
        resource.getBroadcaster().broadcast(ChatMessage.withMessageAndParty(payload, party));
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
