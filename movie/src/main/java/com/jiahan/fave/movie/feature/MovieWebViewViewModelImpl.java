package com.jiahan.fave.movie.feature;

import android.content.Context;
import androidx.databinding.ObservableField;

import com.jiahan.fave.core.common.BaseViewViewModelImpl;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.viewmodelanno.ViewModelFactory;

public class MovieWebViewViewModelImpl extends BaseViewViewModelImpl implements MovieWebViewViewModel {

    ObservableField<String> mWebURL = new ObservableField<>();

    @ViewModelFactory
    public MovieWebViewViewModelImpl(final Context context,
                                     final String screenIdentifier,
                                     final EventSender eventSender,
                                     final MovieInteractor interactor) {
        super(context, screenIdentifier, eventSender);


        mWebURL.set("https://www.cathaycineplexes.com.sg/");
    }

    @Override
    public ObservableField<String> getWebUrl() {
        return mWebURL;
    }

}