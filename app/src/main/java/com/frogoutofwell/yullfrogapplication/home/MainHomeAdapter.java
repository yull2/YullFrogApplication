package com.frogoutofwell.yullfrogapplication.home;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.frogoutofwell.yullfrogapplication.data.DoDetail;
import com.frogoutofwell.yullfrogapplication.data.TestDetail;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class MainHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TITLE = 2;
    public static final int VIEW_TYPE_DO_REVIEW = 3;
    public static final int VIEW_TYPE_TEST_REVIEW = 4;

    DoDetail doDetail;
    TestDetail testDetail;

    public void setDoDetail(DoDetail doDetail){
        this.doDetail = doDetail;
        notifyDataSetChanged();
    }

    public void setTestDetail(TestDetail testDetail){
        this.testDetail =testDetail;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
