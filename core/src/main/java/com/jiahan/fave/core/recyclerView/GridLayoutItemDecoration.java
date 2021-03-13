package com.jiahan.fave.core.recyclerView;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.common.CoreApplication;

import org.jetbrains.annotations.NotNull;

public class GridLayoutItemDecoration extends RecyclerView.ItemDecoration {
    private final int     mSpanCount;
    private final int     mSpacingVertical;
    private final int     mSpacingHorizontal;
    private final boolean mIncludeEdge;

    public GridLayoutItemDecoration(int spanCount, @DimenRes int spacing, boolean includeEdge) {
        mSpanCount = spanCount;
        mSpacingVertical = CoreApplication.getInstance().getResources().getDimensionPixelSize(spacing);
        mSpacingHorizontal = mSpacingVertical;
        mIncludeEdge = includeEdge;
    }

    public GridLayoutItemDecoration(int spanCount, @DimenRes int spacingHorizontal, @DimenRes int spacingVertical, boolean includeEdge) {
        mSpanCount = spanCount;
        mSpacingHorizontal = CoreApplication.getInstance().getResources().getDimensionPixelSize(spacingHorizontal);
        mSpacingVertical = CoreApplication.getInstance().getResources().getDimensionPixelSize(spacingVertical);
        mIncludeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, RecyclerView parent, @NotNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % mSpanCount;
        if (mIncludeEdge) {
            outRect.left = mSpacingHorizontal - column * mSpacingHorizontal / mSpanCount;
            outRect.right = (column + 1) * mSpacingHorizontal / mSpanCount;

            if (position < mSpanCount) {
                outRect.top = mSpacingVertical;
            }
            outRect.bottom = mSpacingVertical;
            return;
        }
        outRect.left = column * mSpacingHorizontal / mSpanCount;
        outRect.right = mSpacingHorizontal - (column + 1) * mSpacingHorizontal / mSpanCount;
        if (position >= mSpanCount) {
            outRect.top = mSpacingVertical;
        }
    }
}