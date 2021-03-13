package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OutletTiming {
    /**
     * outlet_business_hours : [{"hours":["1:00PM - 3:00PM","3:00PM - 11:00PM"],"day":"sunday","open":true},{"hours":["12:00AM - 11:59PM"],"formatted_time":"12:00AM - 11:59PM","day":"monday","open":true},{"hours":["12:00AM - 11:59PM"],"formatted_time":"12:00AM - 11:59PM","day":"tuesday","open":true},{"hours":["12:00AM - 11:59PM"],"formatted_time":"12:00AM - 11:59PM","day":"wednesday","open":true},{"hours":["12:00AM - 11:59PM"],"formatted_time":"12:00AM - 11:59PM","day":"thursday","open":true},{"hours":["12:00AM - 11:59PM"],"formatted_time":"12:00AM - 11:59PM","day":"friday","open":true},{"hours":["12:00AM - 11:59PM"],"formatted_time":"12:00AM - 11:59PM","day":"saturday","open":false}]
     * open_now : true
     * next_closing_hour : 11:59PM
     * next_open_hour : 12:00AM
     */
    @SerializedName("outlet_business_hours")
    public List<OutletBusinessHour> outletBusinessHours;

    @SerializedName("open_now")
    public boolean openNow;

    @SerializedName("next_closing_hour")
    public String nextClosingHour;

    @SerializedName("next_open_hour")
    public String nextOpenHour;
}