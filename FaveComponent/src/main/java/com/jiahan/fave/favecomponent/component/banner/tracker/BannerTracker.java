package com.jiahan.fave.favecomponent.component.banner.tracker;

import androidx.annotation.NonNull;

public interface BannerTracker {
    void onTapBanner();

    void setCategory(@NonNull final String category,
                     @NonNull final String product,
                     @NonNull final String appFilter,
                     @NonNull final String location,
                     final String appliedSorting,
                     final boolean appliedFilter);
}