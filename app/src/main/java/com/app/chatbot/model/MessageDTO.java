package com.app.chatbot.model;/**
 * Created by niranjan on 1/7/17.
 */

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * @author niranjan
 * @since 1/7/17
 */

public class MessageDTO extends RealmObject {

    @PrimaryKey
    @Index
    public int id;

    private String chatBotName;
    private int chatBotID;
    private String message;
    private String emotion;
    private boolean fromBot;
    private int messageDeliveryStatus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isFromBot() {
        return fromBot;
    }

    public void setFromBot(boolean fromBot) {
        this.fromBot = fromBot;
    }

    public int getMessageDeliveryStatus() {
        return messageDeliveryStatus;
    }

    public void setMessageDeliveryStatus(int messageDeliveryStatus) {
        this.messageDeliveryStatus = messageDeliveryStatus;
    }

    public MessageDTO(){

    }
    public MessageDTO (Message message){
        setMessage(message.getMessage());
        setFromBot(message.isFromBot());
        setChatBotID(message.getChatBotID());
        setChatBotName(message.getChatBotName());
        setEmotion(message.getEmotion());
    }
}