package com.frogoutofwell.yullfrogapplication.inter;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frogoutofwell.yullfrogapplication.InterMainActivity;
import com.frogoutofwell.yullfrogapplication.MainPagerAdapter;
import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainInterFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;
    RecyclerView listView;

    MainInterAdapter mAdapter;

    GridLayoutManager mLayoutManager;

    public MainInterFragment() {
        // Required empty public constructor
    }

    public static MainInterFragment newInstance(String name){
        MainInterFragment fragment = new MainInterFragment();
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
        mAdapter = new MainInterAdapter();
        mAdapter.setOnItemClickListener(new MainInterViewHolder.OnItemClickListener(){

            @Override
            public void onItemClick(View view, ActivityDetail activityDetail) {
                Intent intent = new Intent(getActivity(), InterMainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_inter, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        listView.setLayoutManager(mLayoutManager);
        setData();

        return view;
    }
    private void setData() {
        ActivityDetail ad = new ActivityDetail();
        ad.setName("삼성전자 서포터즈");
        ad.setEndDate("D-3");
        ad.setAverageRate(4);
        ad.setAct("서포터즈");
        for (int i = 0; i<20;i++){
            mAdapter.add(ad);
        }
    }

}
