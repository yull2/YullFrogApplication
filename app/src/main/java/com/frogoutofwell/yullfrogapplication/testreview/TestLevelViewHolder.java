package com.frogoutofwell.yullfrogapplication.testreview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.frogoutofwell.yullfrogapplication.R;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class TestLevelViewHolder extends RecyclerView.ViewHolder{
    ImageView levelView;

    public TestLevelViewHolder(View itemView) {
        super(itemView);
        levelView = (ImageView)itemView.findViewById(R.id.img_level);
    }

    public void setLevelImage(String srcImg){
        Glide.with(levelView.getContext()).load(srcImg).into(levelView);
    }
}
