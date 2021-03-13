package com.jiahan.fave.core.common;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.utils.Utils;

import org.jetbrains.annotations.NotNull;

public abstract class BaseDialogFragment extends AppCompatDialogFragment {
    protected com.jiahan.fave.core.common.BaseActivity mActivity;
    protected View         mContentView;

    protected abstract int getLayoutResource();

    @Nullable
    @Override
    public View onCreateView(@NotNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        final Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            final Window window = dialog.getWindow();
            if (window != null) {
                window.getAttributes().windowAnimations = R.style.SlideDialogAnimation;
            }
        }
        mContentView = inflater.inflate(getLayoutResource(), container, false);
        return mContentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        final Dialog dialog = getDialog();
        if (dialog != null && mActivity != null) {
            int width = Utils.getScreenDimension(mActivity).widthPixels - ((int) getResources().getDimension(R.dimen.margin_huge) * 2);
            dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);
    }

    @Override
    public void onAttach(@NotNull Activity activity) {
        super.onAttach(activity);
        mActivity = (com.jiahan.fave.core.common.BaseActivity) activity;
    }
}