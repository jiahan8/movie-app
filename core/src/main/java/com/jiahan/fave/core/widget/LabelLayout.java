package com.jiahan.fave.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.utils.drawableUtils.DrawableUtils;

public class LabelLayout extends FrameLayout {
    private int labelRadius          = 0;
    private int labelBackgroundColor = 0;
    private int labelBorderWidth     = 0;
    private int labelBorderColor     = 0;

    public LabelLayout(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public LabelLayout(@NonNull Context context,
                       @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public LabelLayout(@NonNull Context context,
                       @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(@NonNull Context context,
                      @Nullable AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LabelLayout, 0, 0);
            labelRadius = ta.getDimensionPixelSize(R.styleable.LabelLayout_labelRadius, 0);
            labelBackgroundColor = ta.getColor(R.styleable.LabelLayout_labelBackgroundColor, 0);
            labelBorderWidth = ta.getDimensionPixelSize(R.styleable.LabelLayout_labelBorderWidth, 0);
            labelBorderColor = ta.getColor(R.styleable.LabelLayout_labelBorderColor, 0);
            if(ta.getBoolean(R.styleable.LabelLayout_innerPaddingEnable , true)) {
                setPadding(getResources().getDimensionPixelSize(R.dimen.margin_small), 0, getResources().getDimensionPixelSize(R.dimen.margin_small), 0);
            }
            ta.recycle();
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setBackground(DrawableUtils.createDrawablePixel(GradientDrawable.RECTANGLE,
                labelRadius == 0 ? getHeight() / 2 : labelRadius,
                labelBorderWidth,
                labelBorderColor,
                labelBackgroundColor));
    }
}
