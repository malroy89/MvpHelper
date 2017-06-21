package ru.malroy.mvphelper.exception;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 16.12.2015.
 */
public class ClientHttpException extends HttpException {

    public ClientHttpException(final int code, @NonNull final String message) {
        super(code, message);
    }
}
