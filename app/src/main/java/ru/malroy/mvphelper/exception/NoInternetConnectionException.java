package ru.malroy.mvphelper.exception;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 16.12.2015.
 */
public class NoInternetConnectionException extends Exception {

    public NoInternetConnectionException() {
    }

    public NoInternetConnectionException(@NonNull final String detailMessage) {
        super(detailMessage);
    }
}
