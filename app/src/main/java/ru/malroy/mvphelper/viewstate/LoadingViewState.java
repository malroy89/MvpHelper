package ru.malroy.mvphelper.viewstate;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.LoadingView;

/**
 * Created by Dzmitry Lamaka on 05.01.2016.
 */
public class LoadingViewState<V extends LoadingView<M>, M> implements ViewState<V> {
    private static final int STATE_LOADED = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_ERROR = -1;
    @Nullable
    protected M data;
    @Nullable
    private Throwable throwable;
    private int currentState = STATE_LOADING;

    @Override
    public void apply(@NonNull final V view) {
        switch (currentState) {
            case STATE_LOADED: {
                if (data == null) {
                    throw new NullPointerException("Data is null! Did you forget to call setLoadedState method?");
                }
                view.setData(data);
                view.showContent();
                break;
            }
            case STATE_LOADING: {
                view.showLoading();
                break;
            }
            case STATE_ERROR: {
                if (throwable == null) {
                    throw new NullPointerException("Throwable is null! Did you forget to call setErrorState method?");
                }
                view.showError(throwable);
                break;
            }
        }
    }

    public void setLoadingState() {
        currentState = STATE_LOADING;
    }

    public void setLoadedState(@NonNull final M data) {
        this.data = data;
        currentState = STATE_LOADED;
    }

    public void setErrorState(@NonNull final Throwable throwable) {
        this.throwable = throwable;
        currentState = STATE_ERROR;
    }
}
