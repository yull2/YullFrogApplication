package com.frogoutofwell.yullfrogapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frogoutofwell.yullfrogapplication.data.InterInfoResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

public class InterMainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager pager;

    ImageView logoView;
    TextView nameView, classView, companyNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logoView = (ImageView)findViewById(R.id.img_logo);
        nameView = (TextView)findViewById(R.id.text_name);
        classView = (TextView)findViewById(R.id.text_class);
        companyNameView = (TextView)findViewById(R.id.text_companyname);

        nameView.setText("대외활동명");
        classView.setText("활동분류");
        companyNameView.setText("주최기관");

        tabs = (TabLayout)findViewById(R.id.tabs);
        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new InterMainPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);
        tabs.removeAllTabs();

        tabs.addTab(tabs.newTab().setText("모집요강"));
        tabs.addTab(tabs.newTab().setText("면접후기"));
        tabs.addTab(tabs.newTab().setText("활동후기"));
        tabs.addTab(tabs.newTab().setText("추천"));

        setInterMain();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void setInterMain(){
        NetworkManager.getInstance().getFrogInterInfo(this, 20, new NetworkManager.OnResultListener<InterInfoResult>() {
            @Override
            public void onSuccess(Request request, InterInfoResult result) {
               // String srcImg = result.detail.activityDetail.getCompanyLogo();
                // Log.i("Inter Image Url", "Inter Image Url"+srcImg);
               // Glide.with(logoView.getContext()).load(srcImg).into(logoView);
                nameView.setText(result.detail.activityDetail.getName());
                classView.setText(result.detail.activityDetail.getActClass());
                companyNameView.setText(result.detail.activityDetail.getCompanyName());
            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });

    }

}
