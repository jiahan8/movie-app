package com.jiahan.fave.favecomponent.manager.app;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiahan.fave.core.common.LiveDataSharedPref;
import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.app.ApplicationDataManagerImpl;

import java.util.HashMap;

public class FaveApplicationDataManagerImpl extends ApplicationDataManagerImpl implements FaveApplicationDataManager {
    private static final String REGISTRATION_ID                  = "REGISTRATION_ID";
    private static final String LOCATION_PERMISSION_PROMPTED     = "LOCATION_PERMISSION_PROMPTED";
    private static final String FIRST_TIME_E_CARD_ONBOARDING     = "FIRST_TIME_E_CARD_ONBOARDING";
    private static final String WISHLIST_TOOLTIP                 = "WISHLIST_TOOLTIP";
    private static final String FIRST_TIME_ADD_WISHLIST          = "FIRST_TIME_ADD_WISHLIST";
    private static final String FIRST_TIME_HOME_ONBOARDING       = "FIRST_TIME_HOME_ONBOARDING";
    private static final String FIRST_TIME_NEARBY_ONBOARDING     = "FIRST_TIME_NEARBY_ONBOARDING";
    private static final String FIRST_TIME_COMPANY_REWARD        = "FIRST_TIME_PARTNER_REWARD";
    private static final String FIRST_TIME_ADD_PAYMENT_PROMPT    = "FIRST_TIME_ADD_PAYMENT_PROMPT";
    private static final String BIN_BASED_CARD_NO                = "BIN_BASED_CARD_NO";
    private static final String HAS_OPENED_DEFERRED_LINK         = "HAS_OPENED_DEFERRED_LINK";
    private static final String PENDING_DEFERRED_LINK            = "PENDING_DEFERRED_LINK";
    private static final String NEXMO_CODE                       = "NEXMO_CODE";
    private static final String GRABPAY_CONNECT_TOOLTIP          = "GRABPAY_CONNECT_TOOLTIP";
    private static final String ADD_CREDIT_CARD_TOOLTIP          = "SHOW_CREDIT_CARD_TOOLTIP";
    private static final String SHOW_SET_PRIMARY_METHOD_TOOLTIP  = "SHOW_PRIMARY_METHOD_TOOLTIP";
    private static final String SHOW_ME_NEW_ICON_NOTIFICATION    = "SHOW_ME_NEW_ICON_NOTIFICATION";
    private static final String SHOW_ME_TAB_RED_DOT              = "SHOW_ME_TAB_RED_DOT";
    private static final String SHOW_ME_PAYMENT_DOT              = "SHOW_ME_PAYMENT_DOT";
    private static final String SHOW_ME_PAYMENT_HISTORY_DOT      = "SHOW_ME_PAYMENT_HISTORY_DOT";
    private static final String IS_FINGERPRINT_ENABLED           = "IS_FINGERPRINT_ENABLED";
    private static final String FIRST_TIME_FAVE_EVENT_ONBOARDING = "FIRST_TIME_FAVE_EVENT_ONBOARDING";
    private static final String REMOTE_CONFIG_VALUES             = "REMOTE_CONFIG_VALUES";
    private static final String E_CARD_GIFT_RECIPIENT_DETAIL     = "E_CARD_GIFT_RECIPIENT_DETAIL";
    private static final String SHOW_E_CARD_HOW_IT_WORK          = "SHOW_E_CARD_HOW_IT_WORK";

    public FaveApplicationDataManagerImpl(@NonNull final LiveDataSharedPref mLiveDataSharedPref, @NonNull final Gson gson) {
        super(mLiveDataSharedPref, gson);
    }

    @Override
    public LiveDataSharedPreference<String> registrationId() {
        return mLiveDataSharedPref.getString(REGISTRATION_ID, "");
    }

    @Override
    public LiveDataSharedPreference<Boolean> locationPermissionPrompted() {
        return mLiveDataSharedPref.getBoolean(LOCATION_PERMISSION_PROMPTED, false);
    }

    @Override
    public LiveDataSharedPreference<Boolean> shouldShowECardOnboarding() {
        return mLiveDataSharedPref.getBoolean(FIRST_TIME_E_CARD_ONBOARDING, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> shouldShowWishlistToolTip() {
        return mLiveDataSharedPref.getBoolean(WISHLIST_TOOLTIP, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> shouldShowWishlistOnBoarding() {
        return mLiveDataSharedPref.getBoolean(FIRST_TIME_ADD_WISHLIST, true);
    }


    @Override
    public LiveDataSharedPreference<Boolean> shouldShowHomeOnBoarding() {
        return mLiveDataSharedPref.getBoolean(FIRST_TIME_HOME_ONBOARDING, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> shouldShowNearbyOnBoarding() {
        return mLiveDataSharedPref.getBoolean(FIRST_TIME_NEARBY_ONBOARDING, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> shouldShowEventOnBoarding() {
        return mLiveDataSharedPref.getBoolean(FIRST_TIME_FAVE_EVENT_ONBOARDING, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> shouldShowNewCompanyReward() {
        return mLiveDataSharedPref.getBoolean(FIRST_TIME_COMPANY_REWARD, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> shouldShowAddPaymentPrompt() {
        return mLiveDataSharedPref.getBoolean(FIRST_TIME_ADD_PAYMENT_PROMPT, true);
    }

    @Override
    public LiveDataSharedPreference<String> sixDigitCardBinNo() {
        return mLiveDataSharedPref.getString(BIN_BASED_CARD_NO, "");
    }

    @Override
    public LiveDataSharedPreference<Boolean> hasOpenedDeferredlink() {
        return mLiveDataSharedPref.getBoolean(HAS_OPENED_DEFERRED_LINK, false);
    }

    @Override
    public LiveDataSharedPreference<String> deferredDeepLink() {
        return mLiveDataSharedPref.getString(PENDING_DEFERRED_LINK, "");
    }

    @Override
    public LiveDataSharedPreference<HashMap<String, String>> remoteConfigValues() {
        return getGsonObject(REMOTE_CONFIG_VALUES, new HashMap<String, String>(), new TypeToken<HashMap<String, String>>() {
        });
    }

    @Override
    public LiveDataSharedPreference<String> nexmoCode() {
        return mLiveDataSharedPref.getString(NEXMO_CODE, "");
    }

    @Override
    public LiveDataSharedPreference<Boolean> showGrabpayConnectTooltip() {
        return mLiveDataSharedPref.getBoolean(GRABPAY_CONNECT_TOOLTIP, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> showAddCreditCardTooltip() {
        return mLiveDataSharedPref.getBoolean(ADD_CREDIT_CARD_TOOLTIP, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> showSetPrimaryTooltip() {
        return mLiveDataSharedPref.getBoolean(SHOW_SET_PRIMARY_METHOD_TOOLTIP, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> showPaymentAddMultipleCardTooltip() {
        return mLiveDataSharedPref.getBoolean(SHOW_ME_NEW_ICON_NOTIFICATION, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> showMeTabIconDot() {
        return mLiveDataSharedPref.getBoolean(SHOW_ME_TAB_RED_DOT, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> showMePaymentsDot() {
        return mLiveDataSharedPref.getBoolean(SHOW_ME_PAYMENT_DOT, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> showMePaymentHistoryDot() {
        return mLiveDataSharedPref.getBoolean(SHOW_ME_PAYMENT_HISTORY_DOT, true);
    }

    @Override
    public LiveDataSharedPreference<Boolean> isFingerprintEnabled() {
        return mLiveDataSharedPref.getBoolean(IS_FINGERPRINT_ENABLED, false);
    }

    @Override
    public LiveDataSharedPreference<Boolean> showECardHowItWork() {
        return mLiveDataSharedPref.getBoolean(SHOW_E_CARD_HOW_IT_WORK, true);
    }

//    @Override
//    public LiveDataSharedPreference<ImmutableGiftingDetails> giftRecipientDetail() {
//        ImmutableGiftingDetails giftingDetails = ImmutableGiftingDetails.builder()
//                .build();
//        return getGsonObject(E_CARD_GIFT_RECIPIENT_DETAIL, giftingDetails, TypeToken.get(ImmutableGiftingDetails.class));
//    }
}
