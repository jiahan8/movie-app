package com.jiahan.fave.favecomponent.component.eCard.tracker;

import androidx.annotation.NonNull;

public interface BaseECardTracker {
    void onTapECard();

    void setCategory(@NonNull final String category,
                     @NonNull final String appFilter,
                     @NonNull final String location,
                     final String appliedSorting,
                     final boolean appliedFilter);
}