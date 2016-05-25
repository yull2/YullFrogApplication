package com.frogoutofwell.yullfrogapplication.doreview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;
import com.frogoutofwell.yullfrogapplication.data.DoDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class DoReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_DO_RATE = 1;
    public static final int VIEW_TYPE_DO_FIRSTREVIEW = 2;
    public static final int VIEW_TYPE_DO_SECONDREVIEW = 3;

    List<DoDetail> items = new ArrayList<>();
    int[] counts;

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void setCountStar(int[] star){
        counts = star;
        notifyDataSetChanged();
    }

    public void add(DoDetail doDetail) {
        items.add(doDetail);
        notifyDataSetChanged();
    }

    public void addAll(List<DoDetail> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    DoFirstViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(DoFirstViewHolder.OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return VIEW_TYPE_DO_RATE;
        if (position == 1) return VIEW_TYPE_DO_FIRSTREVIEW;
        if (position > 1 ) return VIEW_TYPE_DO_SECONDREVIEW;
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_DO_RATE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_do_rate_item, null);
                return new DoRateViewHolder(view);
            }
            case VIEW_TYPE_DO_FIRSTREVIEW: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_do_review, null);
                return new DoFirstViewHolder(view);
            }
            case VIEW_TYPE_DO_SECONDREVIEW: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_do_reviews, null);
                return new DoSecondViewHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0){
            DoRateViewHolder h = (DoRateViewHolder)holder;
            h.setDoRate(new ActivityDetail());
            return;
        }
        if (position == 1){
            DoFirstViewHolder h = (DoFirstViewHolder)holder;
            h.setDoFirst(items.get(position));
            h.setOnItemClickListener(mListener);
            return;
        }
        if ( position > 1){
            DoSecondViewHolder h = (DoSecondViewHolder)holder;
            h.setDoSecond(items.get(position));
            return;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
