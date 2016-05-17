package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;

/**
 * Created by Tacademy on 2016-05-17.
 */
public class TestBestViewHolder extends RecyclerView.ViewHolder {
    ImageView logoImgView;
    TextView nameView, rateView, classView, commentView, commentGoodView,commentBadView;

    public TestBestViewHolder(View itemView) {
        super(itemView);
        logoImgView = (ImageView) itemView.findViewById(R.id.img_logo);
        nameView = (TextView)itemView.findViewById(R.id.text_name);
        rateView = (TextView)itemView.findViewById(R.id.text_rate);
        classView = (TextView)itemView.findViewById(R.id.text_class);
        commentView = (TextView)itemView.findViewById(R.id.text_comment);
        commentGoodView = (TextView)itemView.findViewById(R.id.text_commentgood);
        commentBadView = (TextView)itemView.findViewById(R.id.text_commentbad);


    }


}
