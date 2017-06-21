package ru.malroy.mvphelper.viewstate;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpFragment;
import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.MvpFragmentDelegate;
import ru.malroy.mvphelper.delegate.callback.MvpViewStateDelegateCallback;
import ru.malroy.mvphelper.delegate.viewstate.MvpViewStateFragmentDelegate;

/**
 * Created by Dzmitry Lamaka on 21.12.2015.
 */
public abstract class MvpViewStateFragment<V extends MvpView, P extends Presenter<V>> extends MvpFragment<V, P> implements MvpViewStateDelegateCallback<V, P> {
    @Nullable
    protected ViewState<V> viewState;

    @NonNull
    @Override
    protected MvpFragmentDelegate getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new MvpViewStateFragmentDelegate<>(this);
        }
        return mvpDelegate;
    }

    @Nullable
    @Override
    public ViewState<V> getViewState() {
        return viewState;
    }

    @Override
    public void setViewState(@NonNull final ViewState<V> viewState) {
        this.viewState = viewState;
    }
}
