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

    private final CBApplication applicaton;

    public ApplicationModule(CBApplication applicaton) {
        this.applicaton = applicaton;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return applicaton;
    }

    @Provides
    @Singleton
    ParsingExecutor providesParsingExecutor() {
        return new ParsingExecutor();
    }

}
