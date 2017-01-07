package com.app.chatbot.presenter;

public interface BasePresenter<B extends BaseView> {

    void setView(B view);

    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();

    void onActivityCreated();

    void onViewCreated();

    void onCreateView();
}
