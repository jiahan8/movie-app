package com.jiahan.fave.favecomponent.component.sectionHeader;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jiahan.fave.favecomponent.component.seeMore.HorizontalSeeMoreItemViewModelImpl;

public class SectionHeaderViewModelImpl extends HorizontalSeeMoreItemViewModelImpl implements SectionHeaderViewModel {
    private final String mTitle;
    private final String mDescription;

    public SectionHeaderViewModelImpl(@NonNull final String title,
                                      @Nullable final String description,
                                      @Nullable final View.OnClickListener onClickListener) {
        super(onClickListener);
        mTitle = title;
        mDescription = description;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public boolean getDescriptionVisibility() {
        return !TextUtils.isEmpty(getDescription());
    }


    @Override
    public boolean getSeeMoreVisibility() {
        return getOnClickListener() != null;
    }
}