package com.clcx.goldenmaster.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ljc123 on 2016/7/4.
 */
public class Demo1Adapter extends BaseRecyclerAdapter {
    public Demo1Adapter(Activity mContext) {
        super(mContext);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void bindHolder(RecyclerView.ViewHolder holder, int position) {

    }

//    public Demo1Adapter(Activity mContext) {
//        super(mContext);
//    }
//
//    @Override
//    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(getActivity()).inflate(R.layout.demo1_item, parent,false);
//        MyHolder holder = new MyHolder(v);
//
//        return holder;
//    }
//
//    @Override
//    protected void bindHolder(RecyclerView.ViewHolder holder, int position) {
//        MyHolder mHolder = (MyHolder) holder;
//        DemoModel bean = (DemoModel) getItem(position);
//        mHolder.demo1_text.setText(bean.getTitle());
//        mHolder.demo1_text.setTextColor(bean.getTextcolor());
//        mHolder.demo1_text.setBackgroundColor(bean.getColor());
//    }
//
//    private class MyHolder extends RecyclerView.ViewHolder {
//
//        private TextView demo1_text;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            demo1_text = (TextView) itemView.findViewById(R.id.demo1_text);
//        }
//    }
}
