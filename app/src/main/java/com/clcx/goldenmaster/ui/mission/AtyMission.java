package com.clcx.goldenmaster.ui.mission;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.adapters.MissionAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.beans.MissionBean;
import com.clcx.goldenmaster.customview.CapacityBean;
import com.clcx.goldenmaster.customview.CapacityChartView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyMission extends BaseActivity<MissionPresenter, MissionModel> implements MissionContract.View {

    @Bind(R.id.missionRecycler)
    RecyclerView missionRecycler;
    private MissionAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mission;
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
        adapter = new MissionAdapter(this);
        missionRecycler.setLayoutManager(new LinearLayoutManager(this));
        missionRecycler.setAdapter(adapter);
        adapter.setItems(MissionModel.getMissions());
        adapter.setOnItemClickListener(new MissionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                MissionBean bean = (MissionBean) adapter.getItem(position);
                if (bean.isFinish()) {
                    MissionModel.closeMIssion(position);
                    adapter.removeItem(position);
                    adapter.notifyItemRemoved(position);
                    AlchemistaAction.builder().getMoney(bean.getReward());
                    ToastClcxUtil.getInstance().showToast("任务完成，获得$" + bean.getReward());
                } else {
                    ToastClcxUtil.getInstance().showToast("您还没完成这个任务");
                }
            }
        });

    }

}
