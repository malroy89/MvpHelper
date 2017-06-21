package ru.malroy.mvphelper.delegate.callback;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;

/**
 * Created by Dzmitry Lamaka on 05.01.2016.
 */
public interface MvpViewStateViewGroupDelegateCallback<V extends MvpView, P extends Presenter<V>> extends MvpViewStateDelegateCallback<V, P> {
    @NonNull
    Parcelable callSuperOnSaveInstanceState();

    void callSuperOnRestoreInstanceState(@NonNull Parcelable state);

    void onNewViewState();
}
