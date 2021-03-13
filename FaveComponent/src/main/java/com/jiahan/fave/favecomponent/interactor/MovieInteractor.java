package com.jiahan.fave.favecomponent.interactor;

import com.jiahan.fave.favecomponent.entity.MovieDetail;
import com.jiahan.fave.favecomponent.network.MovieAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.MoviesResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class MovieInteractor extends BaseInteractor {

    @Inject
    MovieAPI mMovieAPI;

    @Inject
    public MovieInteractor() {
    }

    public Observable<MoviesResponse> getMovie(int page) {
        return mMovieAPI.getMovieList(
                "a2cdcd4721eca322a8e25ba729721569",
                "2021-08-01",
                "2010-01-01",
                "release_date.desc",
                page);
    }

    public Observable<MovieDetail> getMovieDetail(int movieId) {
        return mMovieAPI.getMovieDetail(
                movieId,
                "a2cdcd4721eca322a8e25ba729721569");
    }


}
