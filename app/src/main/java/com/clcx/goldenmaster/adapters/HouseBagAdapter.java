package com.clcx.goldenmaster.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.customview.CapacityBean;
import com.clcx.goldenmaster.customview.CapacityChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/7/4.
 */
public class HouseBagAdapter extends BaseRecyclerAdapter {

    public HouseBagAdapter(Activity mContext) {
        super(mContext);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_housebag, parent, false);
        MyHolder holder = new MyHolder(v);

        return holder;
    }

    @Override
    protected void bindHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder mHolder = (MyHolder) holder;
        AlchemiItem bean = (AlchemiItem) getItem(position);
        mHolder.housebagItemName.setText(bean.getIntro());
        mHolder.housebagItemCapacity.setData(setCapacities(bean), 10);
        mHolder.housebagItemCapacity.setOnCapacityTopPointTouchListener(new CapacityChartView
                .OnCapacityTopPointTouchListener() {
            @Override
            public void touchCapacity(CapacityBean bean) {
                ToastClcxUtil.getInstance().showToast(bean.getCapacityName() + "：" + bean
                        .getCapacityPoint());
            }
        });
    }

    public void setItems(List<AlchemiItem> items) {
        this.clear();
        this.addItem(items);
        notifyDataSetChanged();
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        private TextView housebagItemName;
        private CapacityChartView housebagItemCapacity;

        public MyHolder(View itemView) {
            super(itemView);
            housebagItemName = (TextView) itemView.findViewById(R.id.housebagItemName);
            housebagItemCapacity = (CapacityChartView) itemView.findViewById(R.id.housebagItemCapacity);
        }
    }

    private List<CapacityBean> setCapacities(AlchemiItem item) {
        List<CapacityBean> setCapacities = new ArrayList<>();
        for (int a = 0; a < AlchemiItem.MAX_PROPERTY_COUNT; a++) {
            setCapacities.add(new CapacityBean(item.getProperties()[a], item.getPropertiesPoint()[a], AlchemiItem
                    .CAPACITY_COLORS[a]));
        }
        return setCapacities;
    }
}
