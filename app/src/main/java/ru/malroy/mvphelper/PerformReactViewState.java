package ru.malroy.mvphelper;

import android.support.annotation.NonNull;

import ru.malroy.mvphelper.viewstate.ViewState;

/**
 * Created by Dzmitry Lamaka on 18.12.2015.
 */
public class PerformReactViewState<V extends PerformReactView> implements ViewState<V> {
    private static final int STATE_SHOW_INIT_VIEW = 0;
    private static final int STATE_SHOW_LOADING = 1;
    private static final int STATE_SHOW_ERROR = 2;

    protected int currentState = STATE_SHOW_INIT_VIEW;

    @Override
    public void apply(@NonNull final V view) {
        switch (currentState) {
            case STATE_SHOW_INIT_VIEW:
                view.showInitView();
                break;
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;
            case STATE_SHOW_ERROR:
                view.showError(null);
                break;
        }
    }

    public void setShowInitViewState() {
        currentState = STATE_SHOW_INIT_VIEW;
    }

    public void setShowLoadingState() {
        currentState = STATE_SHOW_LOADING;
    }

    public void setShowErrorState() {
        currentState = STATE_SHOW_ERROR;
    }

}
