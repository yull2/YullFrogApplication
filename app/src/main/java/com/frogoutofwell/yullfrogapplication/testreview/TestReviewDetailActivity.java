package com.frogoutofwell.yullfrogapplication.testreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


        termView = (TextView)findViewById(R.id.text_term);
        levelView = (TextView)findViewById(R.id.text_level);
        resultView = (TextView)findViewById(R.id.text_info);
        questionView = (TextView)findViewById(R.id.text_question);
        answerView = (TextView)findViewById(R.id.text_answer);
        wayView = (TextView)findViewById(R.id.text_way);

        setData();
    }
    private void setData() {
        NetworkManager.getInstance().getInterTestReviewDetail(this, 2, new NetworkManager.OnResultListener<TestDetailResult>() {
            @Override
            public void onSuccess(Request request, TestDetailResult result) {
                getSupportActionBar().setTitle(result.testDetail.getActivityName());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
