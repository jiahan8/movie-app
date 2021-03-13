package com.jiahan.fave.core.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.common.CoreApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BitmapUtils {
    public static Bitmap applyOverlay(final Context context, final Drawable sourceImage, final int overlayDrawableResourceId) {
        Bitmap bitmap = null;
        try {
            int width = sourceImage.getIntrinsicWidth();
            int height = sourceImage.getIntrinsicHeight();
            Resources resources = context.getResources();
            Drawable[] layers = new Drawable[2];
            layers[0] = sourceImage;
            Bitmap overlayBitmap = decodeSampledBitmapFromResourceDrawable(resources, overlayDrawableResourceId, width, height);
            if (overlayBitmap == null) {
                overlayBitmap = decodeSampledBitmapFromResourceShape(context, overlayDrawableResourceId, width, height);
            }
            if (overlayBitmap == null) {
                return null;
            }
            layers[1] = new BitmapDrawable(resources, overlayBitmap);
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            bitmap = com.jiahan.fave.core.utils.BitmapUtils.drawableToBitmap(layerDrawable);
        } catch (Exception ex) {
            com.jiahan.fave.core.utils.Logger.logException(ex);
        }
        return bitmap;
    }

    private static Bitmap decodeSampledBitmapFromResourceShape(final Context context, final int drawableRes, final int reqWidth, final int reqHeight) {
        Drawable drawable = AppCompatResources.getDrawable(context, drawableRes);
        if (drawable == null) {
            return null;
        }
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(reqWidth, reqHeight, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, reqWidth, reqHeight);
        drawable.draw(canvas);
        return bitmap;
    }

    private static Bitmap decodeSampledBitmapFromResourceDrawable(final Resources res, final int resId, final int reqWidth, final int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(final BitmapFactory.Options options, final int reqWidth, final int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap drawableToBitmap(final Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        }
        else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static void saveImage(final Bitmap image, final String imageUrl) {
        try {
            File dir = new File(Environment.getExternalStorageDirectory() + "/MyFave/");
            if (!(dir.exists() && dir.isDirectory())) {
                dir.mkdirs();
            }
            Uri uri = Uri.parse(imageUrl);
            String imagePath = uri.getPath().replace("/", "");
            File imageFile = new File(dir.getAbsolutePath(), imagePath);
            if (imageFile.exists()) {
                Toast.makeText(CoreApplication.getInstance(), CoreApplication.getInstance().getString(R.string.msg_successful_save_photo), Toast.LENGTH_SHORT).show();
                return;
            }
            OutputStream fOut = new FileOutputStream(imageFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.close();
            Toast.makeText(CoreApplication.getInstance(), CoreApplication.getInstance().getString(R.string.msg_successful_save_photo), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(CoreApplication.getInstance(), CoreApplication.getInstance().getString(R.string.msg_error_save_photo), Toast.LENGTH_SHORT).show();
        }
    }
}