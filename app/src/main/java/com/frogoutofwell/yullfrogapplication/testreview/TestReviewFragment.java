package com.frogoutofwell.yullfrogapplication.testreview;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.write.WriteTestActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestReviewFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    public TestReviewFragment() {
        // Required empty public constructor
    }

    public static TestReviewFragment newInstance(String name){
        TestReviewFragment fragment = new TestReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_review, container, false);
        Button btn = (Button)view.findViewById(R.id.btn_go);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteTestActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
