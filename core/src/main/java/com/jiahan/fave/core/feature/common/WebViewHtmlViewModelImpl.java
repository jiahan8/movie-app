package com.jiahan.fave.core.feature.common;

import android.content.Context;

import com.jiahan.fave.core.common.BaseViewViewModelImpl;
import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

public class WebViewHtmlViewModelImpl extends BaseViewViewModelImpl implements WebViewHtmlViewModel {
    private final String mTitle;
    private final String mHtml;

    public WebViewHtmlViewModelImpl(final Context context,
                                    final String screenIdentifier,
                                    final EventSender eventSender,
                                    final String title,
                                    final String html) {
        super(context, screenIdentifier, eventSender);
        mTitle = title;
        mHtml = html;
        sendScreenDisplayedEvent();
    }

    @Override
    protected void sendScreenDisplayedEvent() {
        if (mEventSender == null) {
            return;
        }
        EventActions actions = mEventSender.screenDisplayed(getScreenIdentifier());
        actions.addProperty(PropertyConstant.Key.TITLE, mTitle);
        mEventSender.send(actions);
    }

    @Override
    public String getHtml() {
        return mHtml;
    }
}