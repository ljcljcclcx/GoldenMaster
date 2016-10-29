package com.clcx.goldenmaster.ui.message;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.adapters.MessageAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.beans.MessageBean;
import com.clcx.goldenmaster.ui.alchemist.AtyAlchemit;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyMessage extends BaseActivity<MessagePresenter, MessageModel> implements MessageContract.View {
    @Bind(R.id.messageRecyclerView)
    RecyclerView messageRecyclerView;
    private MessageAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }


    @Override
    public void initView() {
        adapter = new MessageAdapter(this);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(adapter);
        adapter.setItems(MessageModel.getMessages());
        adapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mPresenter.clickMessage(position);
            }
        });
    }


    @Override
    public MessageAdapter getAdapter() {
        return adapter;
    }

    @OnClick(R.id.messageOnclick)
    public void messageOnclick(View v) {
        int size = adapter.getItemCount();
        for (int a = 0; a < size; a++) {
            mPresenter.clickMessage(0);
        }
    }
}
