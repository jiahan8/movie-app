package com.jiahan.fave.favecomponent.network.pojo.response;

import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.favecomponent.entity.Menu;

import java.util.List;

public class FoodMenuResponse {
    @SerializedName("menu_items")
    public List<Menu> mMenuList;
}