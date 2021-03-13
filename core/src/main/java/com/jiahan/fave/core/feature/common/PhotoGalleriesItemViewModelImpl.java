package com.jiahan.fave.core.feature.common;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.jiahan.fave.core.common.PhotoGalleriesActivity;
import com.jiahan.fave.core.utils.BitmapUtils;
import com.jiahan.fave.core.utils.Utils;

public class PhotoGalleriesItemViewModelImpl implements PhotoGalleriesItemViewModel {
    private final String mImageUrl;

    public PhotoGalleriesItemViewModelImpl(final String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Override
    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public View.OnLongClickListener getOnLongClickedListener() {
        return view -> {
            if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                if (imageView.getContext() instanceof PhotoGalleriesActivity) {
                    String[] requestPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    if (Utils.isPermissionGranted(view.getContext(), requestPermissions)) {
                        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        BitmapUtils.saveImage(bitmap, mImageUrl);
                        return false;
                    }
                    PhotoGalleriesActivity activity = (PhotoGalleriesActivity) imageView.getContext();
                    activity.getViewModel().getStoragePermissionLauncher().launch(requestPermissions);
                }
            }
            return false;
        };
    }
}