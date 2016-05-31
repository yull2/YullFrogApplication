package com.frogoutofwell.yullfrogapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.data.InterInfoResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;
import com.frogoutofwell.yullfrogapplication.write.WriteDoActivity;
import com.frogoutofwell.yullfrogapplication.write.WriteTestActivity;

import java.io.IOException;

import okhttp3.Request;

public class InterMainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager pager;

    ImageView logoView;
    TextView nameView, classView;

    int seq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        Intent intent = getIntent();
        seq = intent.getIntExtra("seq",1);
        Log.i("mainactivity","에서 넘어온 seqqqqqqqqqqqq : "+seq);

        logoView = (ImageView)findViewById(R.id.img_logo);
        nameView = (TextView)findViewById(R.id.text_name);
        classView = (TextView)findViewById(R.id.text_result);


        nameView.setText("대외활동명");
        classView.setText("활동분류");

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

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0 : fab.hide();break;
                    case 1 :
                        fab.show();
                        fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           // Toast.makeText(InterMainActivity.this, "1 ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(InterMainActivity.this, WriteTestActivity.class);
                            intent.putExtra("seq",seq);
                           // Log.i("intermainactivity"," seqqqqqqqqqqqq : "+seq);
                            startActivity(intent);
                        }
                    }); break;
                    case 2 :
                        fab.show();
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               // Toast.makeText(InterMainActivity.this, "2 ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InterMainActivity.this, WriteDoActivity.class);
                                intent.putExtra("seq",seq);
                                startActivity(intent);
                            }
                        }); break;
                    case 3 : fab.hide();break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inter, menu);

        return true;
    }

    private void setInterMain(){
        NetworkManager.getInstance().getFrogInterInfo(this, seq, new NetworkManager.OnResultListener<InterInfoResult>() {
            @Override
            public void onSuccess(Request request, InterInfoResult result) {
               // String srcImg = result.detail.activityDetail.getCompanyLogo();
                // Log.i("Inter Image Url", "Inter Image Url"+srcImg);
               // Glide.with(logoView.getContext()).load(srcImg).into(logoView);
                nameView.setText(result.detail.activityDetail.getName());
                classView.setText(result.detail.activityDetail.getActClass()+" / "+result.detail.activityDetail.getCompanyName());
            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });

    }

}
