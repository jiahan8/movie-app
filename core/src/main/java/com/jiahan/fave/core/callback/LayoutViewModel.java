package com.jiahan.fave.core.callback;

import com.jiahan.fave.core.widget.PullBackLayout;

public interface LayoutViewModel {
    PullBackLayout.OnPullListener getOnPullListener();

    int getMarginTop();
}