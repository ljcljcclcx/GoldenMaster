package com.clcx.goldenmaster.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.MarketItem;

import java.util.List;

/**
 * Created by ljc123 on 2016/7/4.
 */
public class MarketAdapter extends BaseRecyclerAdapter {
    private OnItemClickListener onItemClickListener;

    public MarketAdapter(Activity mContext) {
        super(mContext);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_housemarket, parent, false);
        MyHolder holder = new MyHolder(v, onItemClickListener);

        return holder;
    }

    @Override
    protected void bindHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder mHolder = (MyHolder) holder;
        MarketItem bean = (MarketItem) getItem(position);
        mHolder.housemarketItemName.setText(bean.getItem().getName());
        mHolder.housemarketPrice.setText("售价：$" + bean.getPrice());
    }

    public void setItems(List<MarketItem> items) {
        this.clear();
        this.addItem(items);
        notifyDataSetChanged();
    }

    private class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView housemarketItemName;
        private TextView housemarketPrice;
        private OnItemClickListener onItemClickListener;

        public MyHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            housemarketItemName = (TextView) itemView.findViewById(R.id.housemarketItemName);
            housemarketPrice = (TextView) itemView.findViewById(R.id.housemarketPrice);
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
