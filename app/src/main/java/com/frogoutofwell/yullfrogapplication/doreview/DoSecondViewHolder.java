package com.frogoutofwell.yullfrogapplication.doreview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.DoDetail;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class DoSecondViewHolder extends RecyclerView.ViewHolder {

    TextView rateView, commentView, commentGoodView, commentBadView;
    RatingBar rateBar;

    DoDetail doDetail;

    public interface OnSecondItemClickListener {
        public void onItemClick(View view, int seq);
    }

    OnSecondItemClickListener mListener;
    public void setOnItemClickListener(OnSecondItemClickListener listener) {
        mListener = listener;
    }


    public DoSecondViewHolder(View itemView) {
        super(itemView);
        rateBar = (RatingBar)itemView.findViewById(R.id.ratebar);
        rateView = (TextView)itemView.findViewById(R.id.text_rate);
        commentView = (TextView)itemView.findViewById(R.id.text_comment);
        commentGoodView = (TextView)itemView.findViewById(R.id.text_question);
        commentBadView = (TextView)itemView.findViewById(R.id.text_commentbad);
        Button btn_detail = (Button)itemView.findViewById(R.id.btn_detail);
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v,doDetail.getSeq());
                }
            }
        });
    }

    public void setDoSecond(DoDetail doDetail){
        this.doDetail = doDetail;
        rateBar.setRating(doDetail.getRate());
        rateView.setText(doDetail.getRate()+" ");
        commentView.setText(doDetail.getComment());
        commentGoodView.setText(doDetail.getCommentGood());
        commentBadView.setText(doDetail.getCommentBad());
    }

}
