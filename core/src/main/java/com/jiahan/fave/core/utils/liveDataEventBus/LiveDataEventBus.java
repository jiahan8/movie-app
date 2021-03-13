package com.jiahan.fave.core.utils.liveDataEventBus;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


public final class LiveDataEventBus {
    private static com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus instance;

    private final ConcurrentHashMap<String, BusMutableLiveData<?>> mDataEventBus = new ConcurrentHashMap<>();

    public synchronized static com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus getInstance() {
        if (instance == null)
            instance = new com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus();
        return instance;
    }

    @NotNull
    public final Observable<String> with(@NotNull String key) {
        return this.with(key, String.class);
    }

    @NotNull
    public final synchronized <T> Observable<T> with(@NotNull String key, @NotNull Class<T> type) {
        BusMutableLiveData<?> tempLiveData = mDataEventBus.get(key);

        if (tempLiveData == null) {
            tempLiveData = new BusMutableLiveData<>(key, false);
            mDataEventBus.put(key, tempLiveData);
        }

        tempLiveData.setInitialize(tempLiveData.getValue() == null);
        return (Observable<T>) tempLiveData;
    }

    @NotNull
    public final synchronized <T> Observable<Map<String,T>> withMap(@NotNull String key, @NotNull Class<T> type) {
        BusMutableLiveData<?> tempLiveData = mDataEventBus.get(key);
        if (tempLiveData == null) {
            tempLiveData = new BusMutableLiveData<>(key, false);
            mDataEventBus.put(key, tempLiveData);
        }
        tempLiveData.setInitialize(tempLiveData.getValue() == null);
        return (Observable<Map<String,T>>)tempLiveData;
    }

    public static final class BusMutableLiveData<T> extends MutableLiveData<T> implements com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus.Observable<T> {
        private final Handler mainHandler;
        private final String  mKey;
        private       boolean mIsInitialize;

        public BusMutableLiveData(@Nullable String key, boolean isInitialize) {
            mKey = key;
            mIsInitialize = isInitialize;
            mainHandler = new Handler(Looper.getMainLooper());
        }

        public void observe(@NotNull LifecycleOwner owner, @NotNull Observer<? super T> observer) {
            super.observe(owner, new ObserverWrapper(observer, mIsInitialize));
        }

        public void postValueDelay(T value, long delay) {
            mainHandler.postDelayed(() -> postValue(value), delay);
        }

        public void postValueDelay(T value, long delay, @NotNull TimeUnit unit) {
            this.postValueDelay(value, TimeUnit.MILLISECONDS.convert(delay, unit));
        }

        public void observeForever(@NotNull Observer<? super T> observer) {
            super.observeForever(new ObserverWrapper(observer, mIsInitialize));
        }

        public void observeSticky(@NotNull LifecycleOwner owner, @NotNull Observer<T> observer) {
            super.observe(owner, observer);
        }

        public void observeStickyForever(@NotNull Observer<T> observer) {
            super.observeForever(observer);
        }

        public void removeObserver(@NotNull Observer<? super T> observer) {
            super.removeObserver(observer);
            if (!hasObservers()) {
                com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus.getInstance().mDataEventBus.remove(mKey);
            }
        }

        public final boolean isInitialize() {
            return mIsInitialize;
        }

        public final void setInitialize(boolean var1) {
            mIsInitialize = var1;
        }
    }

    public interface Observable<T> {
        void postValue(T value);

        void postValueDelay(T value, long duration);

        void postValueDelay(T value, long duration, @NotNull TimeUnit var4);

        void observe(@NotNull LifecycleOwner var1, @NotNull Observer<? super T> observer);

        void observeSticky(@NotNull LifecycleOwner var1, @NotNull Observer<T> observer);

        void observeForever(@NotNull Observer<? super T> observer);

        void observeStickyForever(@NotNull Observer<T> observer);

        void removeObserver(@NotNull Observer<? super T> observer);
    }

    private static final class ObserverWrapper<T> implements Observer<T> {
        private final Observer<T> observer;
        private       boolean     isInitialize;

        public ObserverWrapper(@Nullable Observer<T> observer, boolean isInitialize) {
            this.observer = observer;
            this.isInitialize = isInitialize;
        }

        public void onChanged(@Nullable T t) {
            if (!this.isInitialize) {
                this.isInitialize = true;
            }
            else {
                if (observer != null) {
                    observer.onChanged(t);
                }
            }
        }
    }
}
