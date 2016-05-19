package com.frogoutofwell.yullfrogapplication.history;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;
import com.frogoutofwell.yullfrogapplication.testreview.TestReviewDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTestHistoryFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    RecyclerView listView;
    MyTestAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

    public MyTestHistoryFragment() {
        // Required empty public constructor
    }

    public static MyTestHistoryFragment newInstance(String name){
        MyTestHistoryFragment fragment = new MyTestHistoryFragment();
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
        mAdapter = new MyTestAdapter();
        mAdapter.setOnItemClickListener(new MyTestViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TestDetail testDetail) {
                Intent intent = new Intent(getContext(), TestReviewDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_test_history, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);

        setData();

        return view;
    }

    private void setData() {
        for (int i = 0; i<10;i++){
            TestDetail ad = new TestDetail();
            ad.setWriteDate("2016.05." + i);
            ad.setTerm("2015 하반기");
            ad.setQuestion("해당 활동을 지원한 동기와 기자단을 통해서 얻고자 하는 것이 무엇입니까?");
            ad.setLevel(i%5);
            ad.setResult(i%3);
            mAdapter.add(ad);
        }
    }
}
