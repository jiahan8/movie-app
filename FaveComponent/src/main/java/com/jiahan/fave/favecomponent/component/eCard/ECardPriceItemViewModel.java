package com.jiahan.fave.favecomponent.component.eCard;

import androidx.databinding.ObservableBoolean;

public interface ECardPriceItemViewModel extends ECardViewModel{
    ObservableBoolean isSelected();

    void updateSelectedStatus(long selectedEcardId);
}
