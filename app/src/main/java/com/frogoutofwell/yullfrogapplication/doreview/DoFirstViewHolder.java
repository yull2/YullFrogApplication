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
public class DoFirstViewHolder extends RecyclerView.ViewHolder {

    TextView rateView, commentView, commentGoodView, commentBadView;
    RatingBar rateBar;

    DoDetail doDetail;
    public interface OnItemClickListener {
        public void onItemClick(View view, DoDetail doDetail);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DoFirstViewHolder(View itemView) {
        super(itemView);
        rateBar = (RatingBar)itemView.findViewById(R.id.ratebar);
        rateView = (TextView)itemView.findViewById(R.id.text_rate);
        commentView = (TextView)itemView.findViewById(R.id.text_comment);
        commentGoodView = (TextView)itemView.findViewById(R.id.text_commentgood);
        commentBadView = (TextView)itemView.findViewById(R.id.text_commentbad);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v,doDetail);
                }
            }
        });
    }

    public void setDoFirst(DoDetail doDetail){
        this.doDetail = doDetail;
        rateBar.setRating(doDetail.getRate());
        rateView.setText(doDetail.getRate()+" ");
        commentView.setText(doDetail.getComment());
        commentGoodView.setText(doDetail.getCommentGood());
        commentBadView.setText(doDetail.getCommentBad());
    }

}
