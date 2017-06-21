package ru.malroy.mvphelper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import ru.malroy.mvphelper.delegate.ViewGroupMvpDelegate;
import ru.malroy.mvphelper.delegate.ViewGroupMvpDelegateImpl;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 22.02.2016.
 */
public abstract class MvpLinearLayout<V extends MvpView, P extends Presenter<V>> extends LinearLayout implements MvpDelegateCallback<V, P> {
    @Nullable
    private P presenter;
    @Nullable
    protected ViewGroupMvpDelegate viewGroupMvpDelegate;

    public MvpLinearLayout(Context context) {
        super(context);
        init();
    }

    public MvpLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MvpLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MvpLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @CallSuper
    protected void init() {
        getViewGroupDelegate().onInit();
    }

    @NonNull
    protected ViewGroupMvpDelegate getViewGroupDelegate() {
        if (viewGroupMvpDelegate == null) {
            viewGroupMvpDelegate = new ViewGroupMvpDelegateImpl<>(this);
        }
        return viewGroupMvpDelegate;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewGroupDelegate().onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewGroupDelegate().onDetachedFromWindow();
    }

    @NonNull
    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public boolean isRetainedInstance() {
        return false;
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
}
