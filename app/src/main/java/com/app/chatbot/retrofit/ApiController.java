package com.app.chatbot.retrofit;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by niranjan on 12/29/16.
 */
public class ApiController<T> {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    public void hitApi(final RequestBuilder requestBuilder, final ApiDataReceiveCallback callback) {
        Call<String> call = getApiFromApiType(requestBuilder.apiType,requestBuilder.getExtraParameters());
        if(call != null) {
            call.enqueue(new ApiResponseCallBack(callback,requestBuilder.apiType, requestBuilder.getExtraParameters()));
        }
    }

    private Call<String> getApiFromApiType(int apiType, Map<String, String> extraParams) {
        switch (apiType) {
            case NetworkConstants.API_GET_BOT_MESSAGE:
                return apiService.getBotMessage(extraParams);
            default:
                return null;
        }

    }

    private static class ApiResponseCallBack implements Callback<String> {

        private final WeakReference<ApiDataReceiveCallback> apiDataReceiveCallbackWeakReference;
        private int type;
        HashMap<String, String> params;

        ApiResponseCallBack(ApiDataReceiveCallback apiDataReceiveCallback, int type, HashMap<String, String> params) {
            this.apiDataReceiveCallbackWeakReference = new WeakReference<>(apiDataReceiveCallback);
            this.type = type;
            this.params = params;
        }

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            try {
                if(response.isSuccessful()) {
                    handleApiResponse(response.body());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void handleApiResponse(final String response) throws Exception {
            ApiDataReceiveCallback apiDataReceiveCallback = apiDataReceiveCallbackWeakReference.get();
            if (apiDataReceiveCallback != null)
                apiDataReceiveCallback.onDataReceived(response, type, Integer.valueOf(params.get("id")));
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            ApiDataReceiveCallback apiDataReceiveCallback = apiDataReceiveCallbackWeakReference.get();
            if (apiDataReceiveCallback != null)
                apiDataReceiveCallback.onError(type, params);
            t.printStackTrace();
        }
    }
}
