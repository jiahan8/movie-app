package com.jiahan.fave.favecomponent.component.assortment.tracker;

import androidx.annotation.NonNull;

public interface AssortmentTracker {
    void onTapAssortment();

    void setLocation(@NonNull final String location);

    void setCategory(@NonNull final String category,
                     @NonNull final String product,
                     @NonNull final String appFilter,
                     @NonNull final String location,
                     final String appliedSorting,
                     final boolean appliedFilter);
}