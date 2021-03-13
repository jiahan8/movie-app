package com.jiahan.fave.favecomponent.component.eCard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.jiahan.fave.core.common.CoreApplication;
import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.core.utils.drawableUtils.DrawableUtils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.eCard.tracker.BaseECardTracker;
import com.jiahan.fave.favecomponent.entity.BaseECard;

public class BaseECardViewModelImpl implements BaseECardViewModel {
    private final Context          mContext;
    protected final Resources        mResources;
    private final BaseECardTracker mBaseECardTracker;
    private final BaseECard        mECard;
    private final long             mCompanyId;
    private final int              mWidth;

    public BaseECardViewModelImpl(final Context context,
                                  final BaseECardTracker baseECardTracker,
                                  final BaseECard eCard,
                                  final long companyId) {
        mContext = context;
        mResources = context.getResources();
        mBaseECardTracker = baseECardTracker;
        mECard = eCard;
        mCompanyId = companyId;
        mWidth = 0;
    }

    public BaseECardViewModelImpl(final Context context,
                                  final BaseECardTracker baseECardTracker,
                                  final BaseECard eCard,
                                  final long companyId,
                                  final int width) {
        mContext = context;
        mResources = context.getResources();
        mBaseECardTracker = baseECardTracker;
        mECard = eCard;
        mCompanyId = companyId;
        mWidth = width;
    }

    @Override
    public void onItemClicked(final View view) {
        mBaseECardTracker.onTapECard();
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.ECard.getECardDetailActivityIntent(view.getContext(), mECard.mId, mCompanyId));
    }

    @Override
    public int getWidth() {
        if (mWidth > 0) {
            return mWidth;
        }
        return Utils.getScreenDimension(mContext).widthPixels - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.margin_large_extra);
    }

    @Override
    public Drawable getCardBackground() {
        Drawable gradientBackground = DrawableUtils.createGradientRadialDrawable(
                GradientDrawable.Orientation.TL_BR,
                GradientDrawable.RADIAL_GRADIENT,
                R.dimen.margin_large,
                getWidth(),
                1,
                1,
                ContextCompat.getColor(CoreApplication.getInstance(), isLightTheme() ? R.color.white_25 : R.color.black_25),
                ContextCompat.getColor(CoreApplication.getInstance(), R.color.transparent));
        final int inset = 1;
        Drawable blackInsetBackground = DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.margin_large,
                0,
                0,
                R.color.black_20);
        Drawable whiteInsetDrawable = DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.margin_large,
                0,
                0,
                R.color.white_20);
        Drawable whiteInsetBackground = new InsetDrawable(whiteInsetDrawable, 0, 0, inset, inset);
        int color = TextUtils.isEmpty(mECard.mBackgroundColor) ?
                ResourcesCompat.getColor(mResources, R.color.light_black, null) :
                Color.parseColor(mECard.mBackgroundColor);
        Drawable colorDrawable = DrawableUtils.createDrawable(GradientDrawable.RECTANGLE,
                R.dimen.margin_large,
                0,
                0,
                color);
        Drawable colorInsetBackground = new InsetDrawable(colorDrawable, inset);
        return new LayerDrawable(new Drawable[]{blackInsetBackground, whiteInsetBackground, colorInsetBackground, gradientBackground});
    }

    @Override
    public String getLogo() {
        return mECard.mCompanyLogo;
    }

    @Override
    public String getCompanyName() {
        return mECard.mCompanyName;
    }

    @Override
    public String getBonus() {
        return mECard.mBonus;
    }

    @Override
    public Drawable getBonusBackground() {
        return DrawableUtils.createDrawable(GradientDrawable.RECTANGLE,
                R.dimen.size_36,
                0,
                0,
                R.color.colorPrimary);
    }

    @Override
    public boolean getBonusVisibility() {
        return !TextUtils.isEmpty(mECard.mBonus);
    }

    @Override
    public String getBoughtCount() {
        return mResources.getString(R.string.bought, mECard.mSoldCount);
    }

    @Override
    public boolean getSoldCountVisibility() {
        return mECard.mSoldCount > 0;
    }

    @Override
    public String getSellingPrice() {
        return mECard.mPayableAmount;
    }

    @Override
    public String getValue() {
        return mECard.mValue;
    }

    @Override
    public Drawable getDividerBackground() {
        return DrawableUtils.createDrawable(GradientDrawable.RECTANGLE,
                0,
                0,
                0,
                isLightTheme() ? R.color.white : R.color.brown_grey_two);
    }

    @Override
    public int getTextColor() {
        return ResourcesCompat.getColor(mResources, isLightTheme() ? R.color.white : R.color.black, null);
    }

    @Override
    public int getLabelColor() {
        return ResourcesCompat.getColor(mResources, isLightTheme() ? R.color.white : R.color.brown_grey_two, null);
    }

    private boolean isLightTheme() {
        return BaseECard.Theme.LIGHT.equals(mECard.mTheme);
    }
}