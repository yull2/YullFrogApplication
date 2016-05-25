package com.frogoutofwell.yullfrogapplication.testreview;


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
import android.widget.TextView;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.InterTestReviewResult;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;
import com.frogoutofwell.yullfrogapplication.write.WriteTestActivity;

import java.io.IOException;

import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestReviewFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    RecyclerView listView;
    TextView countView;
    TestReviewAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

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
        mAdapter = new TestReviewAdapter();
        mAdapter.setOnItemClickListener(new TestFirstViewHolder.OnItemClickListener() {
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
        View view = inflater.inflate(R.layout.fragment_test_review, container, false);
        countView = (TextView)view.findViewById(R.id.text_count);
        listView = (RecyclerView)view.findViewById(R.id.rv_list);
        listView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        setData();

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


    private void setData() {
        NetworkManager.getInstance().getFrogInterTestReview(getContext(), 2, new NetworkManager.OnResultListener<InterTestReviewResult>() {
            @Override
            public void onSuccess(Request request, InterTestReviewResult result) {
                mAdapter.clear();
                countView.setText("총 "+result.totalInterCount+" 개의 후기");
                mAdapter.setLevelImage(result.totalInterLevel);
                mAdapter.addAll(result.testDetails.testDetails);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "fail : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*for (int i = 0; i<10;i++) {
            TestDetail at = new TestDetail();
            at.setTerm("2015 상반기");
            at.setQuestion("질문 1 왜 지원했죠?---"+i);
            at.setLevel(3);
            at.setResult(2);
            at.setAnswer("ㅇ라어ㅏㅁ렁나러망ㄴ ㅇ라고 대답했어요");
            mAdapter.add(at);
        }*/
    }

}
