package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.DoDetail;

/**
 * Created by Tacademy on 2016-05-17.
 */
public class DoBestViewHolder extends RecyclerView.ViewHolder {
    ImageView logoImgView;
    TextView nameView, rateView, classView, commentView, commentGoodView,commentBadView;

    DoDetail doDetail;

    public DoBestViewHolder(View itemView) {
        super(itemView);
        logoImgView = (ImageView) itemView.findViewById(R.id.img_logo);
        nameView = (TextView)itemView.findViewById(R.id.text_name);
        rateView = (TextView)itemView.findViewById(R.id.text_rate);
        classView = (TextView)itemView.findViewById(R.id.text_class);
        commentView = (TextView)itemView.findViewById(R.id.text_comment);
        commentGoodView = (TextView)itemView.findViewById(R.id.text_question);
        commentBadView = (TextView)itemView.findViewById(R.id.text_commentbad);

    }

    public void setDoBest(DoDetail doDetail){
        this.doDetail = doDetail;
        Log.i("DoDetail","DoDetail :"+doDetail);
       // Glide.with(logoImgView.getContext()).load(doDetail.getDoInter().getLogoImgUrl()).into(logoImgView);
        nameView.setText(doDetail.getActivityName());
        rateView.setText(doDetail.getRate()+" ");
        classView.setText(" ");
        commentView.setText(doDetail.getComment());
        commentGoodView.setText(doDetail.getCommentGood());
        commentBadView.setText(doDetail.getCommentBad());
    }

}
