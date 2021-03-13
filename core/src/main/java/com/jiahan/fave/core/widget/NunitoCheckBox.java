package com.jiahan.fave.core.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.content.res.ResourcesCompat;

import com.jiahan.fave.core.R;

/**
 * Created by zarea at 2020
 */
public class NunitoCheckBox extends AppCompatCheckBox {
    public NunitoCheckBox(@NonNull final Context context) {
        super(context);
        init(context);
    }

    public NunitoCheckBox(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NunitoCheckBox(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.nunito_regular);
        setTypeface(typeface);
    }
}
