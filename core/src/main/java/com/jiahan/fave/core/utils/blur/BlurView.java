package com.jiahan.fave.core.utils.blur;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

public class BlurView extends AppCompatImageView {
    private static final int FADE_DURATION = 300;

    private int tag = 0;

    public BlurView(Context context) {
        super(context);
    }

    public void show() {
        if (tag++ <= 0) {
            animate().alpha(1f).setDuration(FADE_DURATION).start();
        }
    }

    public void hide() {
        if (--tag <= 0) {
            animate().alpha(0f).setDuration(FADE_DURATION).start();
        }
    }

    public void blur() {
        Activity activity = (Activity) getContext();
        if (tag <= 0) {
            View decorView1 = activity.getWindow().getDecorView();
            Bitmap bitmap = Bitmap.createBitmap(decorView1.getWidth(), decorView1.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            decorView1.draw(canvas);
            setBackground(new BitmapDrawable(getResources(), BlurUtils.blur(activity, bitmap, 2, 0.2f)));
            tag = 0;
        }
    }
}