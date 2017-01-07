package com.app.chatbot.module;

import android.content.Context;

import com.app.chatbot.retrofit.ApiController;
import com.app.chatbot.retrofit.ApiService;
import com.app.chatbot.retrofit.ToStringConverter;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class NetModule {

    private final String mBaseUrl;

    // Constructor needs one parameter to instantiate.
    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Context context) {
        OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        okhttp.connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);
        return okhttp.build();
    }

    @Provides
    @Singleton
    ToStringConverter provideConverter() {
        return new ToStringConverter();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, ToStringConverter toStringConverter) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(toStringConverter)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    ApiController providesApiController(ApiService apiService) {
        return new ApiController(apiService);
    }
}
