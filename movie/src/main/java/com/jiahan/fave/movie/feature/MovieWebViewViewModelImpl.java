package com.jiahan.fave.movie.feature;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jiahan.fave.core.common.BaseViewViewModelImpl;
import com.jiahan.fave.core.network.NetworkUtils;
import com.jiahan.fave.core.recyclerView.LoadingViewModel;
import com.jiahan.fave.core.recyclerView.LoadingViewModelImpl;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.utils.EndlessRecyclerViewScrollListener;
import com.jiahan.fave.core.utils.Logger;
import com.jiahan.fave.favecomponent.adapter.MovieAdapterDelegate;
import com.jiahan.fave.favecomponent.component.movie.MovieItemViewModelImpl;
import com.jiahan.fave.favecomponent.entity.Movie;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.favecomponent.network.pojo.response.MoviesResponse;
import com.jiahan.fave.viewmodelanno.ViewModelFactory;

import java.lang.ref.WeakReference;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieWebViewViewModelImpl extends BaseViewViewModelImpl implements MovieWebViewViewModel {

    ObservableField<String> mWebURL = new ObservableField<>();

    @ViewModelFactory
    public MovieWebViewViewModelImpl(final Context context,
                                  final String screenIdentifier,
                                  final EventSender eventSender,
                                  final MovieInteractor interactor,
                                     String lol
    ) {
        super(context, screenIdentifier, eventSender);
        Log.e("lol", lol);
        mWebURL.set( lol );
    }

    @Override
    public ObservableField<String> getWebUrl() {
        return mWebURL;
    }

}
