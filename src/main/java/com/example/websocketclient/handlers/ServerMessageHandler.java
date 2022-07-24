package com.example.websocketclient.handlers;

import com.example.websocketclient.model.OutputMessage;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

/**
 * This class is responsible to handle all STOMP messages
 * that are coming to the client from the SERVER.
 *
 * Messages such as joining or leaving of participants.
 */
public class ServerMessageHandler implements StompFrameHandler {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return OutputMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        OutputMessage msg = (OutputMessage) payload;
        System.out.println("-------------  ["+msg.getTime() + "] " + msg.getText() + "  -------------" );
    }
}
