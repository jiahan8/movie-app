package com.jiahan.fave.favecomponent.manager.app;

import com.jiahan.fave.core.common.LiveDataSharedPreference;
import com.jiahan.fave.core.data.app.ApplicationDataManager;

import java.util.HashMap;

public interface FaveApplicationDataManager extends ApplicationDataManager {
    LiveDataSharedPreference<String> registrationId();

    LiveDataSharedPreference<Boolean> locationPermissionPrompted();

    LiveDataSharedPreference<Boolean> shouldShowECardOnboarding();

    LiveDataSharedPreference<Boolean> shouldShowWishlistToolTip();

    LiveDataSharedPreference<Boolean> shouldShowWishlistOnBoarding();

    LiveDataSharedPreference<Boolean> shouldShowHomeOnBoarding();

    LiveDataSharedPreference<Boolean> shouldShowNearbyOnBoarding();

    LiveDataSharedPreference<Boolean> shouldShowEventOnBoarding();

    LiveDataSharedPreference<Boolean> shouldShowNewCompanyReward();

    LiveDataSharedPreference<Boolean> shouldShowAddPaymentPrompt();

    LiveDataSharedPreference<String> sixDigitCardBinNo();

    LiveDataSharedPreference<Boolean> hasOpenedDeferredlink();

    LiveDataSharedPreference<String> deferredDeepLink();

    LiveDataSharedPreference<String> nexmoCode();

    LiveDataSharedPreference<HashMap<String, String>> remoteConfigValues();

    LiveDataSharedPreference<Boolean> showGrabpayConnectTooltip();

    LiveDataSharedPreference<Boolean> showAddCreditCardTooltip();

    LiveDataSharedPreference<Boolean> showSetPrimaryTooltip();

    LiveDataSharedPreference<Boolean> showPaymentAddMultipleCardTooltip();

    LiveDataSharedPreference<Boolean> showMeTabIconDot();

    LiveDataSharedPreference<Boolean> showMePaymentHistoryDot();

    LiveDataSharedPreference<Boolean> showMePaymentsDot();

    LiveDataSharedPreference<Boolean> isFingerprintEnabled();

    LiveDataSharedPreference<Boolean> showECardHowItWork();

//    LiveDataSharedPreference<ImmutableGiftingDetails> giftRecipientDetail();
}
