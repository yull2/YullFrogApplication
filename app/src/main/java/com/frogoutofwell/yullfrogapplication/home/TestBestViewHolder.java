package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;

/**
 * Created by Tacademy on 2016-05-17.
 */
public class TestBestViewHolder extends RecyclerView.ViewHolder {
    ImageView logoImgView;
    TextView nameView, levelView, resultView ,questionView, answerView;

    TestDetail testDetail;


    public interface OnDetailClickListener{
        public void onItemClick(View view, int seq);
    }

    OnDetailClickListener mListener;
    public void setOnDetailClickListener(OnDetailClickListener listener) {
        mListener = listener;
    }


    public TestBestViewHolder(View itemView) {
        super(itemView);
        logoImgView = (ImageView) itemView.findViewById(R.id.img_logo);
        nameView = (TextView)itemView.findViewById(R.id.text_name);
        levelView = (TextView)itemView.findViewById(R.id.text_level);
        resultView = (TextView)itemView.findViewById(R.id.text_result);
        questionView = (TextView)itemView.findViewById(R.id.text_question);
        answerView = (TextView)itemView.findViewById(R.id.text_answer);

        Button btn_detail = (Button)itemView.findViewById(R.id.btn_detail);
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("button","버트느느느느느 눌렷다 "+testDetail.getSeq());
                if (mListener != null) {
                    mListener.onItemClick(v,testDetail.getSeq());
                }
            }
        });
    }

    public void setTestBest(TestDetail testDetail){
        this.testDetail = testDetail;
        // Glide.with(logoImgView.getContext()).load(doDetail.getDoInter().getLogoImgUrl()).into(logoImgView);
        nameView.setText(testDetail.getActivityName());
        levelView.setText(testDetail.getLevel()+" ");
        resultView.setText(testDetail.getResult()+" ");
        questionView.setText(testDetail.getQuestion());
        answerView.setText(testDetail.getAnswer());
    }


}
