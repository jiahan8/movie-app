package com.jiahan.fave.favecomponent.interactor;

import com.jiahan.fave.core.utils.Constant;
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

    public Observable<MoviesResponse> getMovie(String primaryReleaseDataLte, String primaryReleaseDataGte, String sortBy, int page) {
        return mMovieAPI.getMovieList(
                Constant.APIKEY,
                primaryReleaseDataLte,
                primaryReleaseDataGte,
                sortBy,
                page);
    }

    public Observable<MovieDetail> getMovieDetail(int movieId) {
        return mMovieAPI.getMovieDetail(
                movieId,
                Constant.APIKEY);
    }


}
