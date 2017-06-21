package ru.malroy.mvphelper.viewstate;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Dzmitry Lamaka on 05.01.2016.
 */
public class ViewStateSavedState extends View.BaseSavedState {
    @NonNull
    public static final Parcelable.Creator<ViewStateSavedState> CREATOR =
            new Parcelable.Creator<ViewStateSavedState>() {
                public ViewStateSavedState createFromParcel(Parcel in) {
                    return new ViewStateSavedState(in);
                }

                public ViewStateSavedState[] newArray(int size) {
                    return new ViewStateSavedState[size];
                }
            };

    @Nullable
    private ParcelableViewState viewStateToSave;

    public ViewStateSavedState(@NonNull final Parcel source) {
        super(source);
        viewStateToSave = source.readParcelable(ParcelableViewState.class.getClassLoader());
    }

    public ViewStateSavedState(@NonNull final Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(@NonNull final Parcel out, final int flags) {
        super.writeToParcel(out, flags);
        out.writeParcelable(viewStateToSave, flags);
    }

    @Nullable
    public ParcelableViewState getViewState() {
        return viewStateToSave;
    }

    public void setViewStateToSave(@NonNull final ParcelableViewState viewStateToSave) {
        this.viewStateToSave = viewStateToSave;
    }
}
