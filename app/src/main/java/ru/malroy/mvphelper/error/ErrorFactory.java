package ru.malroy.mvphelper.error;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Dzmitry Lamaka on 07.03.2016.
 */
public interface ErrorFactory {
    @NonNull
    String build(@NonNull Context context, @NonNull Throwable e);
}
