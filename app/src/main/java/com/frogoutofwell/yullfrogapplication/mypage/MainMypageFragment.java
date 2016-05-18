package com.frogoutofwell.yullfrogapplication.mypage;


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

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;
import com.frogoutofwell.yullfrogapplication.history.MyHistoryActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMypageFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    RecyclerView listView;
    MypageLikeAdapter mAdapter;
    GridLayoutManager mLayoutManager;

    public MainMypageFragment() {
        // Required empty public constructor
    }

    public static MainMypageFragment newInstance(String name){
        MainMypageFragment fragment = new MainMypageFragment();
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
        mAdapter = new MypageLikeAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_mypage, container, false);

        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        listView.setLayoutManager(mLayoutManager);
        setData();
        Button btn = (Button)view.findViewById(R.id.btn_go);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyHistoryActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void setData() {
        for (int i = 0; i<3;i++){
            ActivityDetail ad = new ActivityDetail();
            ad.setActClass("서포터즈");
            ad.setName("CJ올리브영 서포터즈 "+i);

            mAdapter.add(ad);
        }
    }

}
