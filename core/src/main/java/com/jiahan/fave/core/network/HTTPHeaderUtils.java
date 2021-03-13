package com.jiahan.fave.core.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.jiahan.fave.core.utils.Logger;
import com.jiahan.fave.core.utils.VersionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HTTPHeaderUtils {
    public static final  String DEFAULT_USER_AGENT                = "(Android " + Build.VERSION.RELEASE + ")";
    private static final String DEFAULT_ACCEPTED_LANGUAGE         = "en";
    private static final String DEFAULT_ACCEPTED_LANGUAGE_COUNTRY = "en-us";

    public static final String FAVE_CUSTOM_AUTH_HEADER      = "X-Fave-Auth";
    public static final String FAVE_CUSTOM_DEVICE_ID_HEADER = "x-device-id";

    /**
     * Obtain an accept language header for up to 5 different languages, with diminishing quality. Quality should start
     * from 1.0 and decrease by 0.1 per additional language defined. If no language is specified, this method returns
     * the en-us as default with q=1. Implemented according to: https://tools.ietf.org/html/rfc2616#page-104 Note: This
     * method perform no validation of the languages passed in
     */
    public static String getAcceptLanguageHeader() {
        // set accepted language
        final Locale defaultLocale = Locale.getDefault();
        final List<String> acceptedLanguages = new ArrayList<String>();
        if (defaultLocale != null) {
            final String languageISOCode = defaultLocale.getLanguage();
            final String languageCountry = defaultLocale.getCountry();
            if (!TextUtils.isEmpty(languageISOCode)) {
                final StringBuilder builder = new StringBuilder(languageISOCode);

                if (!TextUtils.isEmpty(languageCountry)) {
                    builder.append('-');
                    builder.append(languageCountry);
                }

                final String userAcceptedLanguage = builder.toString();
                if (HTTPHeaderUtils.DEFAULT_ACCEPTED_LANGUAGE_COUNTRY.equalsIgnoreCase(userAcceptedLanguage)
                        || HTTPHeaderUtils.DEFAULT_ACCEPTED_LANGUAGE.equalsIgnoreCase(userAcceptedLanguage)) {
                    // User choice is same as default. Just use default
                }
                else {
                    // Always add en-us and en as fallback
                    acceptedLanguages.add(userAcceptedLanguage);
                    acceptedLanguages.add(HTTPHeaderUtils.DEFAULT_ACCEPTED_LANGUAGE_COUNTRY);
                    acceptedLanguages.add(HTTPHeaderUtils.DEFAULT_ACCEPTED_LANGUAGE);
                }
            }
        }

        if (acceptedLanguages.isEmpty()) {
            acceptedLanguages.add(HTTPHeaderUtils.DEFAULT_ACCEPTED_LANGUAGE_COUNTRY);
            acceptedLanguages.add(HTTPHeaderUtils.DEFAULT_ACCEPTED_LANGUAGE);
        }

        final String QUALITY_SPECIFIER = ";q="; //$NON-NLS-1$
        final String LANGUAGES_SEPARATOR = ", "; //$NON-NLS-1$
        double quality = 1.0;

        final String decimalFormat = "%.1f"; //$NON-NLS-1$

        // Limit to max of 5 languages
        final List<String> languageSublist = acceptedLanguages.subList(0, Math.min(acceptedLanguages.size(), 5));

        boolean firstLanguage = true;
        final StringBuilder builder = new StringBuilder();
        for (final String language : languageSublist) {
            if (!firstLanguage) {
                // Append separator first
                builder.append(LANGUAGES_SEPARATOR);
            }
            else {
                firstLanguage = false;
            }

            builder.append(language);
            builder.append(QUALITY_SPECIFIER);
            builder.append(String.format(defaultLocale.getLanguage(), decimalFormat, quality));
            quality -= 0.1;

            // Safety check. Shouldn't happen
            quality = quality < 0 ? 0 : quality;
        }

        return builder.toString();
    }

    public final static String getDefaultUserAgentHeader(final Context context) {
        final String productName = VersionUtil.getInternalProductName();
        final String displayVersion = VersionUtil.getInternalVersion(context);

        return HTTPHeaderUtils.getUserAgentHeader(productName, displayVersion, HTTPHeaderUtils.DEFAULT_USER_AGENT);
    }

    /**
     * Return a custom User Agent string that contains the Product Name and Version in the form of:
     * <Product Name>/<Version> <DEFAULT UA>
     *
     * @return the custom User-Agent string
     */
    @SuppressWarnings("nls")
    public final static String getUserAgentHeader(final String productName, final String appVersion,
                                                  final String defaultUserAgent) {
        final StringBuilder builder = new StringBuilder();
        final boolean hasProductName = !TextUtils.isEmpty(productName);
        final boolean hasAppVersion = !TextUtils.isEmpty(appVersion);
        final boolean hasdefaultUserAgent = !TextUtils.isEmpty(defaultUserAgent);

        if (hasProductName) {
            builder.append(productName);
            if (hasAppVersion) {
                builder.append("/");
            }
        }

        if (hasAppVersion) {
            builder.append(appVersion);
            if (hasdefaultUserAgent) {
                builder.append(" ");
            }
        }

        if (hasdefaultUserAgent) {
            builder.append(defaultUserAgent);
        }

        return builder.toString();
    }

    /**
     * Return a stable device id string that identify the device
     *
     * @return the device string
     */
    @SuppressWarnings("nls")
    @SuppressLint("HardwareIds")
    public final static String getDeviceIdHeader(final Context context) {
        if (context == null) {
            return "";
        }

        final String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (TextUtils.isEmpty(androidID)) {
            try {
                final AdvertisingIdClient.Info idInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                return idInfo.getId();
            } catch (final Exception e) {
                Logger.logException(e);
            }
        }

        return androidID == null ? "" : androidID;
    }
}
