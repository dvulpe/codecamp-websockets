package ro.codecamp.pubsub.config.atmosphere;

import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResourceImpl;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.websocket.WebSocket;
import org.atmosphere.websocket.WebSocketProcessor;
import org.atmosphere.websocket.WebSocketProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.codecamp.pubsub.config.SpringApplicationContext;
import ro.codecamp.pubsub.config.converters.ObjectMarshaller;
import ro.codecamp.pubsub.dto.commands.BaseCommand;
import ro.codecamp.pubsub.dto.commands.Command;
import ro.codecamp.pubsub.dto.commands.EmptyCommand;
import ro.codecamp.pubsub.services.ChatService;

public class DelegatingWebSocketProtocol implements WebSocketProtocol {

    public static final Logger LOG = LoggerFactory.getLogger(DelegatingWebSocketProtocol.class);

    @Override
    public void configure(AtmosphereServlet.AtmosphereConfig atmosphereConfig) {
    }

    @Override
    public AtmosphereRequest onMessage(WebSocket webSocket, String message) {
        if (webSocket.resource() == null) {
            return null;
        }
        AtmosphereResourceImpl resource = (AtmosphereResourceImpl) webSocket.resource();

        ChatService chatService = SpringApplicationContext.getBean(ChatService.class);
        ObjectMarshaller mapper = SpringApplicationContext.getBean(ObjectMarshaller.class);
        Command command = mapper.unmarshall(message, BaseCommand.class, new EmptyCommand());
        command.setResource(resource);

        chatService.execute(command);
        return null;
    }

    @Override
    public AtmosphereRequest onMessage(WebSocket webSocket, byte[] bytes, int offset, int length) {
        return null;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        webSocket.resource().suspend(-1);
        LOG.debug("opened web socket connection {}", webSocket);
    }

    @Override
    public void onClose(WebSocket webSocket) {
        LOG.debug("closing web socket connection {}", webSocket);
    }

    @Override
    public void onError(WebSocket webSocket, WebSocketProcessor.WebSocketException e) {
        LOG.error("error on websocket connection {}", e);
    }

    @Override
    public boolean inspectResponse() {
        return false;
    }

    @Override
    public String handleResponse(AtmosphereResponse atmosphereResponse, String message) {
        return message;
    }

    @Override
    public byte[] handleResponse(AtmosphereResponse atmosphereResponse, byte[] message, int length, int offset) {
        return message;
    }

}