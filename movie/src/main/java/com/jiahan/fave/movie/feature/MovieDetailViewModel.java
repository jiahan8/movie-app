package com.jiahan.fave.movie.feature;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;

import com.jiahan.fave.core.callback.BaseViewViewModel;

public interface MovieDetailViewModel extends BaseViewViewModel {

    void onItemClicked(View view);

    ObservableField<String> getMovieImage();

    ObservableField<String> getMovieTitle();

    ObservableFloat getMoviePopularity();

    ObservableField<String> getMovieOverview();

    ObservableField<String> getMovieGenres();

    ObservableField<String> getMovieOriginalLanguage();

    ObservableInt getMovieRuntime();

}
