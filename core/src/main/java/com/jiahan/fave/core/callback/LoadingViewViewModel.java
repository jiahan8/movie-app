package com.jiahan.fave.core.callback;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

public interface LoadingViewViewModel {
    ObservableField<String> getLoadingDescription();

    ObservableBoolean getLoadingVisibility();
}