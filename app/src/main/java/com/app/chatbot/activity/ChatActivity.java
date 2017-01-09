package com.app.chatbot.activity;/**
 * Created by niranjan on 1/7/17.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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
import com.app.chatbot.model.MessageDeliveryStatus;
import com.app.chatbot.presenter.ChatPresenter;
import com.app.chatbot.presenter.ChatView;
import com.app.chatbot.utils.CBUtils;
import com.app.chatbot.utils.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    ConnectivityChangeReceiver connectivityChangeReceiver = new ConnectivityChangeReceiver();
    boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setUpRecyclerView();
        chatPresenter.getChatMessagesFromDB();
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
    protected void onResume() {
        super.onResume();
        if (null != connectivityChangeReceiver)
        registerReceiver(connectivityChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (connectivityChangeReceiver != null)
                unregisterReceiver(connectivityChangeReceiver);
        } catch (Exception e) {
        }
    }

    @Override
    public void onBotResponseCame(Message message, int parentMessageCode) {
        if (null == messages)
            messages = new ArrayList<>();
        message.setId(getRandomNumber());
        message.setFromBot(true);
        message.setMessageDeliveryStatus(MessageDeliveryStatus.RECEIVED.getValue());
        messages.add(message);
        chatPresenter.saveMessageIntoDB(message);
        changeMessageDeleiveryStatus(parentMessageCode, MessageDeliveryStatus.SENT.getValue());
        mAdapter.notifyDataSetChanged();
        recVwMessages.smoothScrollToPosition(messages.size() - 1);
    }

    @Override
    public void onBotResponseFailed(String parentMessage, int parentMessageCode) {
        changeMessageDeleiveryStatus(parentMessageCode, MessageDeliveryStatus.SEND_FAILED.getValue());
    }

    @Override
    public void onChatMessagesFetchedFromDB(List<Message> ChatMessages) {
        if (null != ChatMessages && ChatMessages.size() > 0)
            messages.addAll(ChatMessages);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.imgVw_send)
    public void sendChatMessage() {
        String enteredText = edtTxtChat.getText().toString().trim();
        if (!TextUtils.isEmpty(enteredText)) {
            if (CBUtils.checkIfInternetAvialable(mContext)) {
                Message chatMessage = addSelfChatMessage(enteredText, true);
                chatPresenter.getBotResponse(chatMessage);
            } else {
                addSelfChatMessage(enteredText, false);
            }
            edtTxtChat.setText("");
        } else
            CBUtils.showToast(mContext, getString(R.string.toast_enter_message));
    }

    private Message addSelfChatMessage(String chatMessage, boolean isInternetAvailabe) {
        Message message = new Message();
        message.setId(getRandomNumber());
        message.setFromBot(false);
        message.setMessage(chatMessage);

        if (isInternetAvailabe)
            message.setMessageDeliveryStatus(MessageDeliveryStatus.PENDING.getValue());
        else
            message.setMessageDeliveryStatus(MessageDeliveryStatus.SEND_FAILED.getValue());
        chatPresenter.saveMessageIntoDB(message);
        messages.add(message);
        mAdapter.notifyDataSetChanged();
        recVwMessages.smoothScrollToPosition(messages.size() - 1);
        return message;
    }



    private int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(999999) + 1;
    }


    public void changeMessageDeleiveryStatus(int messageID, int status){
        for (Message message : messages) {
            if (message.getId() == messageID){
                message.setMessageDeliveryStatus(status);
                chatPresenter.saveMessageIntoDB(message);
            }
        }
    }

    public class ConnectivityChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isFirstTime){
                isFirstTime = false;
                return;
            }
            if (CBUtils.isConnected(context)) {
                for (Message message : messages) {
                    chatPresenter.checkifMessageWasSuccessFullySent(message);
                }
            }
        }
    }

}