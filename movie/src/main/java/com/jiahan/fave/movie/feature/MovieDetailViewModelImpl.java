package com.jiahan.fave.movie.feature;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.jiahan.fave.core.common.BaseViewViewModelImpl;
import com.jiahan.fave.core.common.WebViewHtmlActivity;
import com.jiahan.fave.core.network.NetworkUtils;
import com.jiahan.fave.core.recyclerView.LoadingViewModel;
import com.jiahan.fave.core.recyclerView.LoadingViewModelImpl;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.Logger;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.favecomponent.entity.Genre;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.viewmodelanno.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModelImpl extends BaseViewViewModelImpl implements MovieDetailViewModel {
    private final MovieInteractor    mInteractor;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModelList         = new ObservableArrayList<>();
    private final ObservableBoolean                              mRefreshing            = new ObservableBoolean();
    private final ObservableBoolean                              mLoading               = new ObservableBoolean();
    private final ObservableField<String>                        mMovieImage            = new ObservableField<>();
    private final ObservableField<String>                        mMovieTitle            = new ObservableField<>();
    private final ObservableFloat                                mMoviePopularity       = new ObservableFloat();
    private final ObservableField<String>                        mMovieOverview         = new ObservableField<>();
    private final ObservableField<String>                        mMovieGenres           = new ObservableField<>();
    private final ObservableField<String>                        mMovieOriginalLanguage = new ObservableField<>();
    private final ObservableInt                                  mMovieRuntime          = new ObservableInt();

    @ViewModelFactory
    public MovieDetailViewModelImpl(final Context context,
                                    final String screenIdentifier,
                                    final EventSender eventSender,
                                    final MovieInteractor interactor,
                                    final int movieID
    ) {
        super(context, screenIdentifier, eventSender);
        mInteractor = interactor;
        if( !NetworkUtils.isNetworkConnected() ) {
            onNoInternetError();
            return;
        }
        loadMovieDetail(movieID);
    }

    public void loadMovieDetail(int movieID) {

        LoadingViewModel viewModel = new LoadingViewModelImpl();
        mInteractor.getMovieDetail( movieID )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(
                        disposable -> mViewModelList.add(viewModel)
                )
                .doOnComplete(() -> {
                    mViewModelList.remove(viewModel);
                    mRefreshing.set(false);
                    mLoading.set(false);
                })
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mContextReference.get(), Lifecycle.Event.ON_DESTROY)))
                .subscribe(outletsResponse -> {
                    mMovieImage.set(Utils.getImageURL(outletsResponse.poster_path));
                    mMovieTitle.set(outletsResponse.title);
                    mMoviePopularity.set(outletsResponse.popularity);
                    mMovieOverview.set(outletsResponse.overview);
                    List<String> result = new ArrayList<>();
                    for(Genre genre : outletsResponse.genres){
                        result.add( genre.name );
                    }
                    String resultGenre = TextUtils.join(", ", result);
                    mMovieGenres.set(resultGenre);
                    mMovieOriginalLanguage.set(outletsResponse.original_language);
                    mMovieRuntime.set(outletsResponse.runtime);

                }, throwable -> {
                    Logger.logException(throwable);
                });
    }



    @Override
    public void onItemClicked(View view) {
    }

    @Override
    public ObservableField<String> getMovieImage() {
        return mMovieImage;
    }

    @Override
    public ObservableField<String> getMovieTitle() {
        return mMovieTitle;
    }

    @Override
    public ObservableFloat getMoviePopularity() {
        return mMoviePopularity;
    }

    @Override
    public ObservableField<String> getMovieOverview() {
        return mMovieOverview;
    }

    @Override
    public ObservableField<String> getMovieGenres() {
        return mMovieGenres;
    }

    @Override
    public ObservableField<String> getMovieOriginalLanguage() {
        return mMovieOriginalLanguage;
    }

    @Override
    public ObservableInt getMovieRuntime() {
        return mMovieRuntime;
    }
}
