package com.jiahan.fave.movie.feature;

import androidx.databinding.ObservableField;

import com.jiahan.fave.core.callback.BaseViewViewModel;

public interface MovieWebViewViewModel extends BaseViewViewModel {

    ObservableField<String> getWebUrl();

}