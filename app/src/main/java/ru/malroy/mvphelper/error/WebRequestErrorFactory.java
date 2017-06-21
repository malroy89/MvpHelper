package ru.malroy.mvphelper.error;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.malroy.mvphelper.R;
import ru.malroy.mvphelper.exception.NoInternetConnectionException;
import ru.malroy.mvphelper.exception.RequestProcessingException;
import ru.malroy.mvphelper.exception.ServerConnectionException;

/**
 * Created by Dzmitry Lamaka on 16.12.2015.
 */
final class WebRequestErrorFactory implements ErrorFactory {

    @NonNull
    @Override
    public String build(@NonNull final Context context, @NonNull final Throwable e) {
        if (e instanceof NoInternetConnectionException) {
            return context.getString(R.string.mvp_helper_error_message_no_internet);
        } else if (e instanceof ServerConnectionException) {
            return context.getString(R.string.mvp_helper_error_message_server_connection);
        } else if (e instanceof RequestProcessingException) {
            return ((RequestProcessingException) e).getReasonMessage();
        } else {
            return context.getString(R.string.mvp_helper_error_message_http);
        }
    }
}
