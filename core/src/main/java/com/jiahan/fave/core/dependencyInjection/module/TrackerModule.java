package com.jiahan.fave.core.dependencyInjection.module;

import android.content.Context;

import com.jiahan.fave.core.tracker.EventSender;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TrackerModule {
    @Provides
    @Singleton
    EventSender provideEventSender(final Context context) {
        return new EventSender(context);
    }
}