package com.clcx.goldenmaster.ui.bag;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.ui.creator.CreatorContract;
import com.clcx.goldenmaster.ui.creator.CreatorModel;
import com.clcx.goldenmaster.ui.creator.PreCreator;
import com.clcx.goldenmaster.ui.house.AtyAlchemistHouse;

import butterknife.Bind;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyBag extends BaseActivity<BagPresenter, BagModel> implements BagContract.View {
    @Bind(R.id.houseRecyclerBag)
    RecyclerView houseRecyclerBag;
    private HouseBagAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bag;
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
        //背包的adapter
        adapter = new HouseBagAdapter(this);
        adapter.setItems(Config.getAlchemista(this).getBag());
        houseRecyclerBag.setLayoutManager(new LinearLayoutManager(this));
        houseRecyclerBag.setAdapter(adapter);
    }


}
