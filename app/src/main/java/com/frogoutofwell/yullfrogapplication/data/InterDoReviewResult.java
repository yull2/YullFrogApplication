package com.frogoutofwell.yullfrogapplication.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tacademy on 2016-05-24.
 */
public class InterDoReviewResult {
    public int averageRate;
    public int[] totalPostCountStar;
    public int totalPostCount;

    @SerializedName("postscripts")
    public DoDetails doDetails;


}
