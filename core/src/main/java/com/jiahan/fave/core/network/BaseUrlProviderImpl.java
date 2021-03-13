package com.jiahan.fave.core.network;

public class BaseUrlProviderImpl {
    private static final String BASE_URL_PROD = "https://api.myfave.com";

    public String call() {
        return BASE_URL_PROD;
    }
}