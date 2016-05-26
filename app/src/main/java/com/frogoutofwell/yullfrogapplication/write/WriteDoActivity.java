package com.frogoutofwell.yullfrogapplication.write;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetailResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

public class WriteDoActivity extends AppCompatActivity {

    TextView infoView;
    EditText commentView, commentGoodView, commentBadView;
    RatingBar ratebar;
    Spinner spinner_term;
    String tmp_term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_do);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infoView = (TextView)findViewById(R.id.text_info);
        commentView = (EditText)findViewById(R.id.edit_comment);
        commentGoodView = (EditText)findViewById(R.id.edit_commentgood);
        commentBadView = (EditText)findViewById(R.id.edit_commentbad);
        ratebar = (RatingBar)findViewById(R.id.ratebar);
        spinner_term = (Spinner)findViewById(R.id.spinner_term);

        setData();

        spinner_term.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(WriteDoActivity.this, "term // position : "+ position+", id : "+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                tmp_term = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ratebar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.i("rating","rating :  "+rating+", ratebar.getRating() :"+ratebar.getRating());
            }
        });

        Button btn_upload = (Button)findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentView.getText().toString();
                String commentGood = commentGoodView.getText().toString();
                String commentBad = commentBadView.getText().toString();
                float rate = ratebar.getRating();
                String term = tmp_term;
                reviewUpload(2, 2, rate, term, comment, commentGood, commentBad);
            }
        });
    }

    private void reviewUpload( int activitySeq, int writer, float rate, String term, String comment, String commentGood, String commentBad){
        Log.i("reviewUpload", "reviewUpload : "+rate+", comment: "+comment+", commentGood : "+commentGood+", commentBad: "+commentBad);
        NetworkManager.getInstance().getFrogDoReviewPost(this, activitySeq, writer, rate, term, comment, commentGood, commentBad, new NetworkManager.OnResultListener<String>() {
            @Override
            public void onSuccess(Request request, String result) {
                String status = result;
                Toast.makeText(WriteDoActivity.this, "업로드 상태 "+ status, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(WriteDoActivity.this, "fail : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        NetworkManager.getInstance().getInterClassInfo(this, 2, new NetworkManager.OnResultListener<ActivityDetailResult>() {
            @Override
            public void onSuccess(Request request, ActivityDetailResult result) {
                String actName = result.activityDetail.getName();
                String actClass = result.activityDetail.getActClass();
                String comName = result.activityDetail.getCompanyName();
                String region = result.activityDetail.getRegion();
                infoView.setText(actName+" / "+actClass+" / "+comName+" / "+region);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(WriteDoActivity.this, "fail : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
