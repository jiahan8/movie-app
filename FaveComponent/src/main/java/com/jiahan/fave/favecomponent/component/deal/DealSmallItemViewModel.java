package com.jiahan.fave.favecomponent.component.deal;

import com.jiahan.fave.favecomponent.component.outlet.OutletSmallItemViewModel;

public interface DealSmallItemViewModel extends DealViewModel {
    OutletSmallItemViewModel getOutletViewModel();

    boolean getDividerVisibility();
}