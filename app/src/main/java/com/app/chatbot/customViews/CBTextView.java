package com.app.chatbot.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.app.chatbot.R;
import com.app.chatbot.utils.FontCache;

/**
 * Created by niranjan on 11/14/16.
 */

public class CBTextView extends AppCompatTextView
{

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    private boolean isLight;

    public CBTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public CBTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CBTextView(Context context) {
        this(context,null,0);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        if (attrs != null && !isInEditMode()) {
            String style = "0x0";
            try {
                style = attrs.getAttributeValue(ANDROID_SCHEMA, "textStyle");
            } catch (Exception e) {
                e.printStackTrace();
            }

            int textStyle;
            if (style != null && style.equals("0x1")) {
                textStyle = Typeface.BOLD;
            }else{
                textStyle = Typeface.NORMAL;
            }

            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.Rav_CustomFontTextView, defStyle, 0);
            isLight =  attributes.getBoolean(R.styleable.Rav_CustomFontTextView_fontIsLight, false);

            Typeface tf = selectTypeface(getContext(),textStyle);
            super.setTypeface(tf);

            // We no longer need our attributes TypedArray, give it back to cache
            attributes.recycle();

        }
    }

    private Typeface selectTypeface(Context context, int textStyle) {
        if(isLight) {
            return FontCache.get("fonts/Roboto-Light.ttf", context);
        } else {
            switch (textStyle) {
                case Typeface.BOLD: // bold
                    return FontCache.get("fonts/Roboto-Medium.ttf", context);
                case Typeface.NORMAL: // regular
                default:
                    return FontCache.get("fonts/Roboto-Regular.ttf", context);
            }
        }
    }

    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
    }

}
