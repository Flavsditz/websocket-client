package com.example.websocketclient.controller;

import com.example.websocketclient.config.AppProperties;
import com.example.websocketclient.handlers.MyStompSessionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * This class is used for coordinating the connection
 * with the Chat server.
 */
public class ChatController {

    private final MyStompSessionHandler sessionHandler;
    private final AppProperties properties;

    private final Logger logger = LogManager.getLogger(ChatController.class);

    public ChatController(MyStompSessionHandler sessionHandler, AppProperties properties) {
        this.sessionHandler = sessionHandler;
        this.properties = properties;
    }

    public void chat() {
        mainLoop();
    }

    private void mainLoop() {
        Scanner scanner = new Scanner(System.in);
        boolean isExitCommand = false;

        String newMessage;
        while (!isExitCommand) {
            newMessage = scanner.nextLine();

            if (newMessage.equalsIgnoreCase(properties.getExitCommand())) {
                isExitCommand = true;
                logger.info("User decided to leave the application.");
                sessionHandler.sendLeaveMessage();
            } else {
                sessionHandler.sendMessage(newMessage);
            }
        }
    }
}
