package com.app.chatbot.adapter;/**
 * Created by niranjan on 1/7/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.chatbot.R;
import com.app.chatbot.model.Message;
import com.app.chatbot.viewholder.ChatTextViewHolder;

import java.util.List;

/**
 * @author niranjan
 * @since 1/7/17
 */

public class ChatAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_TEXT_MESSAGE = 1000;
    List<Message> messages;
    Context mContext;

    public ChatAdapter(Context context, List<Message> listMessages){
        this.messages = listMessages;
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View chatView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_chat_text, parent, false);
                ChatTextViewHolder chatTextViewHolder = new ChatTextViewHolder(mContext, chatView);
                return chatTextViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ChatTextViewHolder textViewHolder = (ChatTextViewHolder) viewHolder;
        textViewHolder.loadDataIntoUI(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}