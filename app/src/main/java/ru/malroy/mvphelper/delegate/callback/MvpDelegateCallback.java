package ru.malroy.mvphelper.delegate.callback;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;

/**
 * Created by Dzmitry Lamaka on 18.12.2015.
 */
public interface MvpDelegateCallback<V extends MvpView, P extends Presenter<V>> {

    void injectDependencies();

    @Nullable
    P getPresenter();

    @NonNull
    P createPresenter();

    void setPresenter(@NonNull P presenter);

    @NonNull
    V getMvpView();

    boolean isRetainedInstance();

    void onInitialized();
}
