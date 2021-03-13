package com.jiahan.fave.core.feature.common;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.content.res.AppCompatResources;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.callback.ButtonClickedListener;
import com.jiahan.fave.core.common.BaseDialogFragment;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.core.utils.drawableUtils.DrawableUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageDialogViewModelImpl implements MessageDialogViewModel {
    private final BaseDialogFragment mFragment;
    private final int                mIconResource;
    private final String             mTitle;
    private final String             mMessage;
    private final String             mPositiveButtonText;
    private final String             mNegativeButtonText;

    public MessageDialogViewModelImpl(final BaseDialogFragment fragment,
                                      final int IconResource,
                                      final String title,
                                      final String message,
                                      final String positiveButtonText,
                                      final String negativeButtonText) {
        mFragment = fragment;
        mIconResource = IconResource;
        mTitle = title;
        mMessage = message;
        mPositiveButtonText = positiveButtonText;
        mNegativeButtonText = negativeButtonText;
    }

    @Override
    public Drawable getBackground() {
        return DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.size_8,
                0,
                0,
                R.color.white);
    }

    @Override
    public Drawable getResourceIcon() {
        if (getResourceIconVisibility()) {
            return AppCompatResources.getDrawable(mFragment.getActivity(), mIconResource);
        }
        return null;
    }

    @Override
    public boolean getResourceIconVisibility() {
        return mIconResource != 0;
    }

    @Override
    public String getTitle() {
        return getTitleVisibility() ? mTitle : null;
    }

    @Override
    public boolean getTitleVisibility() {
        return !TextUtils.isEmpty(mTitle);
    }

    @Override
    public Spannable getMessage() {
        return new SpannableString(getWebViewVisibility() ? "" : mMessage);
    }

    @Override
    public Drawable getMessageBackground() {
        return DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.static_size_3,
                0,
                0,
                R.color.white);
    }

    @Override
    public boolean getWebViewVisibility() {
        Pattern pattern = Pattern.compile(Constant.HTML_PATTERN);
        Matcher matcher = pattern.matcher(mMessage);
        return matcher.find();
    }

    @Override
    public Drawable getPositiveBackground() {
        Drawable drawable = DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.static_size_3,
                0,
                0,
                R.color.lipstick);
        return DrawableUtils.createRippleDrawable(drawable);
    }

    @Override
    public String getPositiveButtonText() {
        return mPositiveButtonText;
    }

    @Override
    public void onPositiveClicked(final View view) {
        if (mFragment == null) {
            return;
        }
        ButtonClickedListener listener = ((MessageDialogFragment) mFragment).getPositiveButtonListener();
        if (listener != null) {
            listener.onButtonClicked();
            return;
        }
        mFragment.dismiss();
    }

    @Override
    public boolean getPositiveVisibility() {
        return !TextUtils.isEmpty(getPositiveButtonText());
    }

    @Override
    public Drawable getNegativeBackground() {
        Drawable drawable = DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.static_size_3,
                R.dimen.static_size_1,
                R.color.lipstick,
                R.color.white);
        return DrawableUtils.createRippleDrawable(drawable);
    }

    @Override
    public String getNegativeButtonText() {
        return mNegativeButtonText;
    }

    @Override
    public boolean getNegativeVisibility() {
        return !TextUtils.isEmpty(getNegativeButtonText());
    }

    @Override
    public void onNegativeClicked(final View view) {
        if (mFragment == null) {
            return;
        }
        ButtonClickedListener listener = ((MessageDialogFragment) mFragment).getNegativeButtonListener();
        if (listener != null) {
            listener.onButtonClicked();
            return;
        }
        mFragment.dismiss();
    }

}
