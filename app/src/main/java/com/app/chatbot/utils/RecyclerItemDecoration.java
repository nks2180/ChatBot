package com.app.chatbot.utils;/**
 * Created by niranjan on 1/7/17.
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author niranjan
 * @since 1/7/17
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    int space;

    public RecyclerItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.right = 0;
        outRect.left = 0;
        outRect.top = space;
        outRect.bottom = space;
    }
}
