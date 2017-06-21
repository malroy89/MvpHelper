package ru.malroy.mvphelper;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import ru.malroy.mvphelper.error.ErrorHandler;
import ru.malroy.mvphelper.viewstate.MvpViewStateFragment;
import ru.malroy.mvphelper.viewstate.ViewState;

/**
 * Created by Dzmitry Lamaka on 19.12.2015.
 */
public abstract class PerformReactFragment<V extends PerformReactView, P extends PerformReactPresenter<V>> extends MvpViewStateFragment<V, P> implements PerformReactView {
    @Nullable
    protected ErrorHandler errorHandler;

    @NonNull
    protected ErrorHandler getErrorHandler() {
        if (errorHandler == null) {
            errorHandler = ErrorHandler.defaultErrorHandler();
        }
        return errorHandler;
    }

    @CallSuper
    @Override
    public void showInitView() {
        final PerformReactViewState<V> viewState = getViewState();
        if (viewState != null) {
            viewState.setShowInitViewState();
        }
    }

    @CallSuper
    @Override
    public void showLoading() {
        final PerformReactViewState<V> viewState = getViewState();
        if (viewState != null) {
            getViewState().setShowLoadingState();
        }
    }

    @Override
    public PerformReactViewState<V> getViewState() {
        return (PerformReactViewState<V>) super.getViewState();
    }

    @NonNull
    @Override
    public ViewState<V> createViewState() {
        return new PerformReactViewState<>();
    }

    @CallSuper
    @Override
    public void showError(@Nullable Throwable e) {
        final PerformReactViewState<V> viewState = getViewState();
        if (viewState == null) {
            throw new NullPointerException("ViewState is null");
        }

        Log.e("MvpHelper", "Error while performing request", e);
        viewState.setShowErrorState();
        View view = getView();
        if (view != null) {
            getErrorHandler().handle(e, view);
        }

    }

}
