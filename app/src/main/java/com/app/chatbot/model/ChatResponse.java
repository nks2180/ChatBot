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
public class ChatResponse  implements Serializable{

    @JsonField(name = "success")
    private int success;
    @JsonField(name = "errorMessage")
    private String errorMessage;
    @JsonField(name = "message")
    private Message message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}