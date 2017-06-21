package ru.malroy.mvphelper;


import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Created by Dzmitry Lamaka on 18.12.2015.
 */
public abstract class PerformReactPresenter<V extends PerformReactView> extends RxPresenter<V> {

    public PerformReactPresenter() {
    }

    public PerformReactPresenter(@NonNull final Observable.Transformer transformer) {
        super(transformer);
    }

    protected void callShowLoading() {
        final V view = getView();
        if (view != null) {
            view.showLoading();
        }
    }

    protected void callShowInitView() {
        final V view = getView();
        if (view != null) {
            view.showInitView();
        }
    }

    protected void callShowError(Throwable e) {
        final V view = getView();
        if (view != null) {
            view.showError(e);
        }
    }

    protected void callOnSuccess() {
        final V view = getView();
        if (view != null) {
            view.onSuccess();
        }
    }

}
