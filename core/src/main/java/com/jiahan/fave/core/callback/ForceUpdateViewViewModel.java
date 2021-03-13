package com.jiahan.fave.core.callback;

import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

public interface ForceUpdateViewViewModel {
    ObservableBoolean getForceUpdateVisibility();

    ObservableField<Spanned> getUpdateContent();

    Drawable getUpdateAppButtonBackground();

    void onUpdateAppClicked(View view);
}