package com.example.uibestpractice;

public class Message {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    private String content;

    private int type;

    public Message(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
