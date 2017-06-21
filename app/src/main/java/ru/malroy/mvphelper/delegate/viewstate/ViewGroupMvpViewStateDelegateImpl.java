package ru.malroy.mvphelper.delegate.viewstate;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.ViewGroupMvpDelegateImpl;
import ru.malroy.mvphelper.delegate.callback.MvpViewStateViewGroupDelegateCallback;
import ru.malroy.mvphelper.viewstate.ViewStateSavedState;

/**
 * Created by Dzmitry Lamaka on 05.01.2016.
 */
public class ViewGroupMvpViewStateDelegateImpl<V extends MvpView, P extends Presenter<V>> extends ViewGroupMvpDelegateImpl<V, P> {
    @Nullable
    private MvpLayoutViewStateInternalDelegate<V, P> internalDelegate;

    public ViewGroupMvpViewStateDelegateImpl(@NonNull final MvpViewStateViewGroupDelegateCallback<V, P> mvpDelegateCallback) {
        super(mvpDelegateCallback);
    }

    @NonNull
    private MvpLayoutViewStateInternalDelegate<V, P> getInternalDelegate() {
        if (internalDelegate == null) {
            internalDelegate = new MvpLayoutViewStateInternalDelegate<>((MvpViewStateViewGroupDelegateCallback<V, P>) mvpDelegateCallback);
        }

        return internalDelegate;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getInternalDelegate().createOrRestoreViewState(null);
        getInternalDelegate().applyViewState();
    }

    @NonNull
    public Parcelable onSaveInstanceState() {
        Parcelable superParcelable = getViewStateDelegateCallback().callSuperOnSaveInstanceState();
        return getInternalDelegate().saveViewState(superParcelable);
    }

    public void onRestoreInstanceState(@NonNull final Parcelable state) {
        ViewStateSavedState viewStateSavedState = (ViewStateSavedState) state;
        getInternalDelegate().createOrRestoreViewState(viewStateSavedState);

        getViewStateDelegateCallback().callSuperOnRestoreInstanceState(viewStateSavedState.getSuperState());
    }

    @NonNull
    private MvpViewStateViewGroupDelegateCallback<V, P> getViewStateDelegateCallback() {
        return (MvpViewStateViewGroupDelegateCallback<V, P>) mvpDelegateCallback;
    }

}
