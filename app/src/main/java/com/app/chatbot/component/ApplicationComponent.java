package com.app.chatbot.component;

import com.app.chatbot.CBApplication;
import com.app.chatbot.activity.ChatActivity;
import com.app.chatbot.module.ApplicationModule;
import com.app.chatbot.module.DatabaseModule;
import com.app.chatbot.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by niranjan on 12/29/16.
 */
@Singleton
@Component(modules={ApplicationModule.class, NetModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    void inject(CBApplication materialApplication);


    void inject(ChatActivity chatActivity);
}
