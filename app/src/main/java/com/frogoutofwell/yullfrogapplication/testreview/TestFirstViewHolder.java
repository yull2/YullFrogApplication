package com.frogoutofwell.yullfrogapplication.testreview;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class TestFirstViewHolder extends RecyclerView.ViewHolder {

    TextView classInfoView, levelView, resultView, questionView, answerView;

    TestDetail testDetail;
    public interface OnItemClickListener {
        public void onItemClick(View view, TestDetail testDetail);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public TestFirstViewHolder(View itemView) {
        super(itemView);
        classInfoView = (TextView)itemView.findViewById(R.id.text_classinfo);
        levelView = (TextView)itemView.findViewById(R.id.text_level);
        resultView = (TextView)itemView.findViewById(R.id.text_result);
        questionView = (TextView)itemView.findViewById(R.id.text_question);
        answerView = (TextView)itemView.findViewById(R.id.text_answer);
        Button btn = (Button)itemView.findViewById(R.id.btn_detail);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v,testDetail);
                }
            }
        });
    }

    public void setTestFirst(TestDetail testDetail){
        this.testDetail = testDetail;
        classInfoView.setText(testDetail.getTerm());
        levelView.setText("면접 난이도 " + testDetail.getLevel());
        resultView.setText("면접 결과 " + testDetail.getResult());
        questionView.setText(testDetail.getQuestion());
        answerView.setText(testDetail.getAnswer());

    }

}
