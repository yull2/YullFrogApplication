package com.frogoutofwell.yullfrogapplication.doreview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;


/**
 * Created by Tacademy on 2016-05-18.
 */
public class DoRateViewHolder extends RecyclerView.ViewHolder {

    TextView rateView;
    ImageView rateBar;

    ActivityDetail activityDetail;

    public DoRateViewHolder(View itemView) {
        super(itemView);
        rateView = (TextView)itemView.findViewById(R.id.text_rate);
        rateBar = (ImageView)itemView.findViewById(R.id.img_rate);
    }

    public void setDoRate(ActivityDetail activityDetail){
        this.activityDetail = activityDetail;
        rateView.setText(activityDetail.getAverageRate() + " ");

    }
}
