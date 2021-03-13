package com.jiahan.fave.favecomponent.component.company;

import android.text.TextUtils;

import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.favecomponent.entity.BaseCompany;

import static com.jiahan.fave.core.utils.Utils.join;

public class BaseCompanyViewModelImpl implements BaseCompanyViewModel {
    private final BaseCompany mBaseCompany;

    public BaseCompanyViewModelImpl(final BaseCompany baseCompany) {
        mBaseCompany = baseCompany;
    }

    @Override
    public String getLogo() {
        return mBaseCompany.mLogo;
    }

    @Override
    public String getCompanyName() {
        return mBaseCompany.mName;
    }

    @Override
    public String getRating() {
        return String.valueOf(mBaseCompany.mRating);
    }

    @Override
    public String getDescription() {
        return mBaseCompany.mDescription;
    }

    @Override
    public boolean getDescriptionVisibility() {
        return !TextUtils.isEmpty(getDescription());
    }

    @Override
    public String getTagsLabel() {
        return join(", ", mBaseCompany.mTags != null && mBaseCompany.mTags.size() > 4 ? mBaseCompany.mTags.subList(0, Constant.COMPANY_MAX_TAGS) : mBaseCompany.mTags);
    }

    @Override
    public boolean getTagsLabelVisibility() {
        return !TextUtils.isEmpty(getTagsLabel());
    }

    @Override
    public String getPriceRange() {
        return mBaseCompany.mPriceRange;
    }

    @Override
    public boolean getPriceRangeVisibility() {
        return !TextUtils.isEmpty(getPriceRange());
    }

    @Override
    public String getPartnerCashback() {
        return mBaseCompany.mPartnerCashback;
    }

    @Override
    public boolean getPartnerCashbackVisibility() {
        return !TextUtils.isEmpty(getPartnerCashback());
    }

    @Override
    public boolean getRatingVisibility() {
        return mBaseCompany.mRating > 0;
    }
}