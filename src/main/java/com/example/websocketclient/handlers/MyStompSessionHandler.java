package com.example.websocketclient.handlers;

import com.example.websocketclient.config.AppProperties;
import com.example.websocketclient.model.ChatMessage;
import com.example.websocketclient.model.OutputMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

/**
 * This class is an implementation for <code>StompSessionHandlerAdapter</code>.
 * Once a connection is established, we subscribe to
 * - /topic/messages
 * - /topic/userJoined
 * - /topic/userLeft
 * and attach the appropriate message handler to each subscription
 */
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private final Logger logger = LogManager.getLogger(MyStompSessionHandler.class);

    private final String userName;
    private final AppProperties properties;
    private StompSession session;

    public MyStompSessionHandler(String userName, AppProperties properties) {
        this.userName = userName;
        this.properties = properties;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        this.session = session;
        logger.info("New session established : " + session.getSessionId());

        subscribeToMessageTopic(session);
        subscribeToServerNotifications(session);

        sendJoinMessage();
    }

    private void subscribeToMessageTopic(StompSession session) {
        String topicMessages = properties.getTopicMessages();
        session.subscribe(topicMessages, new ChatMessageHandler(userName));
        logger.info("Subscribed to " + topicMessages);
    }

    private void subscribeToServerNotifications(StompSession session) {
        ServerMessageHandler serverMessageHandler = new ServerMessageHandler();

        String topicUserJoin = properties.getTopicUsersJoin();
        session.subscribe(topicUserJoin, serverMessageHandler);
        logger.info("Subscribed to " + topicUserJoin);

        String topicUserLeft = properties.getTopicUsersLeft();
        session.subscribe(topicUserLeft, serverMessageHandler);
        logger.info("Subscribed to " + topicUserLeft);
    }

    public void sendMessage(String message) {
        ChatMessage payload = new ChatMessage(userName, message);
        session.send(properties.getEndpointMessages(), payload);
    }

    public void sendJoinMessage() {
        session.send(properties.getEndpointUsersJoin(), userName);
    }

    public void sendLeaveMessage() {
        session.send(properties.getEndpointUsersLeft(), userName);
    }
}