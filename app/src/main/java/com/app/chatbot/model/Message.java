package com.app.chatbot.model;/**
 * Created by niranjan on 1/7/17.
 */

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 * @author niranjan
 * @since 1/7/17
 */
@JsonObject
public class Message implements Serializable{
    @JsonField(name = "chatBotName")
    private String chatBotName;
    @JsonField(name = "chatBotID")
    private int chatBotID;
    @JsonField(name = "message")
    private String message;
    @JsonField(name = "emotion")
    private String emotion;

    public boolean isFromBot() {
        return fromBot;
    }

    public void setFromBot(boolean fromBot) {
        this.fromBot = fromBot;
    }

    private boolean fromBot;

    public String getChatBotName() {
        return chatBotName;
    }

    public void setChatBotName(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    public int getChatBotID() {
        return chatBotID;
    }

    public void setChatBotID(int chatBotID) {
        this.chatBotID = chatBotID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
}