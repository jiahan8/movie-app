package com.jiahan.fave.favecomponent.component.review;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface ReviewItemViewModel extends RecyclerViewItemViewModel {
    int getRatingValue();

    String getDate();

    String getComment();

    boolean getCommentVisibility();

    String getName();

    String getCompanyReplied();

    boolean getCompanyRepliedVisibility();
}