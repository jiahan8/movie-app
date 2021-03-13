package com.jiahan.fave.favecomponent.component.label;

import com.jiahan.fave.favecomponent.entity.CompanyLabel;

import static com.jiahan.fave.core.utils.Utils.join;

public class CompanyLabelItemViewModelImpl implements CompanyLabelItemViewModel {
    protected final CompanyLabel mCompanyLabel;
    public CompanyLabelItemViewModelImpl(final CompanyLabel companyLabel) {
        mCompanyLabel = companyLabel;
    }

    @Override
    public String getName() {
        return mCompanyLabel.name;
    }

    @Override
    public String getValue() {
        return join(", ", mCompanyLabel.value);
    }
}
