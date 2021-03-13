package com.jiahan.fave.favecomponent.component.review;

import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface RatingOverallViewModel extends RecyclerViewItemViewModel {
    Drawable getBackground();

    ObservableBoolean getRatingVisibility();

    ObservableField<Drawable> getRatingIcon();

    ObservableField<String> getRating();

    ObservableField<String> getRatingCount();

    LinearLayoutManager getLayoutManager();

    RecyclerViewAdapter getAdapter();
}