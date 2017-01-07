package com.app.chatbot.executor;

import android.os.Handler;
import android.os.Looper;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  @author Niranjan
 *  @since 1/7/17
 */

public class ParsingExecutor<T> {

    private static final ExecutorService THREADPOOL = Executors.newCachedThreadPool();

    public ParsingExecutor() {

    }

    public void execute(final Class<T> type, final String response, final ParsingCallback<T> callback) {
        Runnable runnable = () -> performParsing(type, response, callback);
        runButNotOnMainThread(runnable, Looper.getMainLooper().getThread());
    }

    private void performParsing(Class<T> type, String response, final ParsingCallback<T> callback) {
        T jsonObject = null;
        try {
            jsonObject = LoganSquare.parse(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final T finalJsonObject = jsonObject;
        new Handler(Looper.getMainLooper()).post(() -> {
            if (finalJsonObject != null) callback.onParsingCompleted(finalJsonObject);
        });

    }

    private void runButNotOnMainThread(Runnable toRun, Thread notOn) {
        if (Thread.currentThread() == notOn) {
            THREADPOOL.submit(toRun);
        } else {
            toRun.run();
        }
    }

    public interface ParsingCallback<T> {
        void onParsingCompleted(T result);
    }
}
