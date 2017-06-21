package ru.malroy.mvphelper.delegate;

import android.support.annotation.NonNull;

import ru.malroy.mvphelper.LoadingView;
import ru.malroy.mvphelper.delegate.callback.LoadingPresenterCallback;

/**
 * Created by Dzmitry Lamaka on 22.12.2015.
 */
public class LoadingPresenterDelegate<V extends LoadingView<M>, M> {
    @NonNull
    private final LoadingPresenterCallback<V> loadingPresenterCallback;

    public LoadingPresenterDelegate(@NonNull final LoadingPresenterCallback<V> loadingPresenterCallback) {
        this.loadingPresenterCallback = loadingPresenterCallback;
    }

    public void callShowLoading() {
        if (isViewAttached()) {
            getView().showLoading();
        }
    }

    public void callShowError(@NonNull final Throwable throwable) {
        if (isViewAttached()) {
            getView().showError(throwable);
        }
    }

    public void callHideLoading() {
        if (isViewAttached()) {
            getView().hideLoading();
        }
    }

    public void callShowContent() {
        if (isViewAttached()) {
            getView().showContent();
        }
    }

    public void callSetData(M data) {
        if (isViewAttached()) {
            getView().setData(data);
        }
    }

    @NonNull
    private V getView() {
        return loadingPresenterCallback.getView();
    }

    private boolean isViewAttached() {
        return loadingPresenterCallback.isViewAttached();
    }
}
