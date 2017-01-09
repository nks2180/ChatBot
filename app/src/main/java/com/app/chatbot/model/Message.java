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

    int id;
    @JsonField(name = "chatBotName")
    private String chatBotName;
    @JsonField(name = "chatBotID")
    private int chatBotID;
    @JsonField(name = "message")
    private String message;
    @JsonField(name = "emotion")
    private String emotion;

    private boolean fromBot;
    private Integer messageDeliveryStatus;

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

    public Integer getMessageDeliveryStatus() {
        return messageDeliveryStatus;
    }

    public void setMessageDeliveryStatus(Integer messageDeliveryStatus) {
        this.messageDeliveryStatus = messageDeliveryStatus;
    }

    public Message(){

    }
   public Message(MessageDTO messageDTO){
       setId(messageDTO.getId());
       setMessage(messageDTO.getMessage());
       setFromBot(messageDTO.isFromBot());
       setChatBotID(messageDTO.getChatBotID());
       setChatBotName(messageDTO.getChatBotName());
       setEmotion(messageDTO.getEmotion());
       setMessageDeliveryStatus(messageDTO.getMessageDeliveryStatus());
   }
}