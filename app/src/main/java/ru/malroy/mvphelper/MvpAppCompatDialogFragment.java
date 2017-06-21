package ru.malroy.mvphelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;

import ru.malroy.mvphelper.delegate.MvpFragmentDelegate;
import ru.malroy.mvphelper.delegate.MvpFragmentDelegateImpl;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 24.03.2016.
 */
public abstract class MvpAppCompatDialogFragment<V extends MvpView, P extends Presenter<V>> extends AppCompatDialogFragment implements MvpDelegateCallback<V, P>, MvpView {
    @Nullable
    private MvpFragmentDelegate mvpDelegate;
    @Nullable
    private P presenter;

    @NonNull
    protected MvpFragmentDelegate getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new MvpFragmentDelegateImpl<>(this);
        }
        return mvpDelegate;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
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
        return getRetainInstance();
    }

}
