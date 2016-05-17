package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Tacademy on 2016-05-13.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView;
    }
}
