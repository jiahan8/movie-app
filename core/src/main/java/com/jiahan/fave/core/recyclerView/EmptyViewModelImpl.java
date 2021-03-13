package com.jiahan.fave.core.recyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

public class EmptyViewModelImpl implements EmptyViewModel {
    protected final Context mContext;
    private final   Integer mIconResource;
    private final   String  mTitle;
    private final   String  mDescription;
    private final   String  mActionText;
    private final   boolean mShowArrow;

    public EmptyViewModelImpl(@NonNull final Context context,
                              @Nullable @DrawableRes final Integer iconResource,
                              @Nullable final String title,
                              @Nullable final String description,
                              @Nullable final String actionText,
                              final boolean showArrow) {
        mContext = context;
        mIconResource = iconResource;
        mTitle = title;
        mDescription = description;
        mActionText = actionText;
        mShowArrow = showArrow;
    }

    @Override
    public Drawable getIcon() {
        return getIconVisibility() ? AppCompatResources.getDrawable(mContext, mIconResource) : null;
    }

    @Override
    public boolean getIconVisibility() {
        return mIconResource != null && mIconResource > 0;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public boolean getTitleVisibility() {
        return !TextUtils.isEmpty(getTitle());
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public boolean getDescriptionVisibility() {
        return !TextUtils.isEmpty(getDescription());
    }

    @Override
    public String getActionText() {
        return mActionText;
    }

    @Override
    public boolean getActionVisibility() {
        return !TextUtils.isEmpty(getActionText());
    }

    @Override
    public boolean getActionArrowVisibility() {
        return mShowArrow;
    }

    @Override
    public void onActionClicked(final View view) {

    }
}