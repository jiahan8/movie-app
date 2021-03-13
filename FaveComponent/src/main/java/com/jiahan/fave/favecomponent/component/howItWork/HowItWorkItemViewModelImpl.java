package com.jiahan.fave.favecomponent.component.howItWork;

import android.text.TextUtils;

import com.jiahan.fave.favecomponent.entity.HowItWorkData;

public class HowItWorkItemViewModelImpl implements HowItWorkItemViewModel {
    private final HowItWorkData data;

    public HowItWorkItemViewModelImpl(HowItWorkData data) {
        this.data = data;
    }

    @Override
    public String getTitle() {
        return data.mTitle;
    }

    @Override
    public String getDesc() {
        return data.mDescription;
    }

    @Override
    public String getImageUrl() {
        return data.mImage;
    }

    @Override
    public boolean getImageVisibility() {
        return !TextUtils.isEmpty(data.mImage);
    }
}
