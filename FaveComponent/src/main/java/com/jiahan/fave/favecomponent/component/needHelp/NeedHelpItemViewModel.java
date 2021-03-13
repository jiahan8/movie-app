package com.jiahan.fave.favecomponent.component.needHelp;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface NeedHelpItemViewModel extends RecyclerViewItemViewModel {
    void onTalkToUsClicked(View v);

    Drawable getTalkToUsBackground();
}
