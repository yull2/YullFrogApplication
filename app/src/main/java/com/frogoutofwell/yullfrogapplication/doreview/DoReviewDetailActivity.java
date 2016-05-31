package com.frogoutofwell.yullfrogapplication.doreview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.DoDetailResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

public class DoReviewDetailActivity extends AppCompatActivity {

    TextView commentView, rateView, termView, writeDateView, commentGoodView, commentBadView;
    RatingBar ratebar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_review_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        commentView = (TextView)findViewById(R.id.text_comment);
        rateView = (TextView)findViewById(R.id.text_rate);
        termView = (TextView)findViewById(R.id.text_term);
        writeDateView = (TextView)findViewById(R.id.text_writedate);
        commentGoodView = (TextView)findViewById(R.id.text_commentgood);
        commentBadView = (TextView)findViewById(R.id.text_commentbad);
        ratebar = (RatingBar)findViewById(R.id.ratebar);

        setData();
    }

    private void setData() {
        NetworkManager.getInstance().getInterDoReviewDetail(this, 2, new NetworkManager.OnResultListener<DoDetailResult>() {
            @Override
            public void onSuccess(Request request, DoDetailResult result) {
                getSupportActionBar().setTitle(result.doDetail.getActivityName());
                commentView.setText(result.doDetail.getComment());
                rateView.setText(result.doDetail.getRate() +" ");
                termView.setText(result.doDetail.getTerm());
                writeDateView.setText(result.doDetail.getWriteDate());
                commentGoodView.setText(result.doDetail.getCommentGood());
                commentBadView.setText(result.doDetail.getCommentBad());
                ratebar.setRating(result.doDetail.getRate());
            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });

    }

}
