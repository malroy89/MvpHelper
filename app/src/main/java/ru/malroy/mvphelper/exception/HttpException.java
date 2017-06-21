package ru.malroy.mvphelper.exception;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 16.12.2015.
 */
public class HttpException extends Exception {
    private final int code;
    @NonNull
    private final String message;

    public HttpException(final int code, @NonNull final String message) {
        super("HTTP " + code + " " + message);
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    @NonNull
    public String message() {
        return message;
    }
}
