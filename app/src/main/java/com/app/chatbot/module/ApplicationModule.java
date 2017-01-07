package com.app.chatbot.module;

import android.content.Context;

import com.app.chatbot.CBApplication;
import com.app.chatbot.executor.ParsingExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *  @author Niranjan
 *  @since 1/7/17
 */
@Module
public class ApplicationModule {

    private final CBApplication lybrate;

    public ApplicationModule(CBApplication lybrate) {
        this.lybrate = lybrate;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return lybrate;
    }

    @Provides
    @Singleton
    ParsingExecutor providesParsingExecutor() {
        return new ParsingExecutor();
    }

}
