package com.jiahan.fave.core.common;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.jiahan.fave.core.BR;
import com.jiahan.fave.core.R;
import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.databinding.ActivityBaseBinding;

public abstract class BaseActivity extends AppCompatActivity {
    //    private   RevealAnimation   mRevealAnimation;
    protected BaseViewViewModel  mViewModel;
    protected ViewDataBinding mBinding;
    protected InputMethodManager mInputMethodManager;

    protected abstract int getLayoutResource();

    protected abstract void bindViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            try {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } catch (Exception ignored) {
            }
        }
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        getInputData();
        bindViewModel();
        initToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        mViewModel.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
//        mRevealAnimation.unRevealActivity();
    }


    protected void getInputData() {

    }

    protected void initBinding(BaseViewViewModel viewModel) {
        View rootView = getLayoutInflater().inflate(R.layout.activity_base, (ViewGroup) getWindow().getDecorView(), false);
        ActivityBaseBinding baseBinding = DataBindingUtil.bind(rootView);
        if (baseBinding == null) {
            return;
        }
//        mRevealAnimation = new RevealAnimation(rootView, getIntent(), this);
        int toolbarLayout = viewModel.getCustomToolbarLayout();
        if (toolbarLayout != 0) {
            ViewDataBinding tb = DataBindingUtil.inflate(getLayoutInflater(), toolbarLayout, rootView.findViewById(R.id.app_bar), true);
            tb.setVariable(BR.viewModel, viewModel);
        }
        int fabLayout = viewModel.getFABLayout();
        if (fabLayout != 0) {
            ViewDataBinding tb = DataBindingUtil.inflate(getLayoutInflater(), fabLayout, rootView.findViewById(R.id.coordinator_view), true);
            tb.setVariable(BR.viewModel, viewModel);
        }
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutResource(), rootView.findViewById(R.id.viewStub), true);
        mBinding.setVariable(BR.viewModel, viewModel);
        setContentView(rootView);
        baseBinding.setViewModel(viewModel);
        mViewModel = viewModel;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("");
    }

    public void hideKeyboard() {
        View view = getWindow().getCurrentFocus();
        if (view == null) {
            return;
        }
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        getWindow().getDecorView().clearFocus();
    }
}