package com.jiahan.fave.favecomponent.component.usecase.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.favecomponent.entity.UseCase;

public class UseCaseTrackerImpl implements UseCaseTracker {
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;
    private final String      mSectionName;
    private final int         mPosition;
    private final UseCase     mUseCase;

    public UseCaseTrackerImpl(final EventSender eventSender,
                              final String screenIdentifier,
                              final String sectionName,
                              final int position,
                              final UseCase useCase) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mSectionName = sectionName;
        mPosition = position;
        mUseCase = useCase;
    }

    @Override
    public void onTapUseCase() {
        EventActions actions = mEventSender.tap(mUseCase.mTitle, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.SECTION_NAME, mSectionName)
                .addProperty(PropertyConstant.Key.POSITION, mPosition)
                .addProperty(PropertyConstant.Key.TYPE, mUseCase.mType);
        mEventSender.send(actions);
    }
}