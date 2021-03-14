package com.jiahan.fave.movie.feature;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.common.BaseFragment;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.ScreenIdentifier;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.movie.R;
import com.jiahan.fave.movie.di.Injector;

import javax.inject.Inject;

public class MovieDetailFragment extends BaseFragment {

    @Inject
    EventSender mEventSender;

    @Inject
    MovieInteractor mMovieInteractor;


    public static MovieDetailFragment newInstance(Bundle bundle) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments( bundle );
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    protected BaseViewViewModel getBaseViewViewModel() {
        Bundle bundle = this.getArguments();
        int movieId = 0;
        if(bundle != null){
            movieId = bundle.getInt("EXTRA_MOVIE_ID");
        }
        return new MovieDetailViewModelImpl(mActivity, ScreenIdentifier.SCREEN_MOVIE, mEventSender, mMovieInteractor, movieId );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }


}
