package com.jiahan.fave.favecomponent.component.openingHour;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface OpeningHourItemModel extends RecyclerViewItemViewModel {
    String getDay();

    String getHour();

    boolean getIsToday();

    int getTextColor();
}