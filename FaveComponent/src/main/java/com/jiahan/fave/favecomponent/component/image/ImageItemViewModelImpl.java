package com.jiahan.fave.favecomponent.component.image;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jiahan.fave.core.common.PhotoGalleriesActivity;
import com.jiahan.fave.core.feature.common.PhotoGalleriesItemViewModelImpl;
import com.jiahan.fave.favecomponent.component.image.tracker.ImageTracker;

import java.util.ArrayList;
import java.util.List;

public class ImageItemViewModelImpl extends PhotoGalleriesItemViewModelImpl implements ImageItemViewModel {
    private final Context      mContext;
    private final ImageTracker mImageTracker;
    private final int          mWidth;
    private final int          mHeight;
    private final int          mCornerRadius;
    private final String       mSectionName;
    private final List<String> mImageList;
    private final int          mDefaultIndex;

    public ImageItemViewModelImpl(@NonNull final Context context,
                                  final ImageTracker imageTracker,
                                  final int width,
                                  final int height,
                                  final int cornerRadius,
                                  final String sectionName,
                                  @NonNull final List<String> imageList,
                                  final int defaultIndex,
                                  @Nullable final String imageUrl) {
        super(TextUtils.isEmpty(imageUrl) ? imageList.get(defaultIndex) : imageUrl);
        mContext = context;
        mImageTracker = imageTracker;
        mWidth = width;
        mHeight = height;
        mCornerRadius = cornerRadius;
        mSectionName = sectionName;
        mImageList = imageList;
        mDefaultIndex = defaultIndex;
    }

    @Override
    public void onItemClicked(final View view) {
        mImageTracker.onTapImage();
        PhotoGalleriesActivity.start(mContext, mSectionName, new ArrayList<>(mImageList), mDefaultIndex);
    }

    @Override
    public int getWidth() {
        return mWidth;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public int getCornerRadius() {
        return mCornerRadius;
    }
}