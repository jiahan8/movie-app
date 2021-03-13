package com.jiahan.fave.favecomponent.component.openingHour;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.entity.OutletBusinessHour;

import static com.jiahan.fave.core.utils.Utils.join;

public class OpeningHourItemModelImpl implements OpeningHourItemModel {
    private final Context            mContext;
    private final OutletBusinessHour mOutletBusinessHour;

    public OpeningHourItemModelImpl(final Context context,
                                    final OutletBusinessHour outletBusinessHour) {
        mContext = context;
        mOutletBusinessHour = outletBusinessHour;
    }

    @Override
    public String getDay() {
        return mOutletBusinessHour.mDay.substring(0, 1).toUpperCase() + mOutletBusinessHour.mDay.substring(1);
    }

    @Override
    public String getHour() {
        if (!mOutletBusinessHour.mIsOpen) {
            return mContext.getString(R.string.closed);
        }
        return join("\n", mOutletBusinessHour.mHours);
    }

    @Override
    public boolean getIsToday() {
        return mOutletBusinessHour.mIsToday;
    }

    @Override
    public int getTextColor() {
        return ContextCompat.getColor(mContext, getIsToday() ? R.color.colorPrimary : R.color.light_black);
    }
}