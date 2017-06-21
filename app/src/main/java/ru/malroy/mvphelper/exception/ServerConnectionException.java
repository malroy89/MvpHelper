package ru.malroy.mvphelper.exception;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 16.12.2015.
 */
public class ServerConnectionException extends Exception {

    public ServerConnectionException() {
    }

    public ServerConnectionException(@NonNull final String detailMessage) {
        super(detailMessage);
    }
}
