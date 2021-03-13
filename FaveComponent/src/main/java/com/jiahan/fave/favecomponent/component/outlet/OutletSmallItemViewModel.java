package com.jiahan.fave.favecomponent.component.outlet;

import com.jiahan.fave.favecomponent.component.company.BaseCompanyViewModel;

public interface OutletSmallItemViewModel extends BaseOutletViewModel {
    boolean getDynamicCashbackVisibility();

    String getDynamicCashbackTime();

    BaseCompanyViewModel getCompanyViewModel();

    boolean getDividerVisibility();
}