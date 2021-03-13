package com.jiahan.fave.core.recyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewDividerDecoration extends RecyclerView.ItemDecoration {
    private final int      mOrientation;
    private final boolean  mIncludeLast;
    private final Drawable mDivider;

    private final Rect mBounds = new Rect();

    public RecyclerViewDividerDecoration(final Context context,
                                         final int orientation,
                                         final @DrawableRes int resId) {
        mOrientation = orientation;
        mDivider = AppCompatResources.getDrawable(context, resId);
        mIncludeLast = false;
    }

    public RecyclerViewDividerDecoration(final Context context,
                                         final int orientation,
                                         final @DrawableRes int resId,
                                         final boolean includeLast) {
        mOrientation = orientation;
        mDivider = AppCompatResources.getDrawable(context, resId);
        mIncludeLast = includeLast;
    }

    public RecyclerViewDividerDecoration(final Context context,
                                         final int orientation) {
        mOrientation = orientation;
        final int[] ATTRS = new int[]{android.R.attr.listDivider};
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        mDivider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
        mIncludeLast = false;
    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        if (mDivider == null ||
                parent.getLayoutManager() == null ||
                (!mIncludeLast && parent.getLayoutManager().getItemCount() == parent.getChildAdapterPosition(view) + 1)) {
            outRect.set(0, 0, 0, 0);
            return;
        }
        if (mOrientation == LinearLayout.VERTICAL) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
        else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() == null || mDivider == null) {
            return;
        }
        if (mOrientation == LinearLayout.VERTICAL) {
            drawVertical(canvas, parent);
        }
        else {
            drawHorizontal(canvas, parent);
        }
    }

    private void drawVertical(final Canvas canvas, final RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        }
        else {
            left = 0;
            right = parent.getWidth();
        }
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            final int top = bottom - mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    private void drawHorizontal(final Canvas canvas, final RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        }
        else {
            top = 0;
            bottom = parent.getHeight();
        }
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            final int right = mBounds.right + Math.round(child.getTranslationX());
            final int left = right - mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }
}