package com.jiahan.fave.favecomponent.network.pojo.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.network.pojo.response.BaseResponse;
import com.jiahan.fave.favecomponent.entity.MainCategory;
import com.jiahan.fave.favecomponent.entity.Product;

import java.util.List;

public class MainCategoryResponse extends BaseResponse {
    @SerializedName("products")
    @Nullable
    public List<Product> mProductList;

    @SerializedName("main_categories")
    @Nullable
    public List<MainCategory> mMainCategoryList;
}