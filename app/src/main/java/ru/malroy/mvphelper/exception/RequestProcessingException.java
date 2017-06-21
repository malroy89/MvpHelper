package ru.malroy.mvphelper.exception;

import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 14.12.2015.
 */
public class RequestProcessingException extends Exception {
    @NonNull
    private final String reasonMessage;

    public RequestProcessingException(@NonNull final String reasonMessage) {
        super(reasonMessage);
        this.reasonMessage = reasonMessage;
    }

    @NonNull
    public String getReasonMessage() {
        return reasonMessage;
    }
}
