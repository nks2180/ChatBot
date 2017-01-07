/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.chatbot.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.chatbot.CBApplication;
import com.app.chatbot.component.ApplicationComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base fragment created to be extended by every fragment in this application. This class provides
 * dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every fragment.
 *
 * @author Niranjan
 * @since 1/7/17
 */
public abstract class BaseFragment extends Fragment {


  private Unbinder unbinder;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    injectComponent(((CBApplication)getActivity().getApplication()).getComponent());
    initializePresenter();

  }

  protected abstract void initializePresenter();

  protected abstract void injectComponent(ApplicationComponent component);

  @Override
  public void onAttach(Context activity) {
    super.onAttach(activity);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(getFragmentLayout(), container, false);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
            && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
      view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
    injectViews(view);
    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  /**
   * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
   * avoid duplicate inflate code in every fragment. You only have to return the layout to
   * inflate in this method when extends BaseFragment.
   */
  abstract int getFragmentLayout();

  /**
   * Replace every field annotated with ButterKnife annotations like @BindView with the proper
   * value.
   *
   * @param view to extract each widget injected in the fragment.
   */
  private void injectViews(final View view) {
    unbinder = ButterKnife.bind(this,view);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
