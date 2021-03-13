package com.jiahan.fave.favecomponent.component.location;

public class CountryItemViewModelImpl implements CountryItemViewModel {
    private final String mText;
    private final String mIcon;

    public CountryItemViewModelImpl(final String text, final String icon) {
        mText = text;
        mIcon = icon;
    }

    @Override
    public String getName() {
        return mText;
    }

    @Override
    public String getIcon() {
        return mIcon;
    }
}