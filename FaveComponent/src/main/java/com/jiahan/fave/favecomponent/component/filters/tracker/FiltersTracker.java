package com.jiahan.fave.favecomponent.component.filters.tracker;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zarea at 2020
 */
public interface FiltersTracker {

    void onTapReset();

    void onTapApplyFilter(final String sortBy,
                          final String rating,
                          final String priceRange,
                          final String distance,
                          final String type,
                          final String redeemable,
                          final String openingHours,
                          final HashMap<String, List<String>> labels);
}
