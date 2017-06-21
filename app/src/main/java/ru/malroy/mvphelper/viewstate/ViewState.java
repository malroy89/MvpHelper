package ru.malroy.mvphelper.viewstate;

import android.support.annotation.NonNull;

import ru.malroy.mvphelper.MvpView;

/**
 * Created by Dzmitry Lamaka on 16.12.2015.
 */
public interface ViewState<V extends MvpView> {

    void apply(@NonNull V view);

}
