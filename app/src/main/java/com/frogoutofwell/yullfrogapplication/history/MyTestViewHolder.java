package com.frogoutofwell.yullfrogapplication.history;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class MyTestViewHolder extends RecyclerView.ViewHolder {

    ImageView logoView;
    TextView writeDateView, classView, termView, questionView, levelView, resultView;

    TestDetail testDetail;

    public interface OnItemClickListener {
        public void onItemClick(View view, TestDetail testDetail);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MyTestViewHolder(View itemView) {
        super(itemView);
        logoView = (ImageView)itemView.findViewById(R.id.img_logo);
        writeDateView = (TextView)itemView.findViewById(R.id.text_writedate);
        classView = (TextView)itemView.findViewById(R.id.text_classinfo);
        termView = (TextView)itemView.findViewById(R.id.text_term);
        questionView = (TextView)itemView.findViewById(R.id.text_question);
        levelView = (TextView)itemView.findViewById(R.id.text_level);
        resultView = (TextView)itemView.findViewById(R.id.text_info);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v,testDetail);
                }
            }
        });
    }

    public void setMyTest(TestDetail testDetail){
        this.testDetail = testDetail;

        writeDateView.setText(testDetail.getWriteDate());
        classView.setText("삼성전자 마케터");
        termView.setText(testDetail.getTerm());
        questionView.setText(testDetail.getQuestion());
        levelView.setText("면접 난이도 " + testDetail.getLevel());
        resultView.setText("면접 결과 " + testDetail.getResult());

    }

}
