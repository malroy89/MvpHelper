package ru.malroy.mvphelper.exception;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 16.12.2015.
 */
public class ServerHttpException extends HttpException {

    public ServerHttpException(final int code, @NonNull final String message) {
        super(code, message);
    }
    
}
