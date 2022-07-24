package com.example.websocketclient.controller;

import com.example.websocketclient.config.AppProperties;
import com.example.websocketclient.handlers.MyStompSessionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This class is used for coordinating the connection
 * with the Chat server.
 */
public class ConnectionController {

    private final WebSocketStompClient stompClient;
    private final MyStompSessionHandler sessionHandler;
    private final String url;

    private final Logger logger = LogManager.getLogger(ConnectionController.class);
    private final int maxTries;

    public ConnectionController(WebSocketStompClient stompClient, String userName, AppProperties properties) {
        this.stompClient = stompClient;
        this.sessionHandler = new MyStompSessionHandler(userName, properties);
        this.maxTries = properties.getMaxRetries();
        this.url = properties.getUrl();
    }

    public MyStompSessionHandler connect() {
        int tries = 0;

        while (tries != maxTries) {
            try {
                stompClient.connect(url, sessionHandler).get(3, TimeUnit.SECONDS);
                break;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                tries++;
                logger.warn(String.format("Tried to connect to the server without success (%d/%d)", tries, maxTries));
            }
        }

        if (maxTries == tries) {
            logger.error("Too many retries");
            System.out.println("Too many tries to connect. Check your connection or try again later...");
            System.exit(-1);
        }

        return sessionHandler;
    }

}
