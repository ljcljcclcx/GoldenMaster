package com.clcx.goldenmaster.ui.alchemist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyAlchemit extends BaseActivity<AlchemistPresenter, AlchemistModel> implements AlchemistContract.View {
    @Bind(R.id.houseAlchemistBtn)
    Button houseAlchemistBtn;
    @Bind(R.id.houseRecyclerBagWhenAlchemist)
    RecyclerView houseRecyclerBagWhenAlchemist;
    @Bind(R.id.houseRecyclerAlchemistBottle)
    RecyclerView houseRecyclerAlchemistBottle;
    private AlchemistAdapter bottleAdapter;
    private AlchemistAdapter bagAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_alchemist;
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
        //炼金时的两个adapter
        bagAdapter = new AlchemistAdapter(this);
        bagAdapter.setItems(Config.getAlchemista(this).getBag());
        houseRecyclerBagWhenAlchemist.setLayoutManager(new LinearLayoutManager(this));
        houseRecyclerBagWhenAlchemist.setAdapter(bagAdapter);
        bagAdapter.setOnItemClickListener(new AlchemistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mPresenter.addItemToBottle(position, bagAdapter, bottleAdapter);
            }
        });
        bottleAdapter = new AlchemistAdapter(this);
        houseRecyclerAlchemistBottle.setLayoutManager(new LinearLayoutManager(this));
        houseRecyclerAlchemistBottle.setAdapter(bottleAdapter);
        bottleAdapter.setOnItemClickListener(new AlchemistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mPresenter.removeFromBottle(position, bagAdapter, bottleAdapter);
            }
        });
    }

    @Override
    public void CanAlchemist() {
        houseAlchemistBtn.setEnabled(true);
    }

    @Override
    public void CanNotAlchemist() {
        houseAlchemistBtn.setEnabled(false);
    }

    @OnClick(R.id.houseAlchemistBtn)
    public void houseAlchemistBtn(View v) {
        mPresenter.alchemist(bagAdapter, bottleAdapter);
        v.setEnabled(false);
    }
}
