package com.jiahan.fave.favecomponent.component.review;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.entity.Review;

public class ReviewItemViewModelImpl implements ReviewItemViewModel {
    private final Resources mResources;
    private final Review    mReview;

    public ReviewItemViewModelImpl(final Context context, final Review review) {
        mResources = context.getResources();
        mReview = review;
    }

    @Override
    public int getRatingValue() {
        return (int) mReview.mRating;
    }

    @Override
    public String getDate() {
        return mReview.mReviewedTimeStamp;
        /*try {
            long timestamp = DateTimeUtils.parseRFC3339DateTime(mReview.mReviewedTimeStamp);
            CharSequence date = DateUtils.getRelativeTimeSpanString(
                    timestamp,
                    Calendar.getInstance().getTimeInMillis(),
                    DateUtils.MINUTE_IN_MILLIS);
            return date.toString();
        } catch (Exception ex) {
            return "";
        }*/
    }

    @Override
    public String getComment() {
        return mReview.mComment;
    }

    @Override
    public boolean getCommentVisibility() {
        return !TextUtils.isEmpty(getComment());
    }

    @Override
    public String getName() {
        return mReview.mName;
    }

    @Override
    public String getCompanyReplied() {
        return mResources.getString(R.string.review_shop_response, mReview.mCompanyReplied);
    }

    @Override
    public boolean getCompanyRepliedVisibility() {
        return !TextUtils.isEmpty(mReview.mCompanyReplied);
    }
}