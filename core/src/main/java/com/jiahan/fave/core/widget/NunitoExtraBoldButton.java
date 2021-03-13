package com.jiahan.fave.core.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.button.MaterialButton;
import com.jiahan.fave.core.R;

public class NunitoExtraBoldButton extends MaterialButton {
    public NunitoExtraBoldButton(final Context context) {
        super(context, null);
        init(context);
    }

    public NunitoExtraBoldButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NunitoExtraBoldButton(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.nunito_extra_bold);
        setTypeface(typeface);
        setIncludeFontPadding(false);
    }
}