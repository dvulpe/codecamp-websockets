package ro.codecamp.pubsub.dto.messages;

public final class ChatMessage extends BaseMessage<String> {
    private String payload;

    private ChatMessage(String message) {
        this.payload = message;
    }

    public ChatMessage(String message, String party) {
        this.payload = message;
        this.party = party;
    }

    @Override
    public String getPayload() {
        return this.payload;
    }

    public static ChatMessage withMessageAndParty(String message, String party) {
        return new ChatMessage(message, party);
    }
}
