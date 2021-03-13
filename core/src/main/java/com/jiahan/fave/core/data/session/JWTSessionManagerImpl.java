package com.jiahan.fave.core.data.session;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.jiahan.fave.core.data.user.UserDataManager;
import com.jiahan.fave.core.entity.User;

import okhttp3.Interceptor;
import okhttp3.Request;

public class JWTSessionManagerImpl implements SessionManager<JWTSession> {
    private final UserDataManager<User> mUserDataManager;

    public JWTSessionManagerImpl(@NonNull final UserDataManager<User> userDataManager) {
        mUserDataManager = userDataManager;
    }

    @Nullable
    @Override
    public JWTSession getUserSession() {
        final User user = mUserDataManager.user().get();
        if (user.mId == 0 || TextUtils.isEmpty(user.mJwt)) {
            return null;
        }
        final String jwt = user.mJwt;
        return new JWTSession(user.mId, jwt);
    }

    @NonNull
    @Override
    public Interceptor getOkHttpRequestInterceptor() {
        return chain -> {
            final Request.Builder requestBuilder = chain.request().newBuilder();
            final Pair<String, String> authHeader = getAuthHeader();
            if (authHeader != null && !TextUtils.isEmpty(authHeader.first) && !TextUtils.isEmpty(authHeader.second)) {
                requestBuilder.addHeader(authHeader.first, authHeader.second);
            }
            return chain.proceed(requestBuilder.build());
        };
    }

    @Nullable
    @Override
    public Pair<String, String> getAuthHeader() {
        final JWTSession jwtSession = getUserSession();
        if (jwtSession == null || jwtSession.getJwt() == null) {
            return null;
        }
        return new Pair<>("Authorization", "Bearer " + jwtSession.getJwt());
    }
}