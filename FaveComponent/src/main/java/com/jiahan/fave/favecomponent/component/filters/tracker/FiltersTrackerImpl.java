package com.jiahan.fave.favecomponent.component.filters.tracker;


import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.favecomponent.entity.Product;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zarea at 2020
 */
public class FiltersTrackerImpl implements FiltersTracker{
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;
    private final String      mFromScreen;

    public FiltersTrackerImpl(final EventSender eventSender,
                              final String screenIdentifier,
                              final String fromScreen) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mFromScreen = fromScreen;
    }

    @Override
    public void onTapReset() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.FILTER_RESET, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.FROM_SCREEN_PAGE, mFromScreen);
        mEventSender.send(actions);
    }

    @Override
    public void onTapApplyFilter(final String sortBy,
                                 final String rating,
                                 final String priceRange,
                                 final String distance,
                                 final String type,
                                 final String redeemable,
                                 final String openingHours,
                                 final HashMap<String, List<String>> labels) {

        EventActions actions = mEventSender.customEvent(PropertyConstant.Name.FILTER_APPLIED, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.SORT_BY, sortBy)
                .addProperty(PropertyConstant.Key.RATING, rating)
                .addProperty(PropertyConstant.Key.PRICE_RANGE, priceRange)
                .addProperty(PropertyConstant.Key.DISTANCE, distance)
                .addProperty(PropertyConstant.Key.FROM_SCREEN_PAGE, mFromScreen);
        if(type.equals(Product.ProductType.DEAL.getValue())){
            actions.addProperty(PropertyConstant.Key.REDEEMABLE, redeemable);
        }else{
            actions.addProperty(PropertyConstant.Key.OPENING_HOURS, openingHours);
        }
        if(labels!= null && !labels.isEmpty()){
            for (HashMap.Entry<String, List<String>> entry : labels.entrySet()) {
                actions.addProperty(entry.getKey(), entry.getValue());
            }
        }
        mEventSender.send(actions);
    }
}