package ru.malroy.mvphelper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import ru.malroy.mvphelper.delegate.ViewGroupMvpDelegate;
import ru.malroy.mvphelper.delegate.ViewGroupMvpDelegateImpl;
import ru.malroy.mvphelper.delegate.callback.MvpDelegateCallback;

/**
 * Created by Dzmitry Lamaka on 21.12.2015.
 */
public abstract class MvpFrameLayout<V extends MvpView, P extends Presenter<V>> extends FrameLayout implements MvpDelegateCallback<V, P>, EmbeddedComponent {
    @Nullable
    private P presenter;
    @Nullable
    protected ViewGroupMvpDelegate viewGroupMvpDelegate;
    @Nullable
    private InitListener initListener;

    public MvpFrameLayout(Context context) {
        super(context);
        init();
    }

    public MvpFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MvpFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MvpFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @CallSuper
    protected void init() {
        if (isInEditMode()) return;
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
    public void onInitialized() {
        if (initListener != null) {
            initListener.onInit();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) return;
        getViewGroupDelegate().onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (isInEditMode()) return;
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

    @Override
    public void setInitListener(@NonNull final InitListener initListener) {
        this.initListener = initListener;
    }
}
