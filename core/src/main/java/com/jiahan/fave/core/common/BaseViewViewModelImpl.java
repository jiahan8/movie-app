package com.jiahan.fave.core.common;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.text.HtmlCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;
import com.jiahan.fave.core.R;
import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.data.city.CityManager;
import com.jiahan.fave.core.data.location.LatLngParamSelector;
import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.entity.Place;
import com.jiahan.fave.core.feature.common.MessageDialogFragment;
import com.jiahan.fave.core.location.BaseLocationInteractor;
import com.jiahan.fave.core.network.NetworkConstants;
import com.jiahan.fave.core.network.pojo.response.BaseErrorResponse;
import com.jiahan.fave.core.network.pojo.response.BaseResponse;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewEmptyAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.recyclerView.RecyclerViewLabelAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewLoadingAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewSpaceAdapterDelegate;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.location.LocationTracker;
import com.jiahan.fave.core.tracker.location.LocationTrackerImpl;
import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.core.utils.DialogFragmentUtil;
import com.jiahan.fave.core.utils.LocationUtil;
import com.jiahan.fave.core.utils.Logger;
import com.jiahan.fave.core.utils.StringUtil;
import com.jiahan.fave.core.utils.drawableUtils.DrawableUtils;
import com.jiahan.fave.core.utils.drawableUtils.WidgetDrawable;
import com.jiahan.fave.core.utils.gson.GsonUtils;
import com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus;
import com.jiahan.fave.core.widget.PullBackLayout;

