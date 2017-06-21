package ru.malroy.mvphelper;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 05.03.2016.
 */
public interface EmbeddedComponent {

    /**
     * If you want to make some operations when an embedded component like Fragment, ViewGroup or View
     * is initialized.
     */
    void setInitListener(@NonNull InitListener initListener);

}
