package com.app.chatbot.presenter;

import android.content.Context;

import javax.inject.Inject;

public class BasePresenterImpl<B extends BaseView> implements BasePresenter<B> {

    protected final Context baseContext;
    protected B view = null;

    @Inject
    BasePresenterImpl(Context baseContext) {
        this.baseContext = baseContext;
    }

    @Override
    public void setView(B view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        //to add behaviour on onCreate, override this method
    }

    @Override
    public void onResume() {
        //to add behaviour on onResume, override this method
    }

    @Override
    public void onPause() {
        //to add behaviour on onPause, override this method
    }

    @Override
    public void onDestroy() {
        //to add behaviour on onDestroy, override this method
    }


    /**
     * Only required in case of fragments and presenters.
     */
    @Override
    public void onActivityCreated() {

    }

    /**
     * Only required in case of fragments and presenters.
     */
    @Override
    public void onCreateView() {

    }

    /**
     * Only required in case of fragments and presenters.
     */
    @Override
    public void onViewCreated() {

    }
}
