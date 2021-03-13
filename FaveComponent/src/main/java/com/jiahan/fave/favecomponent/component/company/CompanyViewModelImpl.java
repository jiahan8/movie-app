package com.jiahan.fave.favecomponent.component.company;

import android.text.TextUtils;

import com.jiahan.fave.favecomponent.entity.Company;

import static com.jiahan.fave.core.utils.Utils.join;

public class CompanyViewModelImpl extends BaseCompanyViewModelImpl implements CompanyViewModel {
    private final Company mCompany;

    public CompanyViewModelImpl(final Company company) {
        super(company);
        mCompany = company;
    }

    @Override
    public String getEstablishmentLabel() {
        return join(", ", mCompany.mEstablishmentList);
    }

    @Override
    public boolean getEstablishmentLabelVisibility() {
        return !TextUtils.isEmpty(getEstablishmentLabel());
    }

    @Override
    public String getCuisineLabel() {
        return join(", ", mCompany.mCuisineList);
    }

    @Override
    public boolean getCuisineLabelVisibility() {
        return !TextUtils.isEmpty(getCuisineLabel());
    }

    @Override
    public String getFoodLabel() {
        return join(", ", mCompany.mFoodList);
    }

    @Override
    public boolean getFoodLabelVisibility() {
        return !TextUtils.isEmpty(getFoodLabel());
    }
}