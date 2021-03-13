package com.jiahan.fave.core.recyclerView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.R;

import java.util.List;

public class RecyclerViewLabelAdapterDelegate extends ListAdapterDelegate<LabelItemViewModel, RecyclerViewItemViewModel, com.jiahan.fave.core.recyclerView.RecyclerViewLabelAdapterDelegate.ViewHolder> {
    Context mContext;

    public RecyclerViewLabelAdapterDelegate(Context context) {
        super(LayoutInflater.from(context));
        mContext = context;
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof LabelItemViewModel;
    }

    @NonNull
    @Override
    protected com.jiahan.fave.core.recyclerView.RecyclerViewLabelAdapterDelegate.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        AppCompatTextView textView = new AppCompatTextView(parent.getContext());
        return new ViewHolder(textView);
    }

    @Override
    protected void onBindViewHolder(@NonNull final LabelItemViewModel item, @NonNull final com.jiahan.fave.core.recyclerView.RecyclerViewLabelAdapterDelegate.ViewHolder holder, @NonNull final List<Object> payloads) {
        holder.mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, item.mTextSize);
        holder.mTextView.setText(item.mText);
        holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
        Typeface typeface = null;
        switch (item.mLabelType) {
            case REGULAR:
                typeface = ResourcesCompat.getFont(mContext, R.font.nunito_regular);
                break;
            case MEDIUM:
                typeface = ResourcesCompat.getFont(mContext, R.font.nunito_medium);
                break;
            case SEMI_BOLD:
                typeface = ResourcesCompat.getFont(mContext, R.font.nunito_semi_bold);
                break;
            case BOLD:
                typeface = ResourcesCompat.getFont(mContext, R.font.nunito_bold);
                break;
            case EXTRA_BOLD:
                typeface = ResourcesCompat.getFont(mContext, R.font.nunito_extra_bold);
                break;
        }
        holder.mTextView.setPadding(mContext.getResources().getDimensionPixelSize(item.mPaddingLeft),
                mContext.getResources().getDimensionPixelSize(item.mPaddingTop),
                mContext.getResources().getDimensionPixelSize(item.mPaddingRight),
                mContext.getResources().getDimensionPixelSize(item.mPaddingBottom));
        holder.mTextView.setTypeface(typeface);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView mTextView;

        ViewHolder(final AppCompatTextView textView) {
            super(textView);
            mTextView = textView;
        }
    }
}