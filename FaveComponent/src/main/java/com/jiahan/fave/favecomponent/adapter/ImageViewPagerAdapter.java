package com.jiahan.fave.favecomponent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.jiahan.fave.core.common.PhotoGalleriesPagerAdapter;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.image.ImageItemViewModel;
import com.jiahan.fave.favecomponent.component.image.ImageItemViewModelImpl;
import com.jiahan.fave.favecomponent.component.image.tracker.ImageTracker;
import com.jiahan.fave.favecomponent.component.image.tracker.ImageTrackerImpl;
import com.jiahan.fave.favecomponent.databinding.ViewImageItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageViewPagerAdapter extends PhotoGalleriesPagerAdapter {
    private final Context      mContext;
    private final EventSender  mEventSender;
    private final String       mScreenIdentifier;
    private final String       mSectionName;
    private final List<String> mImageList;

    public ImageViewPagerAdapter(final Context context,
                                 final EventSender eventSender,
                                 final String screenIdentifier,
                                 final String sectionName,
                                 final List<String> imageList) {
        super(context, imageList);
        mContext = context;
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mSectionName = sectionName;
        mImageList = imageList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        ImageTracker imageTracker = new ImageTrackerImpl(mEventSender, mScreenIdentifier, position);
        final ImageItemViewModel viewModel = new ImageItemViewModelImpl(
                mContext,
                imageTracker,
                ViewGroup.MarginLayoutParams.MATCH_PARENT,
                ViewGroup.MarginLayoutParams.MATCH_PARENT,
                0,
                mSectionName,
                mImageList,
                position,
                mImageList.get(position));
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_image_item, container, false);
        ViewImageItemBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(viewModel);
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return view;
    }
}
