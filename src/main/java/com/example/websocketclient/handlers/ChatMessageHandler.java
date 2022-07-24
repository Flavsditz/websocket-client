package com.example.websocketclient.handlers;

import com.example.websocketclient.model.OutputMessage;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

/**
 * This class is responsible to handle all STOMP messages
 * that are coming to the Chat. I.e. all messages incoming
 * from other participants over the main topic
 */
public class ChatMessageHandler implements StompFrameHandler {

    private final String userName;

    public ChatMessageHandler(String userName) {
        this.userName = userName;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return OutputMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        OutputMessage msg = (OutputMessage) payload;

        if (!userName.equals(msg.getFrom())) {
            System.out.println("[" + msg.getTime() + "] " + msg.getFrom() + ": " + msg.getText());
        }
    }
}
