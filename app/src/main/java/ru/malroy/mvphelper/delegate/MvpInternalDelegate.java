package ru.malroy.mvphelper.delegate;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 21.12.2015.
 */
public class MvpInternalDelegate<V extends MvpView, P extends Presenter<V>> {
    @NonNull
    protected final MvpDelegateCallback<V, P> delegateCallback;

    public MvpInternalDelegate(@NonNull final MvpDelegateCallback<V, P> delegateCallback) {
        this.delegateCallback = delegateCallback;
    }

    public void injectDependencies() {
        delegateCallback.injectDependencies();
    }

    public void createPresenter() {
        P presenter = delegateCallback.getPresenter();
        if (presenter == null) {
            presenter = delegateCallback.createPresenter();
        }
        delegateCallback.setPresenter(presenter);
    }

    public void attachView() {
        final P presenter = getPresenter();
        if (presenter != null) {
            presenter.attachView(delegateCallback.getMvpView());
        }
    }

    public void detachView() {
        final P presenter = getPresenter();
        if (presenter != null) {
            presenter.detachView(delegateCallback.isRetainedInstance());
        }
    }

    @Nullable
    private P getPresenter() {
        return delegateCallback.getPresenter();
    }

}
