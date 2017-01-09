package com.app.chatbot.retrofit;

import java.util.HashMap;

public interface ApiDataReceiveCallback {
	void onDataReceived(String response, int type, int parentMesaageCode);
	void onError(int type, HashMap<String, String> params);
}
