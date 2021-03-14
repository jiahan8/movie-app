package com.jiahan.fave.movie.di;

import com.jiahan.fave.favecomponent.FaveCoreApplication;
import com.jiahan.fave.movie.feature.MovieDetailActivity;
import com.jiahan.fave.movie.feature.MovieDetailFragment;
import com.jiahan.fave.movie.feature.MovieListActivity;

public class Injector {
    public static void inject(MovieListActivity activity) {
        DaggerMovieComponent.builder()
                .faveComponent( ((FaveCoreApplication) FaveCoreApplication.getInstance()).getFaveComponent() )
                .build()
                .inject(activity);
    }
    public static void inject(MovieDetailActivity activity) {
        DaggerMovieComponent.builder()
                .faveComponent( ((FaveCoreApplication) FaveCoreApplication.getInstance()).getFaveComponent() )
                .build()
                .inject(activity);
    }
    public static void inject(MovieDetailFragment fragment) {
        DaggerMovieComponent.builder()
                .faveComponent( ((FaveCoreApplication) FaveCoreApplication.getInstance()).getFaveComponent() )
                .build()
                .inject(fragment);
    }
}
