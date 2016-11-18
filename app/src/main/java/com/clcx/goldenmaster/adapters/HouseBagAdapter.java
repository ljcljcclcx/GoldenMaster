package com.clcx.goldenmaster.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.customview.CapacityBean;
import com.clcx.goldenmaster.customview.CapacityChartView;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/7/4.
 */
public class HouseBagAdapter extends BaseRecyclerAdapter {
    private OnItemClickListener onItemClickListener;

    public HouseBagAdapter(Activity mContext) {
        super(mContext);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_housebag, parent, false);
        MyHolder holder = new MyHolder(v, onItemClickListener);

        return holder;
    }

    @Override
    protected void bindHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder mHolder = (MyHolder) holder;
        AlchemiItem bean = (AlchemiItem) getItem(position);
        mHolder.housebagItemName.setText(bean.getIntro());
        mHolder.housebagItemCapacity.setData(Config.setCapacities(bean), 15);
        mHolder.housebagItemCapacity.setOnCapacityTopPointTouchListener(new CapacityChartView
                .OnCapacityTopPointTouchListener() {
            @Override
            public void touchCapacity(CapacityBean bean) {
                ToastClcxUtil.getInstance().showToast(bean.getCapacityName() + "：" + bean
                        .getCapacityPoint());
            }
        });
        mHolder.housebagItemBackground.setBackgroundColor(bean.isLocked() ? Color.YELLOW : Color.WHITE);
    }

    public void setItems(List<AlchemiItem> items) {
        this.clear();
        this.addItem(items);
        notifyDataSetChanged();
    }

    private class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView housebagItemName;
        private CapacityChartView housebagItemCapacity;
        private RelativeLayout housebagItemBackground;
        private OnItemClickListener onItemClickListener;

        public MyHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            housebagItemBackground = (RelativeLayout) itemView.findViewById(R.id.housebagItemBackground);
            housebagItemName = (TextView) itemView.findViewById(R.id.housebagItemName);
            housebagItemCapacity = (CapacityChartView) itemView.findViewById(R.id.housebagItemCapacity);
            this.onItemClickListener = onItemClickListener;
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
