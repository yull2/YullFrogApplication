package com.frogoutofwell.yullfrogapplication.mypage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frogoutofwell.yullfrogapplication.R;
import com.frogoutofwell.yullfrogapplication.data.ActivityDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class MypageLikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ActivityDetail> items = new ArrayList<>();

    public static final int VIEW_TYPE_LIKE_ITEM = 1;
    public static final int VIEW_TYPE_ITEM_MORE = 2;

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void setLikeItem(ActivityDetail activityDetail) {
        items.add(activityDetail);
        notifyDataSetChanged();
    }

    MypageLikeMoreViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(MypageLikeMoreViewHolder.OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("position"," position = "+ position);
        if (position >= 0 && position < 3) return VIEW_TYPE_LIKE_ITEM;
        if (position == 3) return VIEW_TYPE_ITEM_MORE;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_LIKE_ITEM: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_like_doitem, null);
                return new MypageLikeViewHolder(view);
            }
            case VIEW_TYPE_ITEM_MORE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_like_item_more, null);
                return new MypageLikeMoreViewHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= 0 && position < 3){
            MypageLikeViewHolder h = (MypageLikeViewHolder)holder;
            h.setLikeItem(items.get(position));
            return;
        }
        if (position == 3){
            MypageLikeMoreViewHolder h = (MypageLikeMoreViewHolder)holder;
            h.setOnItemClickListener(mListener);
            return;
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
