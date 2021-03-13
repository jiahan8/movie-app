package com.jiahan.fave.favecomponent.component.movie;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface MovieItemViewModel extends RecyclerViewItemViewModel {

    void onItemClicked(View view);

    String getMovieImage();

    String getMovieTitle();

    float getMoviePopularity();

}