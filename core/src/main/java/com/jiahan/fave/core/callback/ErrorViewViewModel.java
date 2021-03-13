package com.jiahan.fave.core.callback;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

public interface ErrorViewViewModel {
    ObservableField<String> getErrorCode();

    ObservableBoolean getErrorCodeVisibility();

    ObservableField<Drawable> getErrorIcon();

    ObservableField<String> getErrorTitle();

    ObservableField<String> getErrorDescription();

    ObservableField<String> getRetryButtonText();

    Drawable getRetryButtonBackground();

    void onRetryClicked(View view);

    ObservableBoolean getErrorVisibility();
}