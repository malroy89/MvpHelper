package ru.malroy.mvphelper.delegate;

/**
 * Created by Dzmitry Lamaka on 18.12.2015.
 */
public interface MvpFragmentDelegate {

    void onCreate();

    void onActivityCreated();

    void onViewCreated();

    void onDestroyView();

}
