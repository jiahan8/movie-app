package com.jiahan.fave.favecomponent.component.eCard;

import android.content.Context;

import com.jiahan.fave.favecomponent.component.eCard.tracker.BaseECardTracker;
import com.jiahan.fave.favecomponent.entity.Company;
import com.jiahan.fave.favecomponent.entity.ECard;

import static com.jiahan.fave.core.utils.Utils.join;

public class ECardXLargeViewModelImpl extends ECardViewModelImpl implements ECardXLargeViewModel {
    private final Company eCardCompany;

    public ECardXLargeViewModelImpl(Context context,
                                    BaseECardTracker baseECardTracker,
                                    ECard eCard,
                                    long companyId,
                                    Company eCardCompany) {
        super(context, baseECardTracker, eCard, companyId);
        this.eCardCompany = eCardCompany;
    }

    @Override
    public String getTags() {
        if (eCardCompany.mTags == null || eCardCompany.mTags.isEmpty()) {
            return "";
        }
        return join(", ", eCardCompany.mTags.subList(0, Math.min(3, eCardCompany.mTags.size())));
    }
}
