package com.frogoutofwell.yullfrogapplication.recommend;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frogoutofwell.yullfrogapplication.InterMainActivity;
import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    RecyclerView listView;
    RecommendAdapter mAdapter;
    GridLayoutManager mLayoutManager;

    public RecommendFragment() {
        // Required empty public constructor
    }

    public static RecommendFragment newInstance(String name){
        RecommendFragment fragment = new RecommendFragment();
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
        mAdapter = new RecommendAdapter();
        mAdapter.setOnItemClickListener(new RecommendViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ActivityDetail activityDetail) {
                Intent intent = new Intent(getContext(), InterMainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        listView.setLayoutManager(mLayoutManager);
        setData();
        return view;
    }

    private void setData() {
        for (int i = 0; i<20;i++){
            ActivityDetail ad = new ActivityDetail();
            ad.setActClass("서포터즈");
            ad.setName("SK 협업 서포터즈 "+i);
            ad.setEndDate("D - "+i);
            ad.setAverageRate(i%5);
            mAdapter.add(ad);
        }
    }

}
