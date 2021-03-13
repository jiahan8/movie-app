package com.jiahan.fave.core.widget.roundedCorner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.jiahan.fave.core.R;

public class RoundedCornerLinearLayout extends LinearLayout {
    private float cornerRadius;

    public RoundedCornerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundedCornerLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int count = canvas.save();
        final Path path = new Path();
        path.addRoundRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), cornerRadius, cornerRadius, Path.Direction.CW);
        canvas.clipPath(path, Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 ? Region.Op.INTERSECT : Region.Op.REPLACE);
        canvas.clipPath(path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(count);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomViewAttribute, 0, 0);
        try {
            cornerRadius = typedArray.getDimension(
                    R.styleable.CustomViewAttribute_cornerRadius,
                    context.getResources().getDimensionPixelSize(R.dimen.static_size_3));
        } finally {
            typedArray.recycle();
        }
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
}