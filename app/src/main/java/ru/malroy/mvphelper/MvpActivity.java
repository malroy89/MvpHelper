package ru.malroy.mvphelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.malroy.mvphelper.delegate.MvpActivityDelegate;
import ru.malroy.mvphelper.delegate.MvpActivityDelegateImpl;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 29.12.2015.
 */
public abstract class MvpActivity<V extends MvpView, P extends Presenter<V>> extends AppCompatActivity implements MvpDelegateCallback<V, P> {
    @Nullable
    private P presenter;
    @Nullable
    private MvpActivityDelegate mvpActivityDelegate;

    @NonNull
    private MvpActivityDelegate getMvpActivityDelegate() {
        if (mvpActivityDelegate == null) {
            mvpActivityDelegate = new MvpActivityDelegateImpl<>(this);
        }
        return mvpActivityDelegate;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpActivityDelegate().onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpActivityDelegate().onDestroy();
    }

    @Nullable
    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(@NonNull final P presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainedInstance() {
        return false;
    }

}
