package com.example.websocketclient.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;

public class OutputMessage {

    private final String from;

    private final String text;

    private final String time;

    @JsonCreator
    public OutputMessage(Map<String,Object> props)
    {
        this.from = (String) props.get("from");
        this.text = (String) props.get("text");
        this.time = (String) props.get("time");
    }

    public OutputMessage(final String from, final String text, final String time) {
        this.from = from;
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return "OutputMessage[from: " + this.from + ", time: " + this.time + ", text: " + text + "]";
    }
}