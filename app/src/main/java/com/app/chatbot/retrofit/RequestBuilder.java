package com.app.chatbot.retrofit;

import com.app.chatbot.utils.Constants;

import java.util.HashMap;

public class RequestBuilder {
	// required mandatory fields
	public int apiType;

	// additional fields if required
	private HashMap<String, String> extraParameters;
	private String absoluteEndpointURL;


	public RequestBuilder(int apiType) {
		this.apiType = apiType;
	}

	public String getAbsoluteEndpointURL() {
		return absoluteEndpointURL;
	}

	public void setAbsoluteEndpointURL(String absoluteEndpointURL) {
		this.absoluteEndpointURL = absoluteEndpointURL;
	}

	public HashMap<String, String> getExtraParameters() {
		return extraParameters;
	}

	public void setExtraParameters(HashMap<String, String> extraParameters) {
		extraParameters.put("apiKey", Constants.API_KEY);
		extraParameters.put("chatBotID", Constants.CHAT_BOT_ID);
		extraParameters.put("externalID", Constants.EXTERNAL_ID);
		this.extraParameters = extraParameters;
	}

}
