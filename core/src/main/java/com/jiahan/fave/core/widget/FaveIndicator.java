package com.jiahan.fave.core.widget;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.jiahan.fave.core.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("CustomViewStyleable")
public class FaveIndicator extends LinearLayout {
    private static final int   DEFAULT_POINT_COLOR  = Color.CYAN;
    public static final  float DEFAULT_WIDTH_FACTOR = 2.5f;
    private static final int   LIMIT                = 4;

    private List<ImageView> dots;
    private ViewPager viewPager;
    private float           dotsSize;
    private float           dotsCornerRadius;
    private float           dotsSpacing;
    private int             currentPage;
    private float           dotsWidthFactor;
    private int             dotsColor;
    private int             totalItem;
    private ArgbEvaluator   argbEvaluator    = new ArgbEvaluator();
    private int             selectedDotColor = 0;
    private int             currentDotIndex  = 0;

    private boolean                        dotsClickable;
    private ViewPager.OnPageChangeListener pageChangedListener;

    public FaveIndicator(Context context) {
        super(context);
        init(null);
    }

    public FaveIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FaveIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        dots = new ArrayList<>();
        setOrientation(HORIZONTAL);

        dotsSize = dpToPx(16); // 16dp
        dotsSpacing = dpToPx(4); // 4dp
        dotsCornerRadius = dotsSize / 2;

