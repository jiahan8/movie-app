package com.jiahan.fave.core.data.session;

public class JWTSession {
    private final long   userId;
    private final String jwt;

    public JWTSession(final long userId, final String jwt) {
        this.userId = userId;
        this.jwt = jwt;
    }

    public long getUserId() {
        return userId;
    }

    public String getJwt() {
        return jwt;
    }
}
