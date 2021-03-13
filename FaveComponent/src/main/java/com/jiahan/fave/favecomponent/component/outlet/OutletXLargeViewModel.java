package com.jiahan.fave.favecomponent.component.outlet;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface OutletXLargeViewModel extends OutletViewModel {
    void onBusinessHourClicked(View view);

    void onPayClicked(View view);

    boolean getOutletHourAvailable();

    String getPayCount();

    boolean getPeopleCountLabelVisibility();

    String getOutletOpenHour();

    String getOutletCloseHour();

    Drawable getPayBackground();
}