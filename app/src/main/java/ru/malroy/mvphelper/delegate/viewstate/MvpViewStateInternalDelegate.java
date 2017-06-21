package ru.malroy.mvphelper.delegate.viewstate;

import android.support.annotation.NonNull;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.MvpInternalDelegate;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;
import ru.malroy.mvphelper.delegate.callback.MvpViewStateDelegateCallback;
import ru.malroy.mvphelper.viewstate.ViewState;

/**
 * Created by Dzmitry Lamaka on 29.12.2015.
 */
public class MvpViewStateInternalDelegate<V extends MvpView, P extends Presenter<V>> extends MvpInternalDelegate<V, P> {

    public MvpViewStateInternalDelegate(@NonNull final MvpDelegateCallback<V, P> delegateCallback) {
        super(delegateCallback);
    }

    public void createViewState() {
        ViewState<V> viewState = getViewStateCallback().getViewState();
        if (viewState == null) {
            viewState = getViewStateCallback().createViewState();
            getViewStateCallback().setViewState(viewState);
        }
    }

    public void applyViewState() {
        final ViewState<V> viewState = getViewStateCallback().getViewState();
        if (viewState != null) {
            final V view = getViewStateCallback().getMvpView();
            viewState.apply(view);
        }
    }

    @NonNull
    private MvpViewStateDelegateCallback<V, P> getViewStateCallback() {
        return (MvpViewStateDelegateCallback<V, P>) delegateCallback;
    }


}
