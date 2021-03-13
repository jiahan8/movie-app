package com.jiahan.fave.core.feature.common;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.callback.ButtonClickedListener;
import com.jiahan.fave.core.common.BaseDialogFragment;
import com.jiahan.fave.core.databinding.FragmentMessageDialogBinding;

/**
 * To setPositiveButtonListener
 * 1. Initialize Fragment
 * 2. Show or Add this fragment
 * 4. Call setPositiveButtonListener or setNegativeButtonListener
 * If the sequence is wrong, it will throw NPE
 */
public class MessageDialogFragment extends BaseDialogFragment {
    public static final  String TAG                        = MessageDialogFragment.class.getSimpleName();
    private static final String EXTRA_ICON_RESOURCE        = TAG + "EXTRA_ICON_RESOURCE";
    private static final String EXTRA_TITLE                = TAG + "EXTRA_TITLE";
    private static final String EXTRA_MESSAGE              = TAG + "EXTRA_MESSAGE";
    private static final String EXTRA_POSITIVE_BUTTON_TEXT = TAG + "EXTRA_POSITIVE_BUTTON_TEXT";
    private static final String EXTRA_NEGATIVE_BUTTON_TEXT = TAG + "EXTRA_NEGATIVE_BUTTON_TEXT";
    private static final String EXTRA_IS_FINISHING         = TAG + "EXTRA_IS_FINISHING";

    private Integer mIconResource;
    private String  mTitle;
    private String  mMessage;
    private String  mPositiveButtonText;
    private String  mNegativeButtonText;
    private Boolean mIsFinishing;

    private ButtonClickedListener mPositiveButtonListener;
    private ButtonClickedListener mNegativeButtonListener;

    public static MessageDialogFragment newInstance(@Nullable final Integer iconResource,
                                                    @Nullable final String title,
                                                    @NonNull final String message,
                                                    @NonNull final String positiveButtonText,
                                                    @Nullable final String negativeButtonText,
                                                    @Nullable final Boolean isFinishing) {
        final Bundle args = new Bundle();
        args.putInt(EXTRA_ICON_RESOURCE, iconResource != null ? iconResource : 0);
        args.putString(EXTRA_TITLE, title);
        args.putString(EXTRA_MESSAGE, message);
        args.putString(EXTRA_POSITIVE_BUTTON_TEXT, positiveButtonText);
        args.putString(EXTRA_NEGATIVE_BUTTON_TEXT, negativeButtonText);
        args.putBoolean(EXTRA_IS_FINISHING, isFinishing);
        final MessageDialogFragment fragment = new MessageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_message_dialog;
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInputData();
        bindData(view);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mIsFinishing) {
            mActivity.finish();
        }
    }

    private void getInputData() {
        Bundle argument = getArguments();
        if (argument == null) {
            return;
        }
        mIconResource = argument.getInt(EXTRA_ICON_RESOURCE, 0);
        mTitle = argument.getString(EXTRA_TITLE);
        mMessage = argument.getString(EXTRA_MESSAGE);
        mPositiveButtonText = argument.getString(EXTRA_POSITIVE_BUTTON_TEXT);
        mNegativeButtonText = argument.getString(EXTRA_NEGATIVE_BUTTON_TEXT);
        mIsFinishing = argument.getBoolean(EXTRA_IS_FINISHING, false);
    }

    private void bindData(View view) {
        MessageDialogViewModel viewModel = new MessageDialogViewModelImpl(
                this,
                mIconResource,
                mTitle,
                mMessage,
                mPositiveButtonText,
                mNegativeButtonText);
        final FragmentMessageDialogBinding binding = DataBindingUtil.bind(view);
        if (binding == null) {
            return;
        }
        binding.setViewModel(viewModel);
        if (viewModel.getWebViewVisibility()) {
            setupWebView();
        }
    }

    private void setupWebView() {
//        if (!TextUtils.isEmpty(mMessage)) {
//            mBinding.webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
//            mBinding.webView.setBackgroundColor(Color.TRANSPARENT);
//            final WebSettings webSettings =  mBinding.webView.getSettings();
//            webSettings.setDefaultTextEncodingName(HTML_ENCODING);
//            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//            mBinding.webView.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    if (URLUtil.isValidUrl(url)) {
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                    }
//                    return true;
//                }
//            });
//            mBinding.webView.clearCache(true);
//            mBinding.webView.clearHistory();
//            mBinding.webView.loadDataWithBaseURL(null, mMessage, HTML_MIME_TYPE, HTML_ENCODING, null);
//        }
    }

    public void setPositiveButtonListener(ButtonClickedListener positiveButtonListener) {
        mPositiveButtonListener = positiveButtonListener;
    }

    public ButtonClickedListener getPositiveButtonListener() {
        return mPositiveButtonListener;
    }

    public void setNegativeButtonListener(ButtonClickedListener negativeButtonListener) {
        mNegativeButtonListener = negativeButtonListener;
    }

    public ButtonClickedListener getNegativeButtonListener() {
        return mNegativeButtonListener;
    }
}
