package com.jiahan.fave.favecomponent.component.sectionHeader;

import com.jiahan.fave.favecomponent.component.seeMore.HorizontalSeeMoreItemViewModel;

public interface SectionHeaderViewModel extends HorizontalSeeMoreItemViewModel {
    String getTitle();

    String getDescription();

    boolean getDescriptionVisibility();

    boolean getSeeMoreVisibility();
}