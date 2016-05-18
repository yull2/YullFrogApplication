package com.frogoutofwell.yullfrogapplication.doreview;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;
import com.frogoutofwell.yullfrogapplication.data.DoDetail;
import com.frogoutofwell.yullfrogapplication.write.WriteDoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoReviewFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    RecyclerView listView;
    DoReviewAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

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
        mAdapter = new DoReviewAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_do_review, container, false);

        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        setData();


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

    private void setData() {
        for (int i = 1; i<10;i++) {
            DoDetail at = new DoDetail();
            at.setRate(i%5);
            at.setComment("한줄로 평하면 ㅎ음... "+i);
            at.setCommentGood("이건 ㅓㅈㅇ말정ㅁ라 도움돼요");
            at.setCommentBad("하 정말 아숩아숩아어아앙어워요");
            mAdapter.add(at);
        }
    }
}
