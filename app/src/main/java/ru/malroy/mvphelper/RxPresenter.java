package ru.malroy.mvphelper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Dzmitry Lamaka on 15.12.2015.
 */
public class RxPresenter<V extends MvpView> extends BasePresenter<V> {
    @NonNull
    private final CompositeSubscription subscriptions = new CompositeSubscription();
    @Nullable
    private Observable.Transformer schedulerTransformer;

    public RxPresenter() {
    }

    public RxPresenter(@NonNull final Observable.Transformer schedulerTransformer) {
        this.schedulerTransformer = schedulerTransformer;
    }

    @SuppressWarnings("unchecked")
    public <M> void subscribe(@NonNull final Observable<M> observable,
                              @NonNull final Observer<M> observer) {
        subscribe(observable, observer, true);
    }

    @SuppressWarnings("unchecked")
    public <M> void subscribe(@NonNull final Observable<M> observable,
                              @NonNull final Observer<M> observer,
                              final boolean autoUnsubscribe) {
        if (schedulerTransformer == null) {
            subscribeBlocking(observable, observer, autoUnsubscribe);
        } else {
            subscribeParallel(observable, observer, autoUnsubscribe);
        }

    }

    private <M> void subscribeBlocking(@NonNull final Observable<M> observable,
                                       @NonNull final Observer<M> observer,
                                       final boolean autoUnsubscribe) {
        processSubscription(
                observable.subscribe(observer),
                autoUnsubscribe
        );

    }

    @SuppressWarnings("unchecked")
    private <M> void subscribeParallel(@NonNull final Observable<M> observable,
                                       @NonNull final Observer<M> observer,
                                       final boolean autoUnsubscribe) {
        final Observable.Transformer transformer = applySchedulerTransformer();
        if (transformer != null) {
            processSubscription(
                    observable.compose(transformer)
                            .subscribe(observer),
                    autoUnsubscribe
            );
        }
    }

    private void processSubscription(@NonNull final Subscription subscription, final boolean autoUnsubscribe) {
        if (autoUnsubscribe) {
            subscriptions.add(subscription);
        }
    }

    @Override
    public void detachView(final boolean isRetainedInstance) {
        super.detachView(isRetainedInstance);
        if (!isRetainedInstance) {
            unsubscribe();
        }
    }

    @Nullable
    @SuppressWarnings("unchecked")
    protected <T extends Observable.Transformer> T applySchedulerTransformer() {
        return (T) schedulerTransformer;
    }

    protected void unsubscribe() {
        if (!subscriptions.isUnsubscribed()) {
            subscriptions.clear();
        }
    }

}
