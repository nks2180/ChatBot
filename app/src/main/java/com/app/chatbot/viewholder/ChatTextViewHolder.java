package com.app.chatbot.viewholder;/**
 * Created by niranjan on 1/7/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.chatbot.R;
import com.app.chatbot.customViews.CBTextView;
import com.app.chatbot.model.Message;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author niranjan
 * @since 1/7/17
 */

public class ChatTextViewHolder extends RecyclerView.ViewHolder {

    private final Context mContext;

    @BindView(R.id.txtVw_chatMessage)
    CBTextView txtVwChatMessage;
    @BindView(R.id.txtVw_dateTime)
    CBTextView txtVwDateTime;
    @BindView(R.id.relLyt_chat)
    RelativeLayout relLytChat;


    private RelativeLayout.LayoutParams chatLayoutParams;

    public ChatTextViewHolder(Context context, View convertView) {
        super(convertView);
        this.mContext = context;
        ButterKnife.bind(this, convertView);
    }

    public void loadDataIntoUI(Message message) {
        initLayoutParams();
        txtVwDateTime.setVisibility(View.GONE);
        txtVwChatMessage.setText(message.getMessage());

        if (message.isFromBot()) {
            showIncomingMessage();
        } else {
            showOutGoingMessage();
        }
    }

    private void initLayoutParams() {
        chatLayoutParams = new RelativeLayout.LayoutParams(relLytChat.getLayoutParams());
        chatLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
    }


    private void showIncomingMessage() {
        chatLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        int spacingInPixels = mContext.getResources().getDimensionPixelSize(R.dimen.margin_five);
        chatLayoutParams.setMargins(spacingInPixels, 0, 0, 0);
        relLytChat.setLayoutParams(chatLayoutParams);
        relLytChat.setBackgroundResource((R.drawable.msg_in));
    }

    private void showOutGoingMessage() {
        chatLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        int spacingInPixels = mContext.getResources().getDimensionPixelSize(R.dimen.margin_five);
        chatLayoutParams.setMargins(0, 0, spacingInPixels, 0);
        relLytChat.setLayoutParams(chatLayoutParams);
        relLytChat.setBackgroundResource((R.drawable.msg_out));
    }
}