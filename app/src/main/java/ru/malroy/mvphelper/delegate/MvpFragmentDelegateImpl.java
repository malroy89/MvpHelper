package ru.malroy.mvphelper.delegate;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 18.12.2015.
 */
public class MvpFragmentDelegateImpl<V extends MvpView, P extends Presenter<V>> implements MvpFragmentDelegate {
    @NonNull
    protected final MvpDelegateCallback<V, P> mvpDelegateCallback;
    @Nullable
    private MvpInternalDelegate<V, P> mvpInternalDelegate;

    public MvpFragmentDelegateImpl(@NonNull final MvpDelegateCallback<V, P> mvpDelegateCallback) {
        this.mvpDelegateCallback = mvpDelegateCallback;
    }

    @NonNull
    private MvpInternalDelegate<V, P> getMvpInternalDelegate() {
        if (mvpInternalDelegate == null) {
            mvpInternalDelegate = new MvpInternalDelegate<>(mvpDelegateCallback);
        }
        return mvpInternalDelegate;
    }

    @CallSuper
    @Override
    public void onCreate() {
        getMvpInternalDelegate().injectDependencies();
    }

    @CallSuper
    @Override
    public void onActivityCreated() {
        getMvpInternalDelegate().createPresenter();
        getMvpInternalDelegate().attachView();
        mvpDelegateCallback.onInitialized();
    }

    @CallSuper
    @Override
    public void onViewCreated() {
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        getMvpInternalDelegate().detachView();
    }

}
