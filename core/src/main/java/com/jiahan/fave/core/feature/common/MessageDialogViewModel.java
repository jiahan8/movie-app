package com.jiahan.fave.core.feature.common;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.view.View;

public interface MessageDialogViewModel {
    Drawable getBackground();

    Drawable getResourceIcon();

    boolean getResourceIconVisibility();

    String getTitle();

    boolean getTitleVisibility();

    Spannable getMessage();

    Drawable getMessageBackground();

    boolean getWebViewVisibility();

    Drawable getPositiveBackground();

    String getPositiveButtonText();

    boolean getPositiveVisibility();

    void onPositiveClicked(View view);

    Drawable getNegativeBackground();

    String getNegativeButtonText();

    boolean getNegativeVisibility();

    void onNegativeClicked(View view);
}