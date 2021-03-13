package com.jiahan.fave.movie.feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.common.BaseFragment;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.ScreenIdentifier;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.movie.R;
//import com.jiahan.fave.movie.databinding.FragmentMovieDetailBinding;
import com.jiahan.fave.movie.di.Injector;

import javax.inject.Inject;

public class MovieDetailFragment extends BaseFragment {

    @Inject
    EventSender mEventSender;

    @Inject
    MovieInteractor mMovieInteractor;

    MovieDetailViewModel mViewModel;

    public static MovieDetailFragment newInstance() {
        return new MovieDetailFragment();
    }

    @Override
    protected int getLayoutResource() {
//        return R.layout.fragment_movie_detail;
        return 0;
    }

    @Override
    protected BaseViewViewModel getBaseViewViewModel() {
//        ViewModelProvider sharedProvider = new ViewModelProvider(mActivity);
//        MovieDetailViewModel movieDetailViewModel = sharedProvider.get(MovieDetailViewModelImpl.class);
//        ViewModelProvider provider = new ViewModelProvider(mActivity, MovieDetailViewModelImplFactory.create(
//                mActivity,
//                ScreenIdentifier.SCREEN_MOVIE,
//                mEventSender,
//                mMovieInteractor
//                ));
//        mViewModel = provider.get(MovieDetailViewModelImpl.class);
//        return mViewModel;
        return new MovieDetailViewModelImpl(mActivity, ScreenIdentifier.SCREEN_MOVIE, mEventSender, mMovieInteractor);
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        FragmentMovieDetailBinding _binding = DataBindingUtil.inflate( inflater, R.layout.fragment_movie_detail, container, false );
////        View view = _binding.getRoot();
//////        _binding.setMovie();
////        return view;
//
//    }




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
