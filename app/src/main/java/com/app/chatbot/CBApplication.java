package com.app.chatbot;

import android.app.Application;

import com.app.chatbot.component.ApplicationComponent;
import com.app.chatbot.component.DaggerApplicationComponent;
import com.app.chatbot.module.ApplicationModule;
import com.app.chatbot.module.DatabaseModule;
import com.app.chatbot.module.NetModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *  @author Niranjan
 *  @since 1/7/17
 */
public class CBApplication extends Application {

    private static final String BASE_URL = "http://www.personalityforge.com/";

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setApplicationComponent();
    }

    private void setApplicationComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(BASE_URL))
                .databaseModule(new DatabaseModule(this))
                .build();

        mComponent.inject(this);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
