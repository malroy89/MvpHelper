package ru.malroy.mvphelper;

/**
 * Created by Dzmitry Lamaka on 18.12.2015.
 */
public interface PerformReactView extends BaseLoadingView {
    void showInitView();

    void showLoading();

    void onSuccess();
}
