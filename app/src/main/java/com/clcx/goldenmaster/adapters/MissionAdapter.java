package com.clcx.goldenmaster.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.beans.MessageBean;
import com.clcx.goldenmaster.beans.MissionBean;

import java.util.List;

/**
 * Created by ljc123 on 2016/7/4.
 */
public class MissionAdapter extends BaseRecyclerAdapter {
    private OnItemClickListener onItemClickListener;

    public MissionAdapter(Activity mContext) {
        super(mContext);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_mission, parent, false);
        MyHolder holder = new MyHolder(v, onItemClickListener);

        return holder;
    }

    @Override
    protected void bindHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder mHolder = (MyHolder) holder;
        MissionBean bean = (MissionBean) getItem(position);
        mHolder.tvMissionName.setText(bean.getIntro());
        mHolder.tvMissionReward.setText("奖励：$" + bean.getReward());
        String str = bean.isFinish() ? "未完成：" : "已完成：";
        int strColor = bean.isFinish() ? Color.rgb(255, 0, 0) : Color.rgb(0, 0, 0);
        mHolder.tvMissionProcess.setText(str + bean.getCurrentNumber() + "/" + bean.getNeedNumber());
        mHolder.tvMissionProcess.setTextColor(strColor);
    }

    public void setItems(List<MissionBean> items) {
        this.clear();
        this.addItem(items);
        notifyDataSetChanged();
    }

    private class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMissionName;
        TextView tvMissionProcess;
        TextView tvMissionReward;
        private OnItemClickListener onItemClickListener;

        public MyHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            tvMissionName = (TextView) itemView.findViewById(R.id.tvMissionName);
            tvMissionProcess = (TextView) itemView.findViewById(R.id.tvMissionProcess);
            tvMissionReward = (TextView) itemView.findViewById(R.id.tvMissionReward);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
