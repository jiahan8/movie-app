package com.jiahan.fave.favecomponent.component.deal;

import com.jiahan.fave.favecomponent.component.outlet.OutletViewModel;

public interface DealXLargeViewModel extends DealViewModel {
    OutletViewModel getOutletViewModel();

    String getWhatYouGetHtml();

    String getPurchaseTitle();

    boolean getPurchaseEnabled();
}