package com.jiahan.fave.core.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.feature.common.WebViewHtmlViewModel;
import com.jiahan.fave.core.feature.common.WebViewHtmlViewModelImpl;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.ScreenIdentifier;

public class WebViewHtmlActivity extends BaseActivity {
    private static final String EXTRA_PREFIX = com.jiahan.fave.core.common.WebViewHtmlActivity.class.getName() + ".";
    private static final String EXTRA_HTML   = EXTRA_PREFIX + "HTML";
    private static final String EXTRA_TITLE  = EXTRA_PREFIX + "TITLE";

    private String mTitle;
    private String mHtml;

    public static void start(Context context, String title, String html) {
        Intent starter = new Intent(context, com.jiahan.fave.core.common.WebViewHtmlActivity.class);
        starter.putExtra(EXTRA_HTML, html);
        starter.putExtra(EXTRA_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_web_view_html;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInputData();
    }

    @Override
    protected void getInputData() {
        super.getInputData();
        mHtml = getIntent().getStringExtra(EXTRA_HTML);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);
    }

    @Override
    protected void bindViewModel() {
        WebViewHtmlViewModel viewModel = new WebViewHtmlViewModelImpl(
                this,
                ScreenIdentifier.SCREEN_HTML,
                new EventSender(this),
                mTitle,
                mHtml);
        initBinding(viewModel);
        viewModel.setToolbarTitle(mTitle);
    }
}