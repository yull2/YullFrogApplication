package com.frogoutofwell.yullfrogapplication.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.MainActivity;
import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.StatusCheckResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

public class StudentConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_confirm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserConfirm("yulll91@naver.com", "20102173@ssu.ac.kr");
            }
        });


    }

    private void setUserConfirm(String email, String sEmail){
        NetworkManager.getInstance().getStudentConfirm(this, email, sEmail, new NetworkManager.OnResultListener<StatusCheckResult>() {
            @Override
            public void onSuccess(Request request, StatusCheckResult result) {
                if(result.status.equals("OK")){
                    Toast.makeText(StudentConfirmActivity.this, "대학생인증에 성공했습니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StudentConfirmActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(StudentConfirmActivity.this,"이메일을 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });
    }


}
