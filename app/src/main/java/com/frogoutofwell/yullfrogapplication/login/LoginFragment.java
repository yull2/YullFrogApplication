package com.frogoutofwell.yullfrogapplication.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.MainActivity;
import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.StatusCheckResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    Button facebookLoginButton;
    EditText emailView,passwordView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        emailView = (EditText)view.findViewById(R.id.edit_email);
        passwordView = (EditText)view.findViewById(R.id.edit_password);

        Button btn_login = (Button)view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailView.getText().toString();
                final String password = passwordView.getText().toString();

                login(email, password);

            }
        });



        facebookLoginButton = (Button)view.findViewById(R.id.btn_login_facebook);
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login();
            }
        });
        Button btn_signup = (Button)view.findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });


        return view;
    }

    private void login(String id, String pw) {
        NetworkManager.getInstance().signIn(getContext(), id, pw, new NetworkManager.OnResultListener<StatusCheckResult>() {
            @Override
            public void onSuccess(Request request, StatusCheckResult result) {
                Toast.makeText(getContext(),"로그인 : "+result.status,Toast.LENGTH_SHORT).show();
                if (result.status.equals("OK")){
                    Toast.makeText(getContext(),"로그인 : "+result.status,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }else if (result.status.equals("notApproval")){
                    Toast.makeText(getContext(),"약관동의가 필요합니다 : "+result.status,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), AgreementActivity.class);
                    startActivity(intent);
                }else if (result.status.equals("notConfirm")){
                    Toast.makeText(getContext(),"대학생 인증이 필요합니다 : "+result.status,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), StudentConfirmActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(),"회원 가입이 필요합니다. : "+result.status,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });

    }

    private void signup() {
        ((LoginActivity)getActivity()).changeSignUp();
    }

}
