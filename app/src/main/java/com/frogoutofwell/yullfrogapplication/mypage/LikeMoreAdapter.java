package com.frogoutofwell.yullfrogapplication.mypage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-05-23.
 */
public class LikeMoreAdapter extends RecyclerView.Adapter<MypageLikeViewHolder> {

    List<ActivityDetail> items = new ArrayList<>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(ActivityDetail activityDetail) {
        items.add(activityDetail);
        notifyDataSetChanged();
    }

    public void addAll(List<ActivityDetail> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public MypageLikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_like_doitem, null);
        return new MypageLikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MypageLikeViewHolder holder, int position) {
        holder.setLikeItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
