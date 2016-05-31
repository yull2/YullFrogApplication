package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;

import java.util.List;

/**
 * Created by Tacademy on 2016-05-13.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView1,imageView2,imageView3;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView1 = (ImageView)itemView.findViewById(R.id.image_homemain);
        imageView2 = (ImageView)itemView.findViewById(R.id.image_homestart);
        imageView3 = (ImageView)itemView.findViewById(R.id.image_homeend);
    }
   public void setHomeImg(List<ActivityDetail> activityDetails){
       Glide.with(imageView2.getContext()).load(activityDetails.get(0).getGuideImg()).into(imageView2);
       Glide.with(imageView2.getContext()).load(activityDetails.get(1).getGuideImg()).into(imageView3);
    }
}
