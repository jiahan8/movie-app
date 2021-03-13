package com.jiahan.fave.core.callback;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

public interface LocationViewModel extends BaseToolbarViewModel {
    ObservableBoolean getLocationVisibility();

    Drawable getLocationBackground();

    ObservableField<String> getAppLocation();

    ObservableField<String> getDisplayAppLocation();

    void onSearchClicked(final View view);

    void onSearchLocationClicked(final View view);

    void onChangeCityClicked(final View view);

    void onUserLocationClicked(final View view);

    void updateLocation(@NonNull final String newLocation);
}