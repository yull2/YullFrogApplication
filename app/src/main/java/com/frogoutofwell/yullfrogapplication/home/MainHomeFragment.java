package com.frogoutofwell.yullfrogapplication.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.DoDetail;
import com.frogoutofwell.yullfrogapplication.data.MainHomeResult;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainHomeFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    RecyclerView listView;
    MainHomeAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

    public MainHomeFragment() {
        // Required empty public constructor
    }

    public static MainHomeFragment newInstance(String name){
        MainHomeFragment fragment = new MainHomeFragment();
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
        mAdapter = new MainHomeAdapter();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);

        setData();

        return view;
    }

    private void setData() {
        NetworkManager.getInstance().getFrogMainHomeFeed(getContext(), new NetworkManager.OnResultListener<MainHomeResult>() {
            @Override
            public void onSuccess(Request request, MainHomeResult result) {
                mAdapter.setDoDetail(result.doDetail);
                mAdapter.setTestDetail(result.testDetail);
               // mAdapter.setActivityImg(result.activityDetail);
               // mAdapter.clear();
                //mAdapter.addAll(result.products.productList);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "fail : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*DoDetail ad = new DoDetail();
        ad.setComment("우와아아아아앙 한줄평");
        ad.setCommentGood("삼성전자 서포터즈 굿굿굿");
        ad.setCommentBad("삼성전자 서포터즈 baaaaaaaaaaaaad");
        ad.setRate(4);

        mAdapter.setDoDetail(ad);

        TestDetail at = new TestDetail();
        at.setLevel(3);
        at.setQuestion("질문 1 : 왜 지원했죠?");
        at.setAnswer("ㅇ라어ㅏㅁ렁나러망ㄴ ㅇ라고 대답했어요");
        mAdapter.setTestDetail(at);*/

    }

}
