package com.app.chatbot.module;

import android.content.Context;

import com.app.chatbot.DBAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *  @author Niranjan
 *  @since 1/7/17
 */
@Module
public class DatabaseModule {

    private final Context context;

    public DatabaseModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    DBAdapter.DatabaseHelper provideDBHelper() {
        return new DBAdapter.DatabaseHelper(context);
    }


    @Provides
    @Singleton
    DBAdapter providesDBAdapter(DBAdapter.DatabaseHelper databaseHelper) {
        return new DBAdapter(context,databaseHelper);
    }


}
