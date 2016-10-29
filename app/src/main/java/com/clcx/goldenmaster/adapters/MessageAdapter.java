package com.clcx.goldenmaster.adapters;

import android.app.Activity;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.beans.MessageBean;

import java.util.List;

/**
 * Created by ljc123 on 2016/7/4.
 */
public class MessageAdapter extends BaseRecyclerAdapter {
    private OnItemClickListener onItemClickListener;

    public MessageAdapter(Activity mContext) {
        super(mContext);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_message, parent, false);
        MyHolder holder = new MyHolder(v, onItemClickListener);

        return holder;
    }

    @Override
    protected void bindHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder mHolder = (MyHolder) holder;
        MessageBean bean = (MessageBean) getItem(position);
        mHolder.messageMsg.setText(bean.getContent());
        mHolder.messageDate.setText(bean.getDate());
    }

    public void setItems(List<MessageBean> items) {
        this.clear();
        this.addItem(items);
        notifyDataSetChanged();
    }

    private class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView messageMsg;
        private TextView messageDate;
        private OnItemClickListener onItemClickListener;
        public MyHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            messageMsg = (TextView) itemView.findViewById(R.id.messageMsg);
            messageDate = (TextView) itemView.findViewById(R.id.messageDate);
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
