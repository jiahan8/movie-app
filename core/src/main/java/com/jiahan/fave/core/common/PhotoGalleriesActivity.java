package com.jiahan.fave.core.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.feature.common.PhotoGalleriesViewModel;
import com.jiahan.fave.core.feature.common.PhotoGalleriesViewModelImpl;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.ScreenIdentifier;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleriesActivity extends BaseActivity {
    private static final String EXTRA_PREFIX        = com.jiahan.fave.core.common.PhotoGalleriesActivity.class.getName() + ".";
    private static final String EXTRA_SECTION_NAME  = EXTRA_PREFIX + "EXTRA_SECTION_NAME";
    private static final String EXTRA_IMAGE_LIST    = EXTRA_PREFIX + "EXTRA_IMAGE_LIST";
    private static final String EXTRA_DEFAULT_INDEX = EXTRA_PREFIX + "EXTRA_DEFAULT_INDEX";

    private String       mSectionName;
    private List<String> mImageList;
    private int          mDefaultIndex;

    private PhotoGalleriesViewModel mViewModel;

    public static void start(final Context context,
                             final String sectionName,
                             final ArrayList<String> imageList,
                             final int defaultIndex) {
        Intent intent = getStartIntent(context, sectionName, imageList, defaultIndex);
        context.startActivity(intent);
    }

    private static Intent getStartIntent(final Context context,
                                         final String sectionName,
                                         final ArrayList<String> activityImages,
                                         final int defaultIndex) {
        Intent intent = new Intent(context, com.jiahan.fave.core.common.PhotoGalleriesActivity.class);
        intent.putExtra(EXTRA_SECTION_NAME, sectionName);
        intent.putExtra(EXTRA_IMAGE_LIST, activityImages);
        intent.putExtra(EXTRA_DEFAULT_INDEX, defaultIndex);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo_galleries;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInputData();
    }

    @Override
    protected void getInputData() {
        final Intent intent = getIntent();
        mImageList = intent.getStringArrayListExtra(EXTRA_IMAGE_LIST);
        mDefaultIndex = intent.getIntExtra(EXTRA_DEFAULT_INDEX, 0);
        mSectionName = intent.getStringExtra(EXTRA_SECTION_NAME);
    }

    @Override
    protected void bindViewModel() {
        mViewModel = new PhotoGalleriesViewModelImpl(
                this,
                ScreenIdentifier.SCREEN_PHOTO_GALLERY,
                new EventSender(this),
                mSectionName,
                mImageList,
                mDefaultIndex);
        initBinding(mViewModel);
    }

    public PhotoGalleriesViewModel getViewModel() {
        return mViewModel;
    }
}