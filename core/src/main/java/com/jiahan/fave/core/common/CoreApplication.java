package com.jiahan.fave.core.common;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Looper;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;

import com.jiahan.fave.core.dependencyInjection.component.CoreApplicationComponent;
import com.jiahan.fave.core.utils.Logger;

import java.util.concurrent.CountDownLatch;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

@SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
public class CoreApplication extends Application {
    private static final String TAG = CoreApplication.class.getName();

    // Singleton Instance
    private static volatile CoreApplication singleton;
    private static          CountDownLatch  mInitLock        = new CountDownLatch(1);
    private                 CountDownLatch  mBaseContextLock = new CountDownLatch(1);

    // Non Data Members
    protected CoreApplicationComponent mCoreApplicationComponent;

    private BehaviorSubject<Boolean> mAppEnteredForeground = BehaviorSubject.create();

    public static CoreApplication getInstance() {
        if (singleton == null) {
            try {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    final Class<?> clazz = Class.forName("android.app.ActivityThread");
                    final Application application =
                            (Application) clazz.getDeclaredMethod("currentApplication").invoke(null);
                    if (application instanceof CoreApplication) {
                        CoreApplication.initSingleton((CoreApplication) application);
                        return singleton;
                    }
                }
            } catch (Exception e) {
                Logger.logException(e);
            }
            try {
                mInitLock.await();
            } catch (InterruptedException e) {
                Logger.logException(e);
            }
        }

        return singleton;
    }

    private static void initSingleton(final CoreApplication application) {
        if (application != null) {
            singleton = application;
            mInitLock.countDown();
        }
    }

    @Override
    public void onCreate() {
        CoreApplication.initSingleton(this);
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initializeSDKs();
    }

    @Override
    protected void attachBaseContext(Context base) {
        CoreApplication.initSingleton(this);
        super.attachBaseContext(base);
        mBaseContextLock.countDown();
        MultiDex.install(this);
    }

    protected void initializeSDKs() {
    }

    public CoreApplicationComponent getCoreApplicationComponent() {
        if (getBaseContext() == null) {
            try {
                mBaseContextLock.await();
            } catch (InterruptedException e) {
                Logger.logException(e);
            }

        }
        return mCoreApplicationComponent;
    }
}