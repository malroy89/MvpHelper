package ru.malroy.mvphelper;

/**
 * Created by Dzmitry Lamaka on 05.03.2016.
 */
public interface InitListener {

    /**
     * Implement this and provide logic which should be executed when {@link EmbeddedComponent} is
     * initialized.
     */
    void onInit();
}
