package com.jiahan.fave.favecomponent.component.filters;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import androidx.databinding.ObservableBoolean;

import com.jiahan.fave.core.utils.drawableUtils.DrawableUtils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.entity.FilterData;


public abstract class FiltersSingleChoiceItemViewModelImpl implements FiltersSingleChoiceItemViewModel {
    protected final FilterData mData;

    private final ObservableBoolean mSelected = new ObservableBoolean();

    public FiltersSingleChoiceItemViewModelImpl(final boolean selected,
                                                final FilterData data) {
        mSelected.set(selected);
        mData = data;
    }

    @Override
    public Drawable getBackground() {
        Drawable unselected = DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.size_12,
                R.dimen.static_size_1,
                R.color.very_light_pink_eight,
                R.color.white);
        Drawable selected = DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.size_12,
                R.dimen.size_1,
                R.color.pastel_pink,
                R.color.pastel_pink);
        return DrawableUtils.createDrawableSelector(false, unselected, selected);
    }

    @Override
    public ColorStateList getTextColor() {
        return DrawableUtils.createTextColorSelector(false, R.color.brownish_grey_four, R.color.lipstick);
    }

    @Override
    public String getName() {
        return mData.mDisplayLabel;
    }

    @Override
    public ObservableBoolean getSelected() {
        return mSelected;
    }

    @Override
    public void updateSelected(final boolean selected) {
        mSelected.set(selected);
    }

    @Override
    public String getKey() {
        return mData.mKey;
    }

    @Override
    public FilterData getData() {
        return mData;
    }
}