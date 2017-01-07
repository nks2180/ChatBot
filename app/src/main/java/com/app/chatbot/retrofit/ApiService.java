package com.app.chatbot.retrofit;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 *  @author Niranjan
 *  @since 1/7/17
 * Custom interface to declare all API calls
 */

public interface ApiService {

    @GET("api/chat/")
    Call<String> getBotMessage(@QueryMap Map<String, String> requestParams);

}
