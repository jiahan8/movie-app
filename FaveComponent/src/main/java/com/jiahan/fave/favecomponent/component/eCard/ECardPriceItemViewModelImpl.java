package com.jiahan.fave.favecomponent.component.eCard;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableBoolean;

import com.jiahan.fave.core.callback.OnItemClickListener;
import com.jiahan.fave.favecomponent.component.eCard.tracker.BaseECardTracker;
import com.jiahan.fave.favecomponent.entity.ECard;

public class ECardPriceItemViewModelImpl extends ECardViewModelImpl implements ECardPriceItemViewModel {
    private final OnItemClickListener<ECard> mItemClickedListener;
    private final ECard mECard;
    private final ObservableBoolean isSelected = new ObservableBoolean(false);

    public ECardPriceItemViewModelImpl(Context context,
                                       BaseECardTracker baseECardTracker,
                                       ECard eCard,
                                       long companyId,
                                       OnItemClickListener<ECard> itemClickedListener) {
        super(context, baseECardTracker, eCard, companyId);
        mItemClickedListener = itemClickedListener;
        mECard = eCard;
    }

    @Override
    public ObservableBoolean isSelected() {
        return isSelected;
    }

    @Override
    public void onItemClicked(View view) {
        mItemClickedListener.onItemClick(mECard);
    }

    @Override
    public void updateSelectedStatus(long selectedEcardId) {
        isSelected.set(selectedEcardId == mECard.mId);
    }
}
