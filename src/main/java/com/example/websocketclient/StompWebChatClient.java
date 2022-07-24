package com.example.websocketclient;

import com.example.websocketclient.config.AppProperties;
import com.example.websocketclient.controller.ChatController;
import com.example.websocketclient.controller.ConnectionController;
import com.example.websocketclient.controller.LoginController;
import com.example.websocketclient.handlers.MyStompSessionHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class StompWebChatClient {


    public static void main(String[] args) {

        String userName = new LoginController().login();

        // Create all the client classes necessary to connect through WebSockets with Stomp
        AppProperties properties = new AppProperties();
        ConnectionController connectionController = createConnectionController(userName, properties);

        // Connect to the server
        MyStompSessionHandler sessionHandler = connectionController.connect();

        // Create the chat controller and start chatting
        ChatController chatController = new ChatController(sessionHandler, properties);
        chatController.chat();
    }

    private static ConnectionController createConnectionController(String userName, AppProperties properties) {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        return new ConnectionController(stompClient, userName, properties);
    }
}