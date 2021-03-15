package com.jiahan.fave.movie.di;

import com.jiahan.fave.favecomponent.di.FaveComponent;
import com.jiahan.fave.movie.feature.MovieDetailActivity;
import com.jiahan.fave.movie.feature.MovieDetailFragment;
import com.jiahan.fave.movie.feature.MovieListActivity;
import com.jiahan.fave.movie.feature.MovieWebViewActivity;

import dagger.Component;

@MovieScope
@Component(dependencies = {FaveComponent.class})
public interface MovieComponent {
    void inject(MovieListActivity movieListActivity);

    void inject(MovieDetailActivity movieDetailActivity);

    void inject(MovieDetailFragment movieDetailFragment);

    void inject(MovieWebViewActivity movieWebViewActivity);
}
