package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;

/**
 * Created by Tacademy on 2016-05-17.
 */
public class TestBestViewHolder extends RecyclerView.ViewHolder {
    ImageView logoImgView;
    TextView nameView, levelView, classView ,questionView, answerView, wayView;

    TestDetail testDetail;

    public TestBestViewHolder(View itemView) {
        super(itemView);
        logoImgView = (ImageView) itemView.findViewById(R.id.img_logo);
        nameView = (TextView)itemView.findViewById(R.id.text_name);
        levelView = (TextView)itemView.findViewById(R.id.text_level);
        classView = (TextView)itemView.findViewById(R.id.text_class);
        questionView = (TextView)itemView.findViewById(R.id.text_question);
        answerView = (TextView)itemView.findViewById(R.id.text_answer);
        // wayView = (TextView)itemView.findViewById(R.id.text_commentbad);

    }

    public void setTestBest(TestDetail testDetail){
        this.testDetail = testDetail;
        Log.i("testDetail","testDetail : "+testDetail);
        // Glide.with(logoImgView.getContext()).load(doDetail.getDoInter().getLogoImgUrl()).into(logoImgView);
        nameView.setText(" ");
        levelView.setText(testDetail.getLevel()+" ");
        classView.setText(" ");
        questionView.setText(testDetail.getQuestion());
        answerView.setText(testDetail.getAnswer());
    }


}
