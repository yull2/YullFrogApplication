package com.frogoutofwell.yullfrogapplication.doreview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;


/**
 * Created by Tacademy on 2016-05-18.
 */
public class DoRateViewHolder extends RecyclerView.ViewHolder {

    TextView rateView;
    ImageView star1View, star2View, star3View, star4View, star5View;
    ImageView rateBar;

    int total;
    int[] counts;

    int sum;

    public DoRateViewHolder(View itemView) {
        super(itemView);
        rateView = (TextView)itemView.findViewById(R.id.text_rate);
        rateBar = (ImageView)itemView.findViewById(R.id.img_rate);
        star1View = (ImageView)itemView.findViewById(R.id.inter_rate1);
        star2View = (ImageView)itemView.findViewById(R.id.inter_rate2);
        star3View = (ImageView)itemView.findViewById(R.id.inter_rate3);
        star4View = (ImageView)itemView.findViewById(R.id.inter_rate4);
        star5View = (ImageView)itemView.findViewById(R.id.inter_rate5);
    }

    public void setDoRate(int total, int[] star){
        this.total = total;
        this.counts = star;
        rateView.setText(total+"");

       // sum = (activityDetail.totalPostCountStar[0]+activityDetail.totalPostCountStar[1]+activityDetail.totalPostCountStar[2]+activityDetail.totalPostCountStar[3]+activityDetail.totalPostCountStar[4])/3;
        if (total == 0){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star0);
        }else if (total == 0.5){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star0_1);
        }else if (total == 1){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star1);
        }else if (total == 1.5){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star1_1);
        }else if (total == 2){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star2);
        }else if (total == 2.5){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star2_1);
        }else if (total == 3){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star3);
        }else if (total == 3.5){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star3_1);
        }else if (total == 4){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star4);
        }else if (total == 4.5){
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star4_1);
        }else {
            rateBar.setImageResource(R.drawable.activityreview_detail_ic_star5);
        }
    /*    star1View.setMaxWidth(activityDetail.totalPostCountStar[0]);
        star2View.setMaxWidth(activityDetail.totalPostCountStar[1]);
        star3View.setMaxWidth(activityDetail.totalPostCountStar[2]);
        star4View.setMaxWidth(activityDetail.totalPostCountStar[3]);
        star5View.setMaxWidth(activityDetail.totalPostCountStar[4]);
        */

    }
}
