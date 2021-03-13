package com.jiahan.fave.favecomponent.component.eCard;

import android.content.Context;

import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.eCard.tracker.BaseECardTracker;
import com.jiahan.fave.favecomponent.entity.ECard;

public class ECardViewModelImpl extends BaseECardViewModelImpl implements ECardViewModel {
    private final ECard mECard;

    public ECardViewModelImpl(Context context, BaseECardTracker baseECardTracker, ECard eCard, long companyId) {
        super(context, baseECardTracker, eCard, companyId);
        mECard = eCard;
    }

    @Override
    public String getValidity() {
        return mResources.getString(R.string.ecard_validity, mECard.validity);
    }

    @Override
    public boolean isMostPopular() {
        return mECard.mostPopular;
    }
}