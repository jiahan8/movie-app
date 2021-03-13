package com.jiahan.fave.core.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

import org.jetbrains.annotations.NotNull;

public class ExpandableTextView extends NunitoRegularTextView {
    private static final int MAX_LINES = 4;

    private final Context mContext;

    private int    mColor;
    private String mViewMore, mViewLess, mFullText;
    private ExpandableTextViewTracker mTracker;
    private EventSender               mEventSender;
    private String                    mScreenIdentifier;

    public ExpandableTextView(final Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ExpandableTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public ExpandableTextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void setEventSender(@NotNull final EventSender eventSender) {
        mEventSender = eventSender;
    }

    public void setScreenIdentifier(@NonNull final String screenIdentifier) {
        mScreenIdentifier = screenIdentifier;
    }

    public void setFullText(@NonNull final String fullText) {
        setMaxLines(Integer.MAX_VALUE);
        mFullText = fullText;
        setText(mFullText);
        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
                if (getLayout() != null && getLayout().getLineCount() > MAX_LINES) {
                    showLess();
                }
            }
        });
    }

    private void init() {
        mTracker = new ExpandableTextViewTracker();
        mColor = ContextCompat.getColor(mContext, R.color.colorPrimary);
        mViewMore = mContext.getString(R.string.show_more);
        mViewLess = mContext.getString(R.string.show_less);
        setMovementMethod(LinkMovementMethod.getInstance());
        setMaxLines(MAX_LINES);
        setFullText(getText().toString());
    }

    private void showLess() {
        int lineEndIndex = getLayout().getLineEnd(MAX_LINES - 1);
        int end = lineEndIndex - (mViewMore.length() + 10);
        if (end > 0) {
            String newText = mFullText.substring(0, end) + "... " + mViewMore;
            SpannableString string = new SpannableString(newText);
            string.setSpan(new ClickableSpan() {
                               @Override
                               public void onClick(@NotNull View widget) {
                                   showMore();
                               }

                               @Override
                               public void updateDrawState(@NotNull TextPaint ds) {
                                   ds.setColor(mColor);
                                   Typeface typeface = ResourcesCompat.getFont(mContext, R.font.nunito_bold);
                                   ds.setTypeface(typeface);
                                   ds.setUnderlineText(false);
                               }
                           }, newText.length() - mViewMore.length(), newText.length()
                    , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            setText(string);
        }
    }

    private void showMore() {
        mTracker.onTapShowMore();
        SpannableString string = new SpannableString(mFullText + " " + mViewLess);
        string.setSpan(new ClickableSpan() {
                           @Override
                           public void onClick(@NotNull View widget) {
                               showLess();
                           }

                           @Override
                           public void updateDrawState(@NotNull TextPaint ds) {
                               ds.setColor(mColor);
                               Typeface typeface = ResourcesCompat.getFont(mContext, R.font.nunito_bold);
                               ds.setTypeface(typeface);
                               ds.setUnderlineText(false);
                           }
                       }, mFullText.length() + 1, mFullText.length() + mViewLess.length() + 1
                , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        setText(string);
    }

    private class ExpandableTextViewTracker {
        public void onTapShowMore() {
            EventActions actions = mEventSender.tap(PropertyConstant.Value.SHOW_MORE_ABOUT, mScreenIdentifier);
            mEventSender.send(actions);
        }
    }
}