package com.jiahan.fave.favecomponent.component.usecase;

import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.favecomponent.component.usecase.tracker.UseCaseTracker;
import com.jiahan.fave.favecomponent.entity.UseCase;

public class UseCaseItemViewModelImpl implements UseCaseItemViewModel {
    private final UseCaseTracker mTracker;
    private final UseCase        mUseCase;

    public UseCaseItemViewModelImpl(final UseCaseTracker tracker,
                                    final UseCase useCase) {
        mTracker = tracker;
        mUseCase = useCase;
    }

    @Override
    public String getName() {
        return mUseCase.mTitle;
    }

    @Override
    public String getIcon() {
        return mUseCase.mIcon;
    }

    @Override
    public void onItemClicked(View view) {
        mTracker.onTapUseCase();
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.OLD.getDeepLinkingActivityIntent(view.getContext(), mUseCase.mDeeplink));
    }
}