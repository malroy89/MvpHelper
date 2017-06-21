package ru.malroy.mvphelper.viewstate;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import ru.malroy.mvphelper.MvpFrameLayout;
import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.ViewGroupMvpDelegate;
import ru.malroy.mvphelper.delegate.callback.MvpViewStateViewGroupDelegateCallback;
import ru.malroy.mvphelper.delegate.viewstate.ViewGroupMvpViewStateDelegateImpl;


/**
 * Created by Dzmitry Lamaka on 05.01.2016.
 */
public abstract class MvpViewStateFrameLayout<V extends MvpView, P extends Presenter<V>> extends MvpFrameLayout<V, P> implements MvpViewStateViewGroupDelegateCallback<V, P> {
    @Nullable
    private ParcelableViewState<V> parcelableViewState;

    public MvpViewStateFrameLayout(Context context) {
        super(context);
    }

    public MvpViewStateFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MvpViewStateFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public MvpViewStateFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @NonNull
    @Override
    protected ViewGroupMvpDelegate getViewGroupDelegate() {
        if (viewGroupMvpDelegate == null) {
            viewGroupMvpDelegate = new ViewGroupMvpViewStateDelegateImpl<>(this);
        }
        return viewGroupMvpDelegate;
    }

    @NonNull
    private ViewGroupMvpViewStateDelegateImpl getViewStateDelegate() {
        return (ViewGroupMvpViewStateDelegateImpl) getViewGroupDelegate();
    }

    @Override
    public final void onInitialized() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected Parcelable onSaveInstanceState() {
        return getViewStateDelegate().onSaveInstanceState();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        getViewStateDelegate().onRestoreInstanceState(state);
    }

    @NonNull
    @Override
    public Parcelable callSuperOnSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public void callSuperOnRestoreInstanceState(@NonNull final Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    public void setViewState(@NonNull final ViewState<V> viewState) {
        parcelableViewState = (ParcelableViewState<V>) viewState;
    }

    @Nullable
    @Override
    public ViewState<V> getViewState() {
        return parcelableViewState;
    }
}
