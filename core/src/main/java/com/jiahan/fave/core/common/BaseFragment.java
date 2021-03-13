package com.jiahan.fave.core.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.jiahan.fave.core.BR;
import com.jiahan.fave.core.R;
import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.databinding.FragmentBaseBinding;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment {
    protected BaseActivity    mActivity;
    protected ViewDataBinding mBinding;

    protected abstract int getLayoutResource();

    protected abstract BaseViewViewModel getBaseViewViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        getInputData();
        FrameLayout rootView = (FrameLayout) getLayoutInflater().inflate(R.layout.fragment_base, container, false);
        FragmentBaseBinding baseBinding = DataBindingUtil.bind(rootView);
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutResource(), rootView.findViewById(R.id.viewStub), true);
        BaseViewViewModel viewModel = getBaseViewViewModel();
        mBinding.setVariable(BR.viewModel, viewModel);
        baseBinding.setViewModel(viewModel);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(@NotNull Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    protected void getInputData() {

    }
}