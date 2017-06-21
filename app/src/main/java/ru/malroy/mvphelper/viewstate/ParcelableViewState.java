package ru.malroy.mvphelper.viewstate;

import android.os.Parcelable;

import ru.malroy.mvphelper.MvpView;


/**
 * Created by Dzmitry Lamaka on 05.01.2016.
 */
public interface ParcelableViewState<V extends MvpView> extends ViewState<V>, Parcelable {
}
