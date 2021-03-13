package com.jiahan.fave.favecomponent.component.review;

public class RatingCategoryItemViewModelImpl implements RatingCategoryItemViewModel {
    private final String mName;
    private final int    mRating;

    public RatingCategoryItemViewModelImpl(final String name, final int rating) {
        mName = name;
        mRating = rating;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public int getRatingValue() {
        return mRating;
    }
}