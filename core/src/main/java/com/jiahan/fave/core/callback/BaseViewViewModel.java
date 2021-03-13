package com.jiahan.fave.core.callback;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface BaseViewViewModel extends ContentViewViewModel,
        ErrorViewViewModel,
        LoadingViewViewModel,
        ForceUpdateViewViewModel,
        ForceLogoutViewViewModel,
        LocationViewModel,
        TrackerViewModel,
        LayoutViewModel,
        FABViewModel {
    void onHttpError(@Nullable final String sentryId,
                     @Nullable final String description);

    void onNoInternetError();

    void onError();

    void onLoading();

    void onContent();

    void onForceUpdate();

    void onForceLogout();

    void updateLoadingContent(@NonNull final String description);

    void updateErrorContent(@Nullable final Integer iconResource,
                            @Nullable final String errorId,
                            @Nullable final String title,
                            @Nullable final String description,
                            @Nullable final String retryButtonText);

    void updateForceUpdateContent(@NonNull final String updateContent);

    ActivityResultLauncher<String[]> getStoragePermissionLauncher();

    RecyclerViewAdapter createRecyclerViewAdapter(@NonNull final ObservableArrayList<RecyclerViewItemViewModel> recyclerViewList,
                                                  @NonNull final AdapterDelegate... delegates);
}