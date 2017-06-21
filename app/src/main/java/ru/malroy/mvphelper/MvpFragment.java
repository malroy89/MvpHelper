package ru.malroy.mvphelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import ru.malroy.mvphelper.delegate.MvpFragmentDelegate;
import ru.malroy.mvphelper.delegate.MvpFragmentDelegateImpl;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 18.12.2015.
 */
public abstract class MvpFragment<V extends MvpView, P extends Presenter<V>> extends Fragment implements MvpDelegateCallback<V, P>, MvpView, EmbeddedComponent {
    @Nullable
    protected MvpFragmentDelegate mvpDelegate;
    @Nullable
    protected P presenter;
    @Nullable
    private InitListener initListener;

    @NonNull
    protected MvpFragmentDelegate getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new MvpFragmentDelegateImpl<>(this);
        }
        return mvpDelegate;
    }

    @Override
    public void onCreate(@NonNull final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this check is needed because if we use fragment in child fragment manager, app crashes
        if (!isChildFragment()) {
            setRetainInstance(true);
        }
        getMvpDelegate().onCreate();
    }

    protected boolean isChildFragment() {
        return false;
    }

    @Override
    public void onInitialized() {
        if (initListener != null) {
            initListener.onInit();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated();
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
    @SuppressWarnings("unchecked")
    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainedInstance() {
        Activity activity = getActivity();
        return (activity != null && activity.isChangingConfigurations()) && getRetainInstance();
    }

    @Override
    public void setInitListener(@NonNull final InitListener initListener) {
        if (isAdded()) {
            initListener.onInit();
        } else {
            this.initListener = initListener;
        }
    }
}
