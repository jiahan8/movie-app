package com.jiahan.fave.favecomponent.component.company;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface BaseCompanyViewModel extends RecyclerViewItemViewModel {
    String getLogo();

    String getCompanyName();

    String getRating();

    String getDescription();

    boolean getDescriptionVisibility();

    String getTagsLabel();

    boolean getTagsLabelVisibility();

    String getPriceRange();

    boolean getPriceRangeVisibility();

    String getPartnerCashback();

    boolean getPartnerCashbackVisibility();

    boolean getRatingVisibility();
}