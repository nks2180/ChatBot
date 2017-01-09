package com.app.chatbot.model;

/**
 * Created by niranjan on 1/7/17.
 */

public enum MessageDeliveryStatus {
    PENDING(0), SENT(1), RECEIVED(2), SEND_FAILED(3);

    private final int value;

    private MessageDeliveryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
