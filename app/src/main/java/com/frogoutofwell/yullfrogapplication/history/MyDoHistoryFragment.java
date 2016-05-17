package com.frogoutofwell.yullfrogapplication.history;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frogoutofwell.yullfrogapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDoHistoryFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    public MyDoHistoryFragment() {
        // Required empty public constructor
    }

    public static MyDoHistoryFragment newInstance(String name){
        MyDoHistoryFragment fragment = new MyDoHistoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_do_history, container, false);
        return view;
    }

}
