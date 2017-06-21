package ru.malroy.mvphelper.delegate.viewstate;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.MvpInternalDelegate;
import ru.malroy.mvphelper.delegate.callback.MvpViewStateViewGroupDelegateCallback;
import ru.malroy.mvphelper.viewstate.ParcelableViewState;
import ru.malroy.mvphelper.viewstate.ViewState;
import ru.malroy.mvphelper.viewstate.ViewStateSavedState;

/**
 * Created by Dzmitry Lamaka on 05.01.2016.
 */
public class MvpLayoutViewStateInternalDelegate<V extends MvpView, P extends Presenter<V>> extends MvpInternalDelegate<V, P> {

    private boolean needToApplyViewState;
    private boolean createOrRestoreAlreadyCalled;

    public MvpLayoutViewStateInternalDelegate(MvpViewStateViewGroupDelegateCallback<V, P> delegateCallback) {
        super(delegateCallback);
    }

    private MvpViewStateViewGroupDelegateCallback<V, P> getViewStateDelegateCallback() {
        return (MvpViewStateViewGroupDelegateCallback<V, P>) delegateCallback;
    }

    public Parcelable saveViewState(Parcelable superState) {
        ViewState<V> viewState = getViewStateDelegateCallback().getViewState();

        ViewStateSavedState viewStateSavedState = new ViewStateSavedState(superState);
        viewStateSavedState.setViewStateToSave((ParcelableViewState) viewState);

        return viewStateSavedState;
    }

    public void createOrRestoreViewState(@Nullable ViewStateSavedState savedState) {
        if (createOrRestoreAlreadyCalled) {
            return;
        }

        createOrRestoreAlreadyCalled = true;

        if (savedState != null) {
            ViewState<V> viewState = savedState.getViewState();
            getViewStateDelegateCallback().setViewState(viewState);

            if (getViewStateDelegateCallback().getViewState() != null) {
                needToApplyViewState = true;
                return;
            }
        }

        getViewStateDelegateCallback().setViewState(getViewStateDelegateCallback().createViewState());
        needToApplyViewState = false;
    }

    public void applyViewState() {
        if (needToApplyViewState) {
            getViewStateDelegateCallback().getViewState().apply(getViewStateDelegateCallback().getMvpView());
            return;
        }

        getViewStateDelegateCallback().onNewViewState();
    }

}
