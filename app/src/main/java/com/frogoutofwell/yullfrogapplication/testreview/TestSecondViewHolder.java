package com.frogoutofwell.yullfrogapplication.testreview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class TestSecondViewHolder extends RecyclerView.ViewHolder {

    TextView classInfoView, levelView, resultView;

    TestDetail testDetail;

    public TestSecondViewHolder(View itemView) {
        super(itemView);
        classInfoView = (TextView)itemView.findViewById(R.id.text_classinfo);
        levelView = (TextView)itemView.findViewById(R.id.text_level);
        resultView = (TextView)itemView.findViewById(R.id.text_result);
    }

    public void setTestSecond(TestDetail testDetail){
        this.testDetail = testDetail;
        classInfoView.setText(testDetail.getTerm());
        levelView.setText("면접 난이도 " + testDetail.getLevel());
        resultView.setText("면접 결과 " + testDetail.getResult());
    }
}