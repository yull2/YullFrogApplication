package com.frogoutofwell.yullfrogapplication.mypage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;

public class LikeMoreDetailActivity extends AppCompatActivity {

    RecyclerView listView;
    LikeMoreAdapter mAdapter;
    GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_more_detail);
        mAdapter = new LikeMoreAdapter();
        listView = (RecyclerView)findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(this, 2);
        listView.setLayoutManager(mLayoutManager);
        setData();
    }

    private void setData() {
        for (int i = 0; i < 10;i++){
            ActivityDetail ad = new ActivityDetail();
            ad.setActClass("해외봉사");
            ad.setName("KIV 해외봉사  "+i);

            mAdapter.add(ad);
        }

    }

}
