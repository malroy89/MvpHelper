package ru.malroy.mvphelper.delegate.callback;


import android.support.annotation.NonNull;

import ru.malroy.mvphelper.MvpView;

/**
 * Created by Dzmitry Lamaka on 22.12.2015.
 */
public interface LoadingPresenterCallback<V extends MvpView> {
    @NonNull
    V getView();
    boolean isViewAttached();
}
