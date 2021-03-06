package com.app.chatbot.presenter;/**
 * Created by niranjan on 1/7/17.
 */

import com.app.chatbot.model.Message;

import java.util.List;

/**
 * @author niranjan
 * @since 1/7/17
 */

public interface ChatView extends BaseView{

    public void onBotResponseCame(Message message, int parentMesaageCode);

    public void onBotResponseFailed(String message, int messageId);

    public void onChatMessagesFetchedFromDB(List<Message> messages);
}