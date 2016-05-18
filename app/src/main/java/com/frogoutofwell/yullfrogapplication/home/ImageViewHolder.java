package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.frogoutofwell.yullfrogapplication.R;

/**
 * Created by Tacademy on 2016-05-13.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView1,imageView2,imageView3;
    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView1 = (ImageView)itemView.findViewById(R.id.image_homemain);
        imageView2 = (ImageView)itemView.findViewById(R.id.image_homemain);
        imageView3 = (ImageView)itemView.findViewById(R.id.image_homemain);
    }
   public void setHomeImg(){

    }
}
