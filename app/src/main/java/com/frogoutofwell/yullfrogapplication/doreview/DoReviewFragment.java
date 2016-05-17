package com.frogoutofwell.yullfrogapplication.doreview;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.write.WriteDoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoReviewFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    public DoReviewFragment() {
        // Required empty public constructor
    }

    public static DoReviewFragment newInstance(String name){
        DoReviewFragment fragment = new DoReviewFragment();
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
        View view = inflater.inflate(R.layout.fragment_do_review, container, false);
        Button btn = (Button)view.findViewById(R.id.btn_go);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteDoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
