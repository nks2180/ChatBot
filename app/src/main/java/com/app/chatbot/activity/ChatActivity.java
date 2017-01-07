package com.app.chatbot.activity;/**
 * Created by niranjan on 1/7/17.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.chatbot.R;
import com.app.chatbot.adapter.ChatAdapter;
import com.app.chatbot.component.ApplicationComponent;
import com.app.chatbot.model.Message;
import com.app.chatbot.presenter.ChatPresenter;
import com.app.chatbot.presenter.ChatView;
import com.app.chatbot.utils.CBUtils;
import com.app.chatbot.utils.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author niranjan
 * @since 1/7/17
 */

public class ChatActivity extends BaseViewPresenterActivity<ChatPresenter> implements ChatView {

    @Inject
    ChatPresenter chatPresenter;

    @BindView(R.id.recVw_messages)
    RecyclerView recVwMessages;
    @BindView(R.id.imgVw_send)
    ImageView imgVwSend;
    @BindView(R.id.edtTxt_chat)
    EditText edtTxtChat;

    Context mContext;

    List<Message> messages = new ArrayList<>();
    ChatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_five);
        recVwMessages.addItemDecoration(new RecyclerItemDecoration(spacingInPixels));
        recVwMessages.setLayoutManager(layoutManager);
        mAdapter = new ChatAdapter(mContext, messages);
        recVwMessages.setAdapter(mAdapter);
    }

    @Override
    protected int getMainLayout() {
        return R.layout.activity_chat;
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter(chatPresenter, this);
    }

    @Override
    public void onBotResponseCame(Message message) {
        if ( null ==  messages)
            messages = new ArrayList<>();
        message.setFromBot(true);
        messages.add(message);
        mAdapter.notifyDataSetChanged();
        recVwMessages.smoothScrollToPosition(messages.size()-1);
    }

    @OnClick(R.id.imgVw_send)
    public void sendChatMessage() {
        String chatMessage = edtTxtChat.getText().toString().trim();
        if (!TextUtils.isEmpty(chatMessage)) {
            chatPresenter.getBotResponse(chatMessage);
            addSelftChatMessage(chatMessage);
            edtTxtChat.setText("");
        }
        else
            CBUtils.showToast(mContext, getString(R.string.toast_enter_message));
    }

    private void addSelftChatMessage(String chatMessage) {
        Message message = new Message();
        message.setFromBot(false);
        message.setMessage(chatMessage);
        messages.add(message);
        mAdapter.notifyDataSetChanged();
        recVwMessages.smoothScrollToPosition(messages.size()-1);
    }

}