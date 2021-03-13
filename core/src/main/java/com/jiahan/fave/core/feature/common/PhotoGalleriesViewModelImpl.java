package com.jiahan.fave.core.feature.common;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.common.BaseViewViewModelImpl;
import com.jiahan.fave.core.common.PhotoGalleriesPagerAdapter;
import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.core.widget.PullBackLayout;

import java.util.List;

public class PhotoGalleriesViewModelImpl extends BaseViewViewModelImpl implements PhotoGalleriesViewModel {
    private final String       mSectionName;
    private final List<String> mImageList;
    private final int          mDefaultIndex;

    private final ObservableInt mPullBackBackgroundColor = new ObservableInt();
    private final ObservableField<String> mCurrentPhotoIndex       = new ObservableField<>();

    public PhotoGalleriesViewModelImpl(@NonNull final Context context,
                                       @NonNull final String screenIdentifier,
                                       final EventSender eventSender,
                                       final String sectionName,
                                       final List<String> imageList,
                                       final int defaultIndex) {
        super(context, screenIdentifier, eventSender);
        mSectionName = sectionName;
        mImageList = imageList;
        mDefaultIndex = defaultIndex;
        setIndexTotal(defaultIndex);
        setPullBackBackgroundColor(Color.argb(255, 0, 0, 0));
        sendScreenDisplayedEvent();
    }

    @Override
    public int getCustomToolbarLayout() {
        return 0;
    }

    @Override
    protected void sendScreenDisplayedEvent() {
        if (mEventSender == null) {
            return;
        }
        EventActions actions = mEventSender.screenDisplayed(getScreenIdentifier());
        actions.addProperty(PropertyConstant.Key.SECTION_NAME, mSectionName);
        mEventSender.send(actions);
    }

    @Override
    public PullBackLayout.OnPullListener getPullBackListener() {
        return new PullBackLayout.OnPullListener() {
            @Override
            public void onPullStart() {

            }

            @Override
            public void onPull(final float v) {
                int alpha = (int) ((1 - v) * 255);
                setPullBackBackgroundColor(Color.argb(alpha, 0, 0, 0));
            }

            @Override
            public void onPullCancel() {

            }

            @Override
            public void onPullComplete() {
                onBackClicked(null);
            }
        };
    }

    @Override
    public ObservableInt getPullBackBackgroundColor() {
        return mPullBackBackgroundColor;
    }

    @Override
    public int getDefaultIndex() {
        return mDefaultIndex;
    }

    @Override
    public PagerAdapter getAdapter() {
        return new PhotoGalleriesPagerAdapter(mContextReference.get(), mImageList);
    }

    @Override
    public ViewPager.OnPageChangeListener getPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                setIndexTotal(position);
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        };
    }

    @Override
    public ObservableField<String> getCurrentPhotoIndex() {
        return mCurrentPhotoIndex;
    }

    private void setIndexTotal(int position) {
        mCurrentPhotoIndex.set(mResources.getString(R.string.photo_gallery_index_total_text, position + 1, mImageList.size()));
    }

    private void setPullBackBackgroundColor(int color) {
        mPullBackBackgroundColor.set(color);
    }
}