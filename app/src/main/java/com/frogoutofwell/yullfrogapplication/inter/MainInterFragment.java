package com.frogoutofwell.yullfrogapplication.inter;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.InterMainActivity;
import com.frogoutofwell.yullfrogapplication.MainPagerAdapter;
import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;
import com.frogoutofwell.yullfrogapplication.data.MainInterResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainInterFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private String mName;

    RecyclerView listView;
    MainInterAdapter mAdapter;
    GridLayoutManager mLayoutManager;

    final String[] actClass = new String[]{"전체","해외탐방","국내봉사","해외봉사","강연","멘토링","서포터즈","마케터","홍보대사","기자단","기획단","기타"};
    final String[] indus = new String[]{"전체","서비스", "제조, 화학", "의료, 제약, 복지", "유통,무역,운송", "교육", "건설", "IT, 웹, 통신", "미디어, 디자인", "은행, 금융", "기관, 협회"};
    final String[] term = new String[]{"전체", "1개월", "1 ~ 3개월","3 ~ 5개월","6개월~"};
    final String[] local = new String[]{"전체", "서울", "경기", "인천", "부산", "대구", "대전", "광주", "울산", "세종", "강원", "경남"};

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

        final Button btn_actclass = (Button)view.findViewById(R.id.btn_actclass);
        btn_actclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterDialogFragment f = new InterDialogFragment().newInstance("활동별",actClass);
                f.setOnItemSelectListener(new InterDialogFragment.OnItemSelectListener() {
                    @Override
                    public void onItemSelect(String item) {
                        btn_actclass.setText(item);
                    }
                });
                f.show(getChildFragmentManager(), "act");

            }
        });

        final Button btn_indus = (Button)view.findViewById(R.id.btn_indus);
        btn_indus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterDialogFragment f = new InterDialogFragment().newInstance("산업별",indus);
                f.setOnItemSelectListener(new InterDialogFragment.OnItemSelectListener() {
                    @Override
                    public void onItemSelect(String item) {
                        btn_indus.setText(item);
                    }
                });
                f.show(getChildFragmentManager(), "indus");
            }
        });

        final Button btn_term = (Button)view.findViewById(R.id.btn_term);
        btn_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterDialogFragment f = new InterDialogFragment().newInstance("기간별",term);
                f.setOnItemSelectListener(new InterDialogFragment.OnItemSelectListener() {
                    @Override
                    public void onItemSelect(String item) {
                        btn_term.setText(item);
                    }
                });
                f.show(getChildFragmentManager(), "term");
            }
        });

        final Button btn_local = (Button)view.findViewById(R.id.btn_local);
        btn_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterDialogFragment f = new InterDialogFragment().newInstance("지역별",local);
                f.setOnItemSelectListener(new InterDialogFragment.OnItemSelectListener() {
                    @Override
                    public void onItemSelect(String item) {
                        btn_local.setText(item);
                    }
                });
                f.show(getChildFragmentManager(), "local");
            }
        });

        setData();

        return view;
    }
    private void setData() {

        NetworkManager.getInstance().getFrogMainInter(getContext(), new NetworkManager.OnResultListener<MainInterResult>() {
            @Override
            public void onSuccess(Request request, MainInterResult result) {
                mAdapter.clear();
                mAdapter.addAll(result.activityDetails.activityDetails);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "fail : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
