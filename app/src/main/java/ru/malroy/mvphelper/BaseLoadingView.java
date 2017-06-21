package ru.malroy.mvphelper;

import android.support.annotation.Nullable;

/**
 * Created by Dzmitry Lamaka on 16.03.2016.
 */
public interface BaseLoadingView extends MvpView {
    void showError(@Nullable Throwable e);
}
