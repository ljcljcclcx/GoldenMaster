package com.clcx.goldenmaster.ui.Rest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.MessageAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.ui.message.MessageContract;
import com.clcx.goldenmaster.ui.message.MessageModel;
import com.clcx.goldenmaster.ui.message.MessagePresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyRest extends BaseActivity<RestPresenter, RestModel> implements RestContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_rest;
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
    }

    @OnClick(R.id.rest10)
    public void rest10(View v) {
        mPresenter.rest(Config.REST_ENERGE_RECOVERY[0], Config.REST_MONEY_REDUCE[0]);
    }

    @OnClick(R.id.rest100)
    public void rest100(View v) {
        mPresenter.rest(Config.REST_ENERGE_RECOVERY[1], Config.REST_MONEY_REDUCE[1]);
    }
}
