package com.jiahan.fave.core.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.tabs.TabLayout;
import com.jiahan.fave.core.R;
import com.jiahan.fave.core.common.CoreApplication;
import com.jiahan.fave.core.widget.EllipsizeTextView;
import com.jiahan.fave.core.widget.FaveIndicator;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.List;

public class BindingAdapters {
    @BindingConversion
    public static int convertBooleanToVisibility(Boolean value) {
        return value ? View.VISIBLE : View.GONE;
    }

    @BindingConversion
    public static int convertBooleanToVisibility(ObservableBoolean value) {
        return value != null && value.get() ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter(value = {"layout_width", "layout_height"}, requireAll = false)
    public static void setLayoutSize(View view, int width, int height) {
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            final View.OnLayoutChangeListener listener = new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(final View myView, final int i, final int i1, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
                    myView.removeOnLayoutChangeListener(this);
                    myView.post(() -> {
                        final ViewGroup.LayoutParams newLayoutParam = myView.getLayoutParams();
                        if (newLayoutParam != null) {
                            if (width > 0) {
                                newLayoutParam.width = width;
                            }
                            if (height > 0) {
                                newLayoutParam.height = height;
                            }
                            myView.setLayoutParams(newLayoutParam);
                        }
                    });
                }
            };
            view.addOnLayoutChangeListener(listener);
            return;
        }
        if (width > 0) {
            layoutParams.width = width;
        }
        if (height > 0) {
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter(value = {"margins", "marginLeft", "marginTop", "marginRight", "marginBottom"}, requireAll = false)
    public static void bindMargin(final View view, final List<Integer> margins, final int marginLeft, final int marginTop, final int marginRight, final int marginBottom) {
        final ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (margins != null && margins.size() == 4) {
            layoutParams.setMargins(margins.get(0), margins.get(1), margins.get(2), margins.get(3));
            view.setLayoutParams(layoutParams);
            return;
        }
        layoutParams.leftMargin = marginLeft;
        layoutParams.topMargin = marginTop;
        layoutParams.rightMargin = marginRight;
        layoutParams.bottomMargin = marginBottom;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter(value = {"imageUrl", "roundAsCircle", "roundedCornerRadius", "applyOverlay", "scaleType"}, requireAll = false)
    public static void setImageUrl(ImageView imageView,
                                   String imageUrl,
                                   boolean roundAsCircle,
                                   float roundedCornerRadius,
                                   boolean applyOverlay,
                                   ImageView.ScaleType scaleType) {
        Context context = CoreApplication.getInstance();
        RequestBuilder<Drawable> requestBuilder = Glide.with(context)
                .asDrawable()
                .fitCenter()
                .load(imageUrl)
                .error(R.drawable.default_placeholder)
                .placeholder(R.drawable.default_placeholder);
        if (roundAsCircle) {
            requestBuilder = requestBuilder.apply(RequestOptions.circleCropTransform());
        }
        if (roundedCornerRadius != 0) {
            requestBuilder = requestBuilder.apply(RequestOptions.bitmapTransform(new RoundedCorners((int) roundedCornerRadius)));
        }
        if (scaleType != null) {
            imageView.setScaleType(scaleType);
        }
        if (!applyOverlay)
            requestBuilder.into(imageView);
        else
            requestBuilder.into(new CustomTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable drawable, @Nullable final Transition<? super Drawable> transition) {
                    imageView.setImageBitmap(BitmapUtils.applyOverlay(context, drawable, R.drawable.overlay));
                }

                @Override
                public void onLoadCleared(@Nullable final Drawable placeholderDrawable) {

                }

                @Override
                public void onLoadFailed(@Nullable final Drawable errorDrawable) {
                    imageView.setImageBitmap(BitmapUtils.applyOverlay(context, errorDrawable, R.drawable.overlay));
                }
            });
    }

    @BindingAdapter({"src"})
    public static void setImageResources(ImageView imageView, @DrawableRes int resourcesId) {
        imageView.setImageResource(resourcesId);
    }

    @BindingAdapter(value = {"adapter", "dotIndicator", "offscreenPageLimit", "currentItem", "onPageChangeListener"}, requireAll = false)
    public static void setVPAdapter(ViewPager viewpager, PagerAdapter pagerAdapter, ViewGroup dotsIndicator, int offscreenPageLimit, int currentItem, ViewPager.OnPageChangeListener listener) {
        if (pagerAdapter != null) {
            viewpager.setAdapter(pagerAdapter);
            viewpager.post(() -> viewpager.setCurrentItem(currentItem, false));
            viewpager.setOffscreenPageLimit(offscreenPageLimit);
            if (dotsIndicator != null) {
                if (dotsIndicator instanceof DotsIndicator) {
                    ((DotsIndicator) dotsIndicator).setViewPager(viewpager);
                }
                else if (dotsIndicator instanceof FaveIndicator) {
                    ((FaveIndicator) dotsIndicator).setViewPager(viewpager);
                }
            }
        }
        if (listener != null) {
            viewpager.setOnPageChangeListener(listener);
        }
    }

    @BindingAdapter({"ellipsizeIndex"})
    public static void expandableText(EllipsizeTextView textView, int ellipsizeIndex) {
        textView.setEllipsizeTextIndex(ellipsizeIndex);
    }

    @BindingAdapter({"childViews"})
    public static void bindChildViews(View parentView, List<View> childViews) {
        if (childViews != null) {
            if (parentView instanceof GridLayout) {
                GridLayout gridLayout = (GridLayout) parentView;
                gridLayout.removeAllViews();
                for (View view : childViews) {
                    ViewGroup parent = (ViewGroup) view.getParent();
                    if (parent != null) {
                        parent.removeView(view);
                    }
                    gridLayout.addView(view);
                }
            }
            else if (parentView instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) parentView;
                linearLayout.removeAllViews();
                for (View view : childViews) {
                    ViewGroup parent = (ViewGroup) view.getParent();
                    if (parent != null) {
                        parent.removeView(view);
                    }
                    linearLayout.addView(view);
                }
            }
            else if (parentView instanceof FlexboxLayout) {
                FlexboxLayout flexboxLayout = (FlexboxLayout) parentView;
                flexboxLayout.removeAllViews();
                for (View view : childViews) {
                    ViewGroup parent = (ViewGroup) view.getParent();
                    if (parent != null) {
                        parent.removeView(view);
                    }
                    flexboxLayout.addView(view);
                }
            }
        }
    }

    @BindingAdapter(value = {"adapter", "itemDecoration"}, requireAll = false)
    public static void bindRVAdapter(final RecyclerView recyclerView, final RecyclerView.Adapter adapter, final RecyclerView.ItemDecoration itemDecoration) {
        if (adapter == null) {
            return;
        }
        if (itemDecoration != null && recyclerView.getItemDecorationCount() == 0) {
            recyclerView.addItemDecoration(itemDecoration);
        }
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("tabWithViewPager")
    public static void bindTabWithViewPager(final TabLayout view, final ViewPager pagerView) {
        view.setupWithViewPager(pagerView, true);
    }

    @BindingAdapter("webViewLoadHtml")
    public static void webViewLoadHtml(WebView webView, String data) {
        if (!TextUtils.isEmpty(data)) {
            final WebSettings webSettings = webView.getSettings();
            webSettings.setDefaultTextEncodingName("UTF-8");
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (URLUtil.isValidUrl(url)) {
                        webView.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }
                    return true;
                }
            });
            webView.clearCache(true);
            webView.clearHistory();
            webView.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
        }
    }

    @BindingAdapter({"refreshing"})
    public static void bindRefreshing(SwipeRefreshLayout swipeRefreshLayout, boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @BindingAdapter({"onRefreshListener"})
    public static void bindOnRefreshListener(SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener listener) {
        swipeRefreshLayout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"onScrollListener"})
    public static void bindOnScrollListener(RecyclerView recyclerView, RecyclerView.OnScrollListener listener) {
        recyclerView.addOnScrollListener(listener);
    }

    @BindingAdapter({"onTextChangedListener"})
    public static void bindOnTextChangedListener(EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    @BindingAdapter({"onLongClickedListener"})
    public static void bindOnLongClickedListener(View view, View.OnLongClickListener listener) {
        view.setOnLongClickListener(listener);
    }

    @BindingAdapter("isBold")
    public static void setBold(TextView view, boolean isBold) {
        view.setTypeface(isBold ?
                ResourcesCompat.getFont(view.getContext(), R.font.nunito_extra_bold) :
                ResourcesCompat.getFont(view.getContext(), R.font.nunito_regular));
    }

    @BindingAdapter({"drawableStartAlign"})
    public static void drawableStartAlignId(TextView view, int drawableResource) {
        if (drawableResource == 0) {
            return;
        }
        drawableStartAlign(view, ResourcesCompat.getDrawable(view.getResources(), drawableResource, null));
    }

    @BindingAdapter({"drawableStartAlign"})
    public static void drawableStartAlign(TextView view, Drawable drawable) {
        if (drawable == null) {
            return;
        }
        view.setGravity(Gravity.CENTER_VERTICAL);
        int paddingTop = view.getResources().getDimensionPixelSize(R.dimen.size_1);
        drawable.setBounds(0,
                paddingTop,
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight() + paddingTop);
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
    }

    @BindingAdapter({"drawableEndAlign"})
    public static void drawableEndAlign(TextView view, Drawable drawable) {
        if (drawable == null) {
            return;
        }
        view.setGravity(Gravity.CENTER_VERTICAL);
        int paddingTop = view.getResources().getDimensionPixelSize(R.dimen.size_1);
        drawable.setBounds(0,
                paddingTop,
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight() + paddingTop);
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @BindingAdapter({"selected"})
    public static void bindSelected(View view, boolean selected) {
        view.setSelected(selected);
    }



    /**
     * Binding adapter used to hide view when data is null.
     */
    @BindingAdapter("goneIfNull")
    public static void goneIfNull(View view, Object it) {
        view.setVisibility( it == null ? View.GONE : View.VISIBLE );
    }

    /**
     * Binding adapter used to display images from URL using Fresco.
     */
    @BindingAdapter("imageUrl")
    public static void setImageUrl(SimpleDraweeView imageView, String movie) {
        if( movie != null )
            imageView.setImageURI( Utils.getImageURL(movie) );
        else
            imageView.setVisibility( View.GONE );
    }

    /**
     * Binding adapter used to truncate long text.
     */
    @BindingAdapter("movieTitle")
    public static void setMovieTitle(TextView textView, String title) {
        String sentence = title;
        if (sentence.length() > 60) {
            sentence = sentence.substring(0, 60) + "...";
        }
        textView.setText( sentence );
    }

    @BindingAdapter("webViewURL")
    public static void setWebViewURL(WebView webView, String url) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            webView.getSettings().setSafeBrowsingEnabled( false );
        }
        webView.getSettings().setJavaScriptEnabled( true );
        webView.loadUrl( url );
    }

}