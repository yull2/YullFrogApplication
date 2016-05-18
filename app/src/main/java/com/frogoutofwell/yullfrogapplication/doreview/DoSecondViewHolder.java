package com.frogoutofwell.yullfrogapplication.doreview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.DoDetail;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class DoSecondViewHolder extends RecyclerView.ViewHolder {

    TextView rateView, commentView;
    RatingBar rateBar;

    DoDetail doDetail;

    public DoSecondViewHolder(View itemView) {
        super(itemView);
        rateBar = (RatingBar)itemView.findViewById(R.id.ratebar);
        rateView = (TextView)itemView.findViewById(R.id.text_rate);
        commentView = (TextView)itemView.findViewById(R.id.text_comment);
    }

    public void setDoSecond(DoDetail doDetail){
        this.doDetail = doDetail;
        rateBar.setRating(doDetail.getRate());
        rateView.setText(doDetail.getRate()+" ");
        commentView.setText(doDetail.getComment());

    }

}
