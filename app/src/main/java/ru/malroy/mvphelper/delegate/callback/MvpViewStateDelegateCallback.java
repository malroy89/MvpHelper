package ru.malroy.mvphelper.delegate.callback;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.viewstate.ViewState;

/**
 * Created by Dzmitry Lamaka on 21.12.2015.
 */
public interface MvpViewStateDelegateCallback<V extends MvpView, P extends Presenter<V>> extends MvpDelegateCallback<V, P> {

    @Nullable
    ViewState<V> getViewState();

    @NonNull
    ViewState<V> createViewState();

    void setViewState(@NonNull ViewState<V> viewState);

}
