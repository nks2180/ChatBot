package com.app.chatbot.presenter;/**
 * Created by niranjan on 1/7/17.
 */

import android.content.Context;
import android.util.Log;

import com.app.chatbot.executor.ParsingExecutor;
import com.app.chatbot.model.ChatResponse;
import com.app.chatbot.retrofit.ApiController;
import com.app.chatbot.retrofit.ApiDataReceiveCallback;
import com.app.chatbot.retrofit.NetworkConstants;
import com.app.chatbot.retrofit.RequestBuilder;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * @author niranjan
 * @since 1/7/17
 */

public class ChatPresenter  extends BasePresenterImpl<ChatView> implements ApiDataReceiveCallback {

    @Inject
    ApiController apiController;

    @Inject
    ParsingExecutor parsingExecutor;

    @Inject
    ChatPresenter(Context baseContext) {
        super(baseContext);
    }


    public void getBotResponse(String chatMessage){
        HashMap<String, String> map = new HashMap<>();
        map.put("message", chatMessage);
        RequestBuilder requestBuilder = new RequestBuilder(NetworkConstants.API_GET_BOT_MESSAGE);
        requestBuilder.setExtraParameters(map);
        apiController.hitApi(requestBuilder, this);
    }

    @Override
    public void onDataReceived(String response, int type) {
        Log.i("Response", response);
        switch (type) {
            case NetworkConstants.API_GET_BOT_MESSAGE:
                parseBotResponse(response);

        }
    }

    private void parseBotResponse(String response) {
        parsingExecutor.execute(ChatResponse.class, response, new ParsingExecutor.ParsingCallback<ChatResponse>() {
            @Override
            public void onParsingCompleted(ChatResponse chatResponse) {
                try {
                    if (null != chatResponse)
                        view.onBotResponseCame(chatResponse.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onError(int type) {

    }
}