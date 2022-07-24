package com.example.websocketclient.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppProperties {

    private final String url;
    private final int maxRetries;
    private final String topicMessages;
    private final String topicUsersJoin;
    private final String topicUsersLeft;
    private final String endpointMessages;
    private final String endpointUsersJoin;
    private final String endpointUsersLeft;
    private final String exitCommand;

    public AppProperties() {
        Properties prop = new Properties();

        try (InputStream input = AppProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.url = prop.getProperty("app.server.url");
        this.maxRetries = Integer.parseInt(prop.getProperty("app.server.maxRetries"));
        this.topicMessages = prop.getProperty("app.topic.messages");
        this.topicUsersJoin = prop.getProperty("app.topic.users.join");
        this.topicUsersLeft = prop.getProperty("app.topic.users.left");
        this.endpointMessages = prop.getProperty("app.endpoint.messages");
        this.endpointUsersJoin = prop.getProperty("app.endpoint.users.join");
        this.endpointUsersLeft = prop.getProperty("app.endpoint.users.left");
        this.exitCommand = prop.getProperty("app.chat.exitCommand");

    }

    public String getUrl() {
        return url;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public String getTopicMessages() {
        return topicMessages;
    }

    public String getTopicUsersJoin() {
        return topicUsersJoin;
    }

    public String getTopicUsersLeft() {
        return topicUsersLeft;
    }

    public String getEndpointMessages() {
        return endpointMessages;
    }

    public String getEndpointUsersJoin() {
        return endpointUsersJoin;
    }

    public String getEndpointUsersLeft() {
        return endpointUsersLeft;
    }

    public String getExitCommand() {
        return exitCommand;
    }
}