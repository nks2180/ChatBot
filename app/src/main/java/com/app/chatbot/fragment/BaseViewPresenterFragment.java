package com.app.chatbot.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.chatbot.presenter.BasePresenterImpl;
import com.app.chatbot.presenter.BaseView;

/**
 *  @author Niranjan
 *  @since 1/7/17
 */

public abstract class BaseViewPresenterFragment<P extends BasePresenterImpl> extends BaseFragment implements BaseView {

    private P presenter;

    protected void initializePresenter(P presenter, BaseView baseView) {
        this.presenter = presenter;

        presenter.setView(baseView);
        presenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);
        presenter.onCreateView();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void moveToNextScreen(Intent intent) {
        startActivity(intent);
    }
}
