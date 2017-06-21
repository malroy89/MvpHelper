package ru.malroy.mvphelper.delegate;

/**
 * Created by Dzmitry Lamaka on 21.12.2015.
 */
public interface ViewGroupMvpDelegate {
    void onInit();
    void onAttachedToWindow();
    void onDetachedFromWindow();
}
