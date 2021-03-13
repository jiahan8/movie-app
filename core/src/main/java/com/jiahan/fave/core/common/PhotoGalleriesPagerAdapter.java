package com.jiahan.fave.core.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.databinding.ViewPhotoGalleriesItemBinding;
import com.jiahan.fave.core.feature.common.PhotoGalleriesItemViewModel;
import com.jiahan.fave.core.feature.common.PhotoGalleriesItemViewModelImpl;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PhotoGalleriesPagerAdapter extends PagerAdapter {
    private final Context      mContext;
    private final List<String> mImageList;

    public PhotoGalleriesPagerAdapter(final Context context,
                                      final List<String> imageList) {
        mContext = context;
        mImageList = imageList;
    }

    @Override
    public int getCount() {
        return mImageList != null ? mImageList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NotNull final View view, @NotNull final Object object) {
        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        final PhotoGalleriesItemViewModel viewModel = new PhotoGalleriesItemViewModelImpl(mImageList.get(position));
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_photo_galleries_item, container, false);
        ViewPhotoGalleriesItemBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(viewModel);
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        container.removeView((View) object);
    }
}