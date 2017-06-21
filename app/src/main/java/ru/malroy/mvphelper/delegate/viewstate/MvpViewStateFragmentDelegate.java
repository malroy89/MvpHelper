package ru.malroy.mvphelper.delegate.viewstate;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.malroy.mvphelper.MvpView;
import ru.malroy.mvphelper.Presenter;
import ru.malroy.mvphelper.delegate.MvpFragmentDelegateImpl;
import ru.malroy.mvphelper.delegate.callback.MvpViewStateDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 21.12.2015.
 */
public class MvpViewStateFragmentDelegate<V extends MvpView, P extends Presenter<V>> extends MvpFragmentDelegateImpl<V, P> {
    @Nullable
    private MvpViewStateInternalDelegate<V, P> mvpViewStateInternalDelegate;

    public MvpViewStateFragmentDelegate(@NonNull final MvpViewStateDelegateCallback<V, P> mvpDelegateCallback) {
        super(mvpDelegateCallback);
    }

    @NonNull
    private MvpViewStateInternalDelegate<V, P> getMvpViewStateInternalDelegate() {
        if (mvpViewStateInternalDelegate == null) {
            mvpViewStateInternalDelegate = new MvpViewStateInternalDelegate<>(mvpDelegateCallback);
        }
        return mvpViewStateInternalDelegate;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getMvpViewStateInternalDelegate().createViewState();
    }

    @Override
    public void onActivityCreated() {
        super.onActivityCreated();
        getMvpViewStateInternalDelegate().applyViewState();
    }

}
