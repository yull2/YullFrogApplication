package com.frogoutofwell.yullfrogapplication.history;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.DoDetail;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class MyDoViewHolder extends RecyclerView.ViewHolder {

    ImageView logoView;
    TextView writeDateView, classView, termView, commentView, rateView;
    RatingBar ratingBar;

    DoDetail doDetail;

    public interface OnItemClickListener {
        public void onItemClick(View view, DoDetail doDetail);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MyDoViewHolder(View itemView) {
        super(itemView);
        logoView = (ImageView)itemView.findViewById(R.id.img_logo);
        writeDateView = (TextView)itemView.findViewById(R.id.text_writedate);
        classView = (TextView)itemView.findViewById(R.id.text_classinfo);
        termView = (TextView)itemView.findViewById(R.id.text_term);
        commentView = (TextView)itemView.findViewById(R.id.text_comment);
        rateView = (TextView)itemView.findViewById(R.id.text_rate);
        ratingBar = (RatingBar)itemView.findViewById(R.id.ratebar);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v,doDetail);
                }
            }
        });
    }

    public void setMyDo(DoDetail doDetail){
        this.doDetail = doDetail;

        writeDateView.setText(doDetail.getWriteDate());
        classView.setText(doDetail.getCompanyName());
        termView.setText(doDetail.getTerm());
        commentView.setText(doDetail.getComment());
        ratingBar.setRating(doDetail.getRate());
        rateView.setText(doDetail.getRate() + " ");

    }

}
