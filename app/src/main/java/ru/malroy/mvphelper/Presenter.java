package ru.malroy.mvphelper;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 02.12.2015.
 */
public interface Presenter<V extends MvpView> {
    void attachView(@NonNull V view);
    void detachView(boolean isRetainedInstance);
}
