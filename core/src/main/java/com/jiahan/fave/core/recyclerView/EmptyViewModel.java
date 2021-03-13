package com.jiahan.fave.core.recyclerView;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface EmptyViewModel extends RecyclerViewItemViewModel {
    Drawable getIcon();

    boolean getIconVisibility();

    String getTitle();

    boolean getTitleVisibility();

    String getDescription();

    boolean getDescriptionVisibility();

    String getActionText();

    boolean getActionVisibility();

    boolean getActionArrowVisibility();

    void onActionClicked(final View view);
}