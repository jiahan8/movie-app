package com.jiahan.fave.favecomponent.component.deal.tracker;

import androidx.annotation.NonNull;

import com.jiahan.fave.favecomponent.entity.Assortment;

public interface BaseDealTracker {
    void onTapDeal();

    void setAssortment(@NonNull Assortment assortment);

    void setCategory(@NonNull final String category,
                     @NonNull final String product,
                     @NonNull final String appFilter,
                     @NonNull final String location,
                     final String appliedSorting,
                     final boolean appliedFilter);
}