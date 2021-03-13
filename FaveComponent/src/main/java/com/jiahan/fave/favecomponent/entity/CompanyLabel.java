package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyLabel {
    @SerializedName("name")
    public String       name;
    @SerializedName("value")
    public List<String> value;

}