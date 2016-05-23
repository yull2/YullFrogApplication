package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;
import com.frogoutofwell.yullfrogapplication.data.DoDetail;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;

import java.util.List;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class MainHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TITLE = 2;
    public static final int VIEW_TYPE_DO_REVIEW = 3;
    public static final int VIEW_TYPE_TEST_REVIEW = 4;

    List<ActivityDetail> activityDetail;
    DoDetail doDetail;
    TestDetail testDetail;

    public void setActivityImg(List<ActivityDetail> activityDetail){
        this.activityDetail = activityDetail;
        notifyDataSetChanged();
    }


    public void setDoDetail(DoDetail doDetail){
        this.doDetail = doDetail;
        notifyDataSetChanged();
    }

    public void setTestDetail(TestDetail testDetail){
        this.testDetail = testDetail;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) return VIEW_TYPE_IMAGE;
        if (position == 1) return VIEW_TYPE_TITLE;
        if (position == 2) return VIEW_TYPE_DO_REVIEW;
        if (position == 3) return VIEW_TYPE_TITLE;
        if (position == 4) return VIEW_TYPE_TEST_REVIEW;
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_IMAGE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_img, null);
                return new ImageViewHolder(view);
            }
            case VIEW_TYPE_TITLE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_title, null);
                return new TitleViewHolder(view);
            }
            case VIEW_TYPE_DO_REVIEW : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_best_do_review, null);
                return new DoBestViewHolder(view);
            }
            case VIEW_TYPE_TEST_REVIEW : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_best_test_review, null);
                return new TestBestViewHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0){
            ImageViewHolder h = (ImageViewHolder)holder;
            return;
        }
        if (position == 1){
            TitleViewHolder h = (TitleViewHolder)holder;
            h.setTitle("베스트 활동 후기");
            return;
        }
        if (position == 2){
            DoBestViewHolder h = (DoBestViewHolder)holder;
            h.setDoBest(doDetail);
            return;
        }
        if (position == 3){
            TitleViewHolder h = (TitleViewHolder)holder;
            h.setTitle("베스트 면접 후기");
            return;
        }
        if (position == 4){
            TestBestViewHolder h = (TestBestViewHolder)holder;
            h.setTestBest(testDetail);
            return;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
