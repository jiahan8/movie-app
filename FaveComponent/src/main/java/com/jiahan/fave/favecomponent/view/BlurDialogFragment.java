package com.jiahan.fave.favecomponent.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jiahan.fave.core.common.BaseDialogFragment;
import com.jiahan.fave.core.utils.blur.BlurView;
import com.jiahan.fave.favecomponent.R;

import org.jetbrains.annotations.NotNull;

public abstract class BlurDialogFragment extends BaseDialogFragment {
    private BlurView mBlurView;

    @Nullable
    @Override
    public View onCreateView(@NotNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
        mBlurView = decorView.findViewById(R.id.blur_dialog_bg);
        if (mBlurView == null) {
            mBlurView = new BlurView(mActivity);
            mBlurView.setId(R.id.blur_dialog_bg);
            mBlurView.setAlpha(0f);
            decorView.addView(mBlurView, new ViewGroup.LayoutParams(-1, -1));
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mBlurView != null) {
            mBlurView.blur();
            mBlurView.show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mBlurView != null) {
            mBlurView.hide();
        }
    }
}