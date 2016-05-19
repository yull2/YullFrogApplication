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
import com.frogoutofwell.yullfrogapplication.data.DoDetail;
import com.frogoutofwell.yullfrogapplication.doreview.DoReviewDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDoHistoryFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    RecyclerView listView;
    MyDoAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

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
        mAdapter = new MyDoAdapter();
        mAdapter.setOnItemClickListener(new MyDoViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, DoDetail doDetail) {
                Intent intent = new Intent(getContext(), DoReviewDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_do_history, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        setData();

        return view;
    }

    private void setData() {
        for (int i = 0; i<10;i++){
            DoDetail ad = new DoDetail();
            ad.setWriteDate("2016.05." + i);
            ad.setTerm("2015 하반기");
            ad.setComment("성장가능성을 높여주고, 많은 경험을 형성할수 있는 기회였어요 .");
            ad.setRate(i%5);
            mAdapter.add(ad);
        }
    }


}
