package ru.malroy.mvphelper;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 02.12.2015.
 */
public interface LoadingView<E> extends BaseLoadingView {
    void showLoading();
    void hideLoading();
    void showContent();
    void setData(@NonNull E data);
}
