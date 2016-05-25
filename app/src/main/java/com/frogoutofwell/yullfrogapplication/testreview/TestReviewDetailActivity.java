package com.frogoutofwell.yullfrogapplication.testreview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.TestDetailResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

public class TestReviewDetailActivity extends AppCompatActivity {

    TextView termView, levelView, resultView, questionView, answerView, wayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_review_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        termView = (TextView)findViewById(R.id.text_term);
        levelView = (TextView)findViewById(R.id.text_level);
        resultView = (TextView)findViewById(R.id.text_result);
        questionView = (TextView)findViewById(R.id.text_question);
        answerView = (TextView)findViewById(R.id.text_answer);
        wayView = (TextView)findViewById(R.id.text_way);

        setData();
    }
    private void setData() {
        NetworkManager.getInstance().getInterTestReviewDetail(this, 2, new NetworkManager.OnResultListener<TestDetailResult>() {
            @Override
            public void onSuccess(Request request, TestDetailResult result) {
                termView.setText(result.testDetail.getTerm());
                levelView.setText(result.testDetail.getLevel() +" ");
                resultView.setText(result.testDetail.getResult() +" ");
                questionView.setText(result.testDetail.getQuestion());
                answerView.setText(result.testDetail.getAnswer());
                wayView.setText(result.testDetail.getWay());
            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });

    }

}
