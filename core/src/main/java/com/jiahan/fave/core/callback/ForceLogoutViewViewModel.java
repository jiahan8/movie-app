package com.jiahan.fave.core.callback;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ObservableBoolean;

public interface ForceLogoutViewViewModel {
    ObservableBoolean getForceLogoutVisibility();

    Drawable getLogoutButtonBackground();

    void onLogoutClicked(View view);
}