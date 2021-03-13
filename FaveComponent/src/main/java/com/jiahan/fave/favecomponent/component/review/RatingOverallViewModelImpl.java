package com.jiahan.fave.favecomponent.component.review;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.utils.drawableUtils.DrawableUtils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.adapter.RatingCategoryListAdapterDelegate;
import com.jiahan.fave.favecomponent.entity.RatingOverall;

public class RatingOverallViewModelImpl implements RatingOverallViewModel {
    private final Context           mContext;
    private final BaseViewViewModel mViewViewModel;
    private final RatingOverall     mRatingOverall;

    private final ObservableBoolean mRatingVisibility = new ObservableBoolean();
    private final ObservableField<Drawable> mRatingIcon       = new ObservableField<>();
    private final ObservableField<String> mRating           = new ObservableField<>();
    private final ObservableField<String> mRatingCount      = new ObservableField<>();
    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModelList    = new ObservableArrayList<>();

    public RatingOverallViewModelImpl(final Context context,
                                      final BaseViewViewModel viewViewModel,
                                      final RatingOverall ratingOverall) {
        mContext = context;
        mViewViewModel = viewViewModel;
        mRatingOverall = ratingOverall;
        mRatingVisibility.set(mRatingOverall.mOverall > 0);
        mRating.set(String.valueOf(mRatingOverall.mOverall));
        mRatingIcon.set(AppCompatResources.getDrawable(context, getRatingVisibility().get() ?
                R.drawable.ic_star_filled_with_margin :
                R.drawable.ic_star_empty_with_margin));
        createRatingCategoryViews(context);
        if (mRatingVisibility.get()) {
            mRatingCount.set(context.getResources().getQuantityString(R.plurals.ratings,
                    mRatingOverall.mTotalCount,
                    mRatingOverall.mTotalCount));
            return;
        }
        mRatingCount.set(context.getString(R.string.no_ratings));
    }

    @Override
    public Drawable getBackground() {
        return DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.size_12,
                R.dimen.size_1,
                R.color.very_light_pink_eight,
                R.color.white);
    }

    @Override
    public ObservableBoolean getRatingVisibility() {
        return mRatingVisibility;
    }

    @Override
    public ObservableField<Drawable> getRatingIcon() {
        return mRatingIcon;
    }

    @Override
    public ObservableField<String> getRating() {
        return mRating;
    }

    @Override
    public ObservableField<String> getRatingCount() {
        return mRatingCount;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        return mViewViewModel.createRecyclerViewAdapter(mViewModelList, new RatingCategoryListAdapterDelegate(inflater));
    }

    private void createRatingCategoryViews(Context context) {
        mViewModelList.clear();
        RatingCategoryItemViewModel viewModel;
        viewModel = new RatingCategoryItemViewModelImpl(context.getString(R.string.review_service), mRatingOverall.mService);
        mViewModelList.add(viewModel);
        viewModel = new RatingCategoryItemViewModelImpl(context.getString(R.string.review_ambience), mRatingOverall.mAmbience);
        mViewModelList.add(viewModel);
        viewModel = new RatingCategoryItemViewModelImpl(context.getString(R.string.review_food), mRatingOverall.mFood);
        mViewModelList.add(viewModel);
        viewModel = new RatingCategoryItemViewModelImpl(context.getString(R.string.review_value_for_money), mRatingOverall.mValueForMoney);
        mViewModelList.add(viewModel);
    }
}