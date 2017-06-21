package ru.malroy.mvphelper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by Dzmitry Lamaka on 02.12.2015.
 */
public class BasePresenter<V extends MvpView> implements Presenter<V> {
    @Nullable
    private WeakReference<V> weakView;

    @Override
    public void attachView(@NonNull final V view) {
        weakView = new WeakReference<>(view);
    }

    @Nullable
    public V getView() {
        return (weakView == null) ? null : weakView.get();
    }

    @Override
    public void detachView(final boolean isRetainedInstance) {
        if (weakView != null) {
            weakView.clear();
            weakView = null;
        }
    }
}