import java.lang.ref.WeakReference;
import java.util.List;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class BaseViewViewModelImpl extends ViewModel implements BaseViewViewModel {
    protected       WeakReference<RecyclerView> mRecyclerViewReference;
    protected       WeakReference<Context>      mContextReference;
    protected final Resources                   mResources;
    protected final EventSender                 mEventSender;
    protected final LocationTracker             mLocationTracker;

    private final String                  mScreenIdentifier;
    private final ObservableInt mNavigationIcon         = new ObservableInt(R.drawable.ic_navigation_back);
    private final ObservableField<String> mToolbarTitle           = new ObservableField<>();
    private final ObservableBoolean mToolbarTitleVisibility = new ObservableBoolean();

    private final ObservableBoolean mForceUpdateVisibility = new ObservableBoolean();
    private final ObservableBoolean mForceLogoutVisibility = new ObservableBoolean();
    private final ObservableBoolean mErrorCodeVisibility   = new ObservableBoolean();
    private final ObservableBoolean mErrorVisibility       = new ObservableBoolean();
    private final ObservableBoolean mLoadingVisibility     = new ObservableBoolean();
    private final ObservableBoolean mContentVisibility     = new ObservableBoolean();

    private final ObservableField<Spanned> mUpdateContent = new ObservableField<>();

    private final ObservableField<String> mErrorCode            = new ObservableField<>();
    private final ObservableField<Drawable> mErrorIcon            = new ObservableField<>();
    private final ObservableField<String> mErrorTitle           = new ObservableField<>();
    private final ObservableField<String> mErrorDescription     = new ObservableField<>();
    private final ObservableField<String> mErrorRetryButtonText = new ObservableField<>();

    private final ObservableField<String> mLoadingDescription = new ObservableField<>();

    private final ObservableBoolean mLocationVisibility = new ObservableBoolean();
    private final ObservableField<String> mAppLocation        = new ObservableField<>();
    private final ObservableField<String> mDisplayAppLocation = new ObservableField<>();

    private ActivityResultLauncher<String> mLocationPermissionLauncher;
    private ActivityResultLauncher<String[]> mStoragePermissionLauncher;
    private ActivityResultLauncher<Intent> mGpsEnabledLauncher;

    private ProgressDialog         progressDialog;
    private LatLngParamSelector    mSelector;
    private BaseLocationInteractor mLocationInteractor;
    private CityManager            mCityManager;

    public BaseViewViewModelImpl(@NonNull final Context context,
                                 @NonNull final String screenIdentifier,
                                 final EventSender eventSender) {
        mContextReference = new WeakReference<>(context);
        mResources = context.getResources();
        mScreenIdentifier = screenIdentifier;
        mEventSender = eventSender;
        mLocationTracker = new LocationTrackerImpl(eventSender, screenIdentifier);
        BaseActivity activity = ((BaseActivity) mContextReference.get());
        try {
            mLocationPermissionLauncher = activity.registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(),
                    result -> {
                        if (!result) {
                            Toast.makeText(context, context.getString(R.string.msg_error_enable_permission_location), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!LocationUtil.isGpsEnabled(context)) {
                            displayGPSDialog();
                            return;
                        }
                        retrieveUserLocation();
                    });
            mStoragePermissionLauncher = activity.registerForActivityResult(
                    new ActivityResultContracts.RequestMultiplePermissions(),
                    result -> {

                    });
            mGpsEnabledLauncher = activity.registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (!LocationUtil.isGpsEnabled(context)) {
                            Toast.makeText(context, context.getString(R.string.msg_error_enable_gps), Toast.LENGTH_SHORT).show();
                        }
                        onUserLocationClicked(null);
                    }
            );
        } catch (Exception ignored) {

        }
        init();
    }

    @Override
    public void onCleared() {
        super.onCleared();
        mContextReference.clear();
        if (mRecyclerViewReference != null) {
            mRecyclerViewReference.clear();
        }
    }

    @Override
    public void onHttpError(@Nullable final String sentryId, @Nullable final String description) {
        onError();
        updateErrorContent(R.drawable.ic_error_http,
                sentryId,
                mResources.getString(R.string.msg_error_generic_title),
                description,
                mContextReference.get().getString(R.string.msg_error_generic_button));
    }

    @Override
    public void onNoInternetError() {
        onError();
        updateErrorContent(R.drawable.ic_no_internet,
                null,
                mResources.getString(R.string.msg_error_no_internet_title),
                mResources.getString(R.string.msg_error_no_internet),
                mResources.getString(R.string.msg_error_generic_button));
    }

    @Override
    public void onError() {
        mErrorVisibility.set(true);
        mLoadingVisibility.set(!mErrorVisibility.get());
        mContentVisibility.set(!mErrorVisibility.get());
        mForceUpdateVisibility.set(!mErrorVisibility.get());
        mForceLogoutVisibility.set(!mErrorVisibility.get());
    }

    @Override
    public void onLoading() {
        mLoadingVisibility.set(true);
        mErrorVisibility.set(!mLoadingVisibility.get());
        mContentVisibility.set(!mLoadingVisibility.get());
        mForceUpdateVisibility.set(!mLoadingVisibility.get());
        mForceLogoutVisibility.set(!mLoadingVisibility.get());
    }

    @Override
    public void onContent() {
        mContentVisibility.set(true);
        mErrorVisibility.set(!mContentVisibility.get());
        mLoadingVisibility.set(!mContentVisibility.get());
        mForceUpdateVisibility.set(!mContentVisibility.get());
        mForceLogoutVisibility.set(!mContentVisibility.get());
    }

    @Override
    public void onForceUpdate() {
        mForceUpdateVisibility.set(true);
        mErrorVisibility.set(!mForceUpdateVisibility.get());
        mLoadingVisibility.set(!mForceUpdateVisibility.get());
        mContentVisibility.set(!mForceUpdateVisibility.get());
        mForceLogoutVisibility.set(!mForceUpdateVisibility.get());
    }

    @Override
    public void onForceLogout() {
        mForceLogoutVisibility.set(true);
        mErrorVisibility.set(!mForceLogoutVisibility.get());
        mLoadingVisibility.set(!mForceLogoutVisibility.get());
        mContentVisibility.set(!mForceLogoutVisibility.get());
        mForceUpdateVisibility.set(!mForceLogoutVisibility.get());
    }

    @Override
    public void updateLoadingContent(@NonNull final String description) {
        if (!TextUtils.isEmpty(description)) {
            mLoadingDescription.set(description);
        }
    }

    @Override
    public void updateErrorContent(@Nullable @DrawableRes final Integer iconResource,
                                   @Nullable final String errorId,
                                   @Nullable final String title,
                                   @Nullable final String description,
                                   @Nullable final String retryButtonText) {
        if (iconResource != null && iconResource != 0) {
            mErrorIcon.set(AppCompatResources.getDrawable(mContextReference.get(), iconResource));
        }
        mErrorCodeVisibility.set(!TextUtils.isEmpty(errorId));
        if (!TextUtils.isEmpty(errorId)) {
            mErrorTitle.set(mResources.getString(R.string.msg_error_http_id, errorId));
        }
        if (!TextUtils.isEmpty(title)) {
            mErrorTitle.set(title);
        }
        if (!TextUtils.isEmpty(description)) {
            mErrorDescription.set(description);
        }
        if (!TextUtils.isEmpty(retryButtonText)) {
            mErrorRetryButtonText.set(retryButtonText);
        }
    }

    @Override
    public void updateForceUpdateContent(@NonNull final String updateContent) {
        mUpdateContent.set(HtmlCompat.fromHtml(updateContent, HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    @Override
    public ActivityResultLauncher<String[]> getStoragePermissionLauncher() {
        return mStoragePermissionLauncher;
    }

    @Override
    public ObservableBoolean getContentVisibility() {
        return mContentVisibility;
    }

    @Override
    public ObservableField<String> getErrorCode() {
        return mErrorCode;
    }

    @Override
    public ObservableBoolean getErrorCodeVisibility() {
        return mErrorCodeVisibility;
    }

    @Override
    public ObservableField<Drawable> getErrorIcon() {
        return mErrorIcon;
    }

    @Override
    public ObservableField<String> getErrorTitle() {
        return mErrorTitle;
    }

    @Override
    public ObservableField<String> getErrorDescription() {
        return mErrorDescription;
    }

    @Override
    public ObservableField<String> getRetryButtonText() {
        return mErrorRetryButtonText;
    }

    @Override
    public Drawable getRetryButtonBackground() {
        return WidgetDrawable.getDefaultButtonBackground();
    }

    @Override
    public void onRetryClicked(final View view) {

    }

    @Override
    public ObservableBoolean getErrorVisibility() {
        return mErrorVisibility;
    }

    @Override
    public ObservableField<String> getLoadingDescription() {
        return mLoadingDescription;
    }

    @Override
    public ObservableBoolean getLoadingVisibility() {
        return mLoadingVisibility;
    }

    @Override
    public ObservableBoolean getForceUpdateVisibility() {
        return mForceUpdateVisibility;
    }

    @Override
    public ObservableField<Spanned> getUpdateContent() {
        return mUpdateContent;
    }

    @Override
    public Drawable getUpdateAppButtonBackground() {
        return WidgetDrawable.getDefaultButtonBackground();
    }

    @Override
    public void onUpdateAppClicked(final View view) {
//TODO launch google play store or huawei app gallery
    }

    @Override
    public ObservableBoolean getForceLogoutVisibility() {
        return mForceLogoutVisibility;
    }

    @Override
    public Drawable getLogoutButtonBackground() {
        return WidgetDrawable.getDefaultButtonBackground();
    }

    @Override
    public void onLogoutClicked(final View view) {
//TODO logout user and clear everything
    }

    @Override
    public ObservableField<String> getToolbarTitle() {
        return mToolbarTitle;
    }

    @Override
    public ObservableInt getNavigationIcon() {
        return mNavigationIcon;
    }

    @Override
    public ObservableBoolean getToolbarTitleVisibility() {
        return mToolbarTitleVisibility;
    }

    @Override
    public void setToolbarTitle(final String title) {
        mToolbarTitleVisibility.set(!TextUtils.isEmpty(title));
        if (!TextUtils.isEmpty(title) && !mResources.getString(R.string.e_cards).equals(title)) {
            mToolbarTitle.set(StringUtil.capitalise(title));
            return;
        }
        mToolbarTitle.set(title);
    }

    @Override
    public void setNavigationIcon(final int iconResource) {
        mNavigationIcon.set(iconResource);
    }

    @Override
    public void onOptionsItemSelected(final MenuItem item) {

    }

    @Override
    public AppBarLayout.OnOffsetChangedListener getOnOffsetChangedListener() {
        return null;
    }

    @Override
    public int getCustomToolbarLayout() {
        return R.layout.toolbar_default;
    }

    @Override
    public int getMarginTop() {
        return mResources.getDimensionPixelSize(R.dimen.size_0);
    }

    @Override
    public int getFABLayout() {
        return 0;
    }

    @Override
    public void onBackClicked(final View view) {
        BaseActivity activity = ((BaseActivity) mContextReference.get());
        View focusedView = activity.getCurrentFocus();
        if (view != null && focusedView instanceof EditText) {
            activity.hideKeyboard();
            return;
        }
        activity.onBackPressed();
    }

    @Override
    public PullBackLayout.OnPullListener getOnPullListener() {
        return null;
    }

    @Override
    public ObservableBoolean getLocationVisibility() {
        return mLocationVisibility;
    }

    @Override
    public Drawable getLocationBackground() {
        return DrawableUtils.createDrawable(GradientDrawable.RECTANGLE,
                R.dimen.size_36,
                0,
                0,
                R.color.white);
    }

    @Override
    public ObservableField<String> getAppLocation() {
        return mAppLocation;
    }

    @Override
    public ObservableField<String> getDisplayAppLocation() {
        return mDisplayAppLocation;
    }

    @Override
    public void onSearchClicked(final View view) {
        mLocationTracker.onTapSearch(getAppLocation().get());
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.OLD.getSearchActivityIntent(view.getContext(), getScreenIdentifier()));
    }

    @Override
    public void onSearchLocationClicked(final View view) {
        mLocationTracker.onTapSearchLocation(getAppLocation().get());
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.Location.getSearchLocationActivityIntent(view.getContext(), getScreenIdentifier()));
    }

    @Override
    public void onChangeCityClicked(final View view) {
        mLocationTracker.onTapChangeCity();
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.Location.getChangeCityActivityIntent(view.getContext(), getScreenIdentifier()));
    }

    @Override
    public void onUserLocationClicked(final View view) {
        if (view != null) {
            mLocationTracker.onTapUserLocation(getAppLocation().get());
        }
        if (LocationUtil.isLocationPermissionGranted(mContextReference.get())) {
            if (LocationUtil.isGpsEnabled(mContextReference.get())) {
                retrieveUserLocation();
                return;
            }
            displayGPSDialog();
            return;
        }
        mLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void updateLocation(@NonNull final String newLocation) {
        mAppLocation.set(newLocation);
        mDisplayAppLocation.set(mResources.getString(R.string.search_in, newLocation));
    }

    @Override
    public EventSender getEventSender() {
        return mEventSender;
    }

    @Override
    public String getScreenIdentifier() {
        return mScreenIdentifier;
    }

    @Override
    public RecyclerViewAdapter createRecyclerViewAdapter(@NonNull final ObservableArrayList<RecyclerViewItemViewModel> recyclerViewList,
                                                         @NonNull final AdapterDelegate... delegates) {
        final AdapterDelegatesManager<List<RecyclerViewItemViewModel>> manager = new AdapterDelegatesManager<>();
        if (mContextReference != null) {
            final LayoutInflater inflater = LayoutInflater.from(mContextReference.get());
            manager.addDelegate(new RecyclerViewLoadingAdapterDelegate(inflater));
            manager.addDelegate(new RecyclerViewEmptyAdapterDelegate(inflater));
            manager.addDelegate(new RecyclerViewLabelAdapterDelegate(mContextReference.get()));
            manager.addDelegate(new RecyclerViewSpaceAdapterDelegate(mContextReference.get()));
        }
        for (AdapterDelegate adapterDelegate : delegates) {
            manager.addDelegate(adapterDelegate);
        }
        return new RecyclerViewAdapter(manager, recyclerViewList);
    }

    protected void handleErrorResponse(@NonNull Throwable throwable) {
        Logger.logException(throwable);
        if (throwable instanceof HttpException) {
            Response response = ((HttpException) throwable).response();
            if (response != null) {
                if (NetworkConstants.FORCE_LOGOUT_HTTP_CODE == response.code()) {
                    onForceLogout();
                    return;
                }
                if (NetworkConstants.FORCE_UPDATE_HTTP_CODE == response.code() && response.body() != null) {
                    BaseResponse baseResponse = GsonUtils.getGson().fromJson(response.body().toString(), BaseResponse.class);
                    updateForceUpdateContent(baseResponse.mMessage);
                    onForceUpdate();
                    return;
                }
                if (response.errorBody() != null) {
                    try {
                        BaseResponse baseResponse = GsonUtils.getGson().fromJson(response.errorBody().string(), BaseResponse.class);
                        if (baseResponse.mErrorList == null) {
                            onHttpError(null, baseResponse.mMessage);
                            return;
                        }
                        for (BaseErrorResponse baseErrorResponse : baseResponse.mErrorList) {
                            onHttpError(baseErrorResponse.mSentryId, baseErrorResponse.mMessage);
                            return;
                        }
                    } catch (Exception ignored) {

                    } finally {
                        onError();
                    }
                }
            }
            return;
        }
        onError();
    }

    protected void sendScreenDisplayedEvent() {
        if (mEventSender != null) {
            mEventSender.send(mEventSender.screenDisplayed(getScreenIdentifier()));
        }
    }

    protected synchronized void showProgressDialog(final String message) {
        BaseActivity activity = (BaseActivity) mContextReference.get();
        activity.runOnUiThread(() -> {
            try {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (activity.isFinishing()) {
                    return;
                }
                if (!TextUtils.isEmpty(message)) {
                    progressDialog = ProgressDialog.show(activity, "", message, true);
                }
                else {
                    progressDialog = ProgressDialog.show(activity, null, null, false, true);
                    if (progressDialog.getWindow() != null) {
                        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    }
                }
                progressDialog.setCancelable(true);
                progressDialog.setCanceledOnTouchOutside(false);
            } catch (Exception e) {
                Logger.logException(e);
            }
        });
    }

    protected void dismissProgressDialog() {
        BaseActivity activity = (BaseActivity) mContextReference.get();
        activity.runOnUiThread(() -> {
            if (!activity.isFinishing()) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                progressDialog = null;
            }
        });
    }

    protected void setLocationSelector(@NonNull final LatLngParamSelector selector) {
        mSelector = selector;
    }

    protected void setBaseLocationInteractor(@NonNull final BaseLocationInteractor locationInteractor) {
        mLocationInteractor = locationInteractor;
    }

    protected void setCityManager(@NonNull final CityManager cityManager) {
        mCityManager = cityManager;
    }

    private void init() {
        updateLoadingContent(mResources.getString(R.string.msg_loading_please_wait));
        updateErrorContent(R.drawable.ic_error_generic,
                null,
                mResources.getString(R.string.msg_error_generic_title),
                mResources.getString(R.string.msg_error_generic),
                mResources.getString(R.string.msg_error_generic_button));
        setToolbarTitle("");
        setNavigationIcon(R.drawable.ic_navigation_back);
        sendScreenDisplayedEvent();
    }

    private void displayGPSDialog() {
        try {
            final MessageDialogFragment fragment = MessageDialogFragment.newInstance(
                    null,
                    mResources.getString(R.string.enable_location_title),
                    mResources.getString(R.string.enable_location_content),
                    mResources.getString(R.string.okay),
                    mResources.getString(R.string.not_now),
                    false);
            DialogFragmentUtil.displayFragment((BaseActivity) mContextReference.get(), fragment, MessageDialogFragment.TAG);
            fragment.setPositiveButtonListener(() -> {
                fragment.dismiss();
                mGpsEnabledLauncher.launch(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            });
        } catch (Exception e) {
            Logger.logException(e);
            // State Loss. For detail explanation of this issue:
            // http://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html
        }
    }

    private void retrieveUserLocation() {
        if (mSelector == null || mLocationInteractor == null || mCityManager == null) {
            return;
        }
        showProgressDialog(mResources.getString(R.string.msg_loading_please_wait));
        mSelector.refreshAndGetUserLocation()
                .subscribeOn(Schedulers.io())
                .flatMap(latLng -> mLocationInteractor.getCityByCoordinate(latLng.getLatitude(), latLng.getLongitude())
                        .flatMap(cityByCoordinateResponse -> {
                            cityByCoordinateResponse.mLatitude = latLng.getLatitude();
                            cityByCoordinateResponse.mLongitude = latLng.getLongitude();
                            return Observable.just(cityByCoordinateResponse);
                        }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((BaseActivity) mContextReference.get(), Lifecycle.Event.ON_DESTROY)))
                .subscribe(cityByCoordinateResponse -> {
                    dismissProgressDialog();
                    if (cityByCoordinateResponse == null || cityByCoordinateResponse.mCity == null) {
                        return;
                    }
                    if (cityByCoordinateResponse.mCity.id != mCityManager.getCurrentCity().blockingFirst().id) {
                        LiveDataEventBus.getInstance()
                                .with(Constant.CHANGE_CITY, City.class)
                                .postValue(cityByCoordinateResponse.mCity);
                        return;
                    }
                    Place place = new Place();
                    place.mName = cityByCoordinateResponse.mLocation;
                    place.mLatitude = cityByCoordinateResponse.mLatitude;
                    place.mLongitude = cityByCoordinateResponse.mLongitude;
                    LiveDataEventBus.getInstance()
                            .with(Constant.CHANGE_PLACE, Place.class)
                            .postValue(place);
                }, throwable -> {
                    Logger.logException(throwable);
                    dismissProgressDialog();
                });
    }
}