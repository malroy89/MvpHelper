package ru.malroy.mvphelper.delegate;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 29.12.2015.
 */
public class MvpActivityDelegateImpl<V extends MvpView, P extends Presenter<V>> implements MvpActivityDelegate {
    @NonNull
    private final MvpDelegateCallback<V, P> mvpDelegateCallback;
    @Nullable
    private MvpInternalDelegate<V, P> mvpInternalDelegate;

    public MvpActivityDelegateImpl(@NonNull final MvpDelegateCallback<V, P> mvpDelegateCallback) {
        this.mvpDelegateCallback = mvpDelegateCallback;
    }

    @NonNull
    private MvpInternalDelegate<V, P> getMvpInternalDelegate() {
        if (mvpInternalDelegate == null) {
            mvpInternalDelegate = new MvpInternalDelegate<>(mvpDelegateCallback);
        }
        return mvpInternalDelegate;
    }

    @Override
    public void onCreate() {
        getMvpInternalDelegate().injectDependencies();
        getMvpInternalDelegate().createPresenter();
        getMvpInternalDelegate().attachView();
        mvpDelegateCallback.onInitialized();
    }

    @Override
    public void onDestroy() {
        getMvpInternalDelegate().detachView();
    }
}
