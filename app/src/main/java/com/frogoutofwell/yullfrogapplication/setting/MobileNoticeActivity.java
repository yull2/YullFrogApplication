package com.frogoutofwell.yullfrogapplication.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;

import com.frogoutofwell.yullfrogapplication.R;

public class MobileNoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_notice);
        setTitle(getString(R.string.notice_mobile));


        SwitchCompat push_mobile = (SwitchCompat)findViewById(R.id.p_mobile);


    }
}