        dotsWidthFactor = DEFAULT_WIDTH_FACTOR;
        dotsColor = DEFAULT_POINT_COLOR;
        dotsClickable = true;

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DotsIndicator);
            dotsColor = a.getColor(R.styleable.DotsIndicator_dotsColor, DEFAULT_POINT_COLOR);
            setUpCircleColors(dotsColor);
            selectedDotColor = a.getColor(R.styleable.DotsIndicator_selectedDotColor, DEFAULT_POINT_COLOR);
            dotsWidthFactor = a.getFloat(R.styleable.DotsIndicator_dotsWidthFactor, 2.5f);
            if (dotsWidthFactor < 1) {
                dotsWidthFactor = 2.5f;
            }
            dotsSize = a.getDimension(R.styleable.DotsIndicator_dotsSize, dotsSize);
            dotsCornerRadius =
                    (int) a.getDimension(R.styleable.DotsIndicator_dotsCornerRadius, dotsSize / 2);
            dotsSpacing = a.getDimension(R.styleable.DotsIndicator_dotsSpacing, dotsSpacing);
            a.recycle();
        }
        else {
            setUpCircleColors(DEFAULT_POINT_COLOR);
        }

        if (isInEditMode()) {
            addDots(5);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        refreshDots();
    }

    private void refreshDots() {
        if (viewPager != null && viewPager.getAdapter() != null) {
            totalItem = viewPager.getAdapter().getCount();

            if (dots.size() < LIMIT && dots.size() < totalItem) {
                addDots(Math.min(totalItem, LIMIT) - dots.size());
            }
            else if (dots.size() > LIMIT && dots.size() > totalItem) {
                removeDots(dots.size() - Math.min(totalItem, LIMIT));
            }

            if(dots.size() > currentDotIndex) {
                setDotWidth(dots.get(currentDotIndex), (int) (dotsSize * dotsWidthFactor));
                ((GradientDrawable) dots.get(currentDotIndex).getBackground()).setColor(selectedDotColor);
            }

            setUpOnPageChangedListener();
        }
    }

    private void addDots(int count) {
        for (int i = 0; i < count; i++) {
            View dot = LayoutInflater.from(getContext()).inflate(R.layout.dot_layout, this, false);
            ImageView imageView = dot.findViewById(R.id.dot);
            RelativeLayout.LayoutParams params =
                    (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            params.width = params.height = (int) dotsSize;
            params.setMargins((int) dotsSpacing, 0, (int) dotsSpacing, 0);
            ((GradientDrawable) imageView.getBackground()).setCornerRadius(dotsCornerRadius);
            ((GradientDrawable) imageView.getBackground()).setColor(dotsColor);

            final int finalI = i;
            dot.setOnClickListener(v -> {
                if (dotsClickable
                        && viewPager != null
                        && viewPager.getAdapter() != null
                        && finalI < viewPager.getAdapter().getCount()) {
                    viewPager.setCurrentItem(finalI, true);
                }
            });

            dots.add(imageView);
            addView(dot);
        }
    }

    private void removeDots(int count) {
        for (int i = 0; i < count; i++) {
            removeViewAt(getChildCount() - 1);
            dots.remove(dots.size() - 1);
        }
    }

    private void setUpOnPageChangedListener() {
        totalItem = viewPager.getAdapter().getCount();
        pageChangedListener = new ViewPager.OnPageChangeListener() {
            private int lastPage;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int actualPosition = position % totalItem;
                if (actualPosition != currentPage && positionOffset == 0 || currentPage < actualPosition) {
                    currentPage = actualPosition;
                }

                if (Math.abs(currentPage - actualPosition) > 1) {
                    currentPage = lastPage;
                }

                // do nothing if index is Range from 1 to totalItem - LIMIT
                if (totalItem > LIMIT && currentPage >= 1 && currentPage <= totalItem - LIMIT) {
                    return;
                }

                if (totalItem > LIMIT && currentPage != 0) {
                    currentDotIndex = Math.abs(totalItem - currentPage - LIMIT);
                }
                else {
                    currentDotIndex = currentPage;
                }

                ImageView dot = null;
                ImageView nextDot = null;
                // scrolling next
                if (currentPage == actualPosition && currentPage + 1 < totalItem) {
                    dot = dots.get(currentDotIndex);
                    nextDot = dots.get(currentDotIndex + 1);
                }
                // scrolling back
                else if (currentPage > actualPosition) {
                    if (currentDotIndex == 1 && currentDotIndex != currentPage)
                        return;
                    nextDot = dots.get(currentDotIndex);
                    dot = dots.get(currentDotIndex - 1);
                }

                if (dot != null) {
                    int dotWidth = (int) (dotsSize + (dotsSize * (dotsWidthFactor - 1) * (1 - positionOffset)));
                    setDotWidth(dot, dotWidth);

                    GradientDrawable selectedDotBackground = (GradientDrawable) dot.getBackground();
                    int selectedColor = (Integer) argbEvaluator.evaluate(positionOffset, selectedDotColor, dotsColor);
                    selectedDotBackground.setColor(selectedColor);
                }

                if (nextDot != null) {
                    int nextDotWidth = (int) (dotsSize + (dotsSize * (dotsWidthFactor - 1) * (positionOffset)));
                    setDotWidth(nextDot, nextDotWidth);

                    GradientDrawable nextDotBackground = (GradientDrawable) nextDot.getBackground();
                    int nextColor = (Integer) argbEvaluator.evaluate(positionOffset, dotsColor, selectedDotColor);
                    nextDotBackground.setColor(nextColor);
                }

                lastPage = actualPosition;
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };

        viewPager.addOnPageChangeListener(pageChangedListener);
    }

    private void setDotWidth(ImageView dot, int dotWidth) {
        ViewGroup.LayoutParams dotParams = dot.getLayoutParams();
        dotParams.width = dotWidth;
        dot.setLayoutParams(dotParams);
    }

    private void setUpCircleColors(int color) {
        if (dots != null) {
            for (ImageView elevationItem : dots) {
                ((GradientDrawable) elevationItem.getBackground()).setColor(color);
            }
        }
    }


    private void setUpViewPager() {
        if (viewPager.getAdapter() != null) {
            viewPager.getAdapter().registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    refreshDots();
                }
            });
        }
    }

    private int dpToPx(int dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp);
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        if (viewPager != null) {
            setUpViewPager();
            refreshDots();
        }
    }
}
