package com.jiahan.fave.core.recyclerView;

import androidx.annotation.DimenRes;

import com.jiahan.fave.core.R;

public class LabelItemViewModel implements com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel {
    public final String    mText;
    public final int       mTextSize;
    public final LabelType mLabelType;
    public final int       mPaddingLeft;
    public final int       mPaddingTop;
    public final int       mPaddingRight;
    public final int       mPaddingBottom;

    public LabelItemViewModel(final String text,
                              final int textSize,
                              final LabelType type) {
        this(text, textSize, type, R.dimen.size_0, R.dimen.size_0, R.dimen.size_0, R.dimen.size_0);
    }

    public LabelItemViewModel(final String text,
                              final int textSize,
                              final LabelType type,
                              @DimenRes int padding) {
        this(text, textSize, type, padding, padding);
    }

    public LabelItemViewModel(final String text,
                              final int textSize,
                              final LabelType type,
                              @DimenRes int paddingLeftRight,
                              @DimenRes int paddingTopBottom) {
        this(text, textSize, type, paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
    }

    public LabelItemViewModel(final String text,
                              final int textSize,
                              final LabelType type,
                              @DimenRes int paddingLeft,
                              @DimenRes int paddingTop,
                              @DimenRes int paddingRight,
                              @DimenRes int paddingBottom) {
        mText = text;
        mTextSize = textSize;
        mLabelType = type;
        mPaddingLeft = paddingLeft;
        mPaddingTop = paddingTop;
        mPaddingRight = paddingRight;
        mPaddingBottom = paddingBottom;
    }

    public enum LabelType {
        BOLD,
        EXTRA_BOLD,
        MEDIUM,
        REGULAR,
        SEMI_BOLD
    }
}