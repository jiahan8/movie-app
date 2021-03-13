package com.jiahan.fave.core.widget;

/**
 * Created by zarea at 2020
 */
public class TabLayoutAttribute {
    String mName;
    String mTag;

    public TabLayoutAttribute(final String name, final String tag) {
        mName = name;
        mTag = tag;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(final String tag) {
        mTag = tag;
    }
}
