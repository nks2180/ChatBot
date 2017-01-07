package com.app.chatbot.utils;

import android.util.Log;

import com.app.chatbot.BuildConfig;

/**
 *  @author Niranjan
 *  @since 1/7/17
 */

public class CBLogger {
    public static String LOGGER_TAG = "ToDoAppLogger";

    private CBLogger() {
    }

    public static void e(String s) {
        if (BuildConfig.DEBUG)
            Log.e(LOGGER_TAG, s);
    }

    public static void d(String s) {
        if (BuildConfig.DEBUG)
            Log.d(LOGGER_TAG, s);
    }

    public static void w(String s) {
        if (BuildConfig.DEBUG)
            Log.w(LOGGER_TAG, s);
    }

    public static void i(String s) {
        if (BuildConfig.DEBUG)
            Log.i(LOGGER_TAG, s);
    }

    public static void v(String s) {
        if (BuildConfig.DEBUG)
            Log.v(LOGGER_TAG, s);
    }
    public static void e(String LOGGER_TAG,String s) {
        if (BuildConfig.DEBUG)
            Log.e(LOGGER_TAG, s);
    }
    public static void d(String LOGGER_TAG,String s) {
        if (BuildConfig.DEBUG)
            Log.d(LOGGER_TAG, s);
    }

    public static void w(String LOGGER_TAG,String s) {
        if (BuildConfig.DEBUG)
            Log.w(LOGGER_TAG, s);
    }

    public static void i(String LOGGER_TAG,String s) {
        if (BuildConfig.DEBUG)
            Log.i(LOGGER_TAG, s);
    }

    public static void v(String LOGGER_TAG,String s) {
        if (BuildConfig.DEBUG)
            Log.v(LOGGER_TAG, s);
    }
}
