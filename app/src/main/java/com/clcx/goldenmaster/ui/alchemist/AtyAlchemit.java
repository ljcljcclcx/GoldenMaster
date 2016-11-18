package com.clcx.goldenmaster.ui.alchemist;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.tools.ImageUtil;
import com.clcx.goldenmaster.customview.MyButton;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyAlchemit extends BaseActivity<AlchemistPresenter, AlchemistModel> implements AlchemistContract.View {
    @Bind(R.id.houseAlchemistBtn)
    MyButton houseAlchemistBtn;
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
        CanNotAlchemist();
        //炼金时的两个adapter
        bagAdapter = new AlchemistAdapter(this, false);
        bagAdapter.setItems(Config.getAlchemista().getBag());
        houseRecyclerBagWhenAlchemist.setLayoutManager(new GridLayoutManager(this, 3));
        houseRecyclerBagWhenAlchemist.setAdapter(bagAdapter);
        bagAdapter.setOnItemClickListener(new AlchemistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mPresenter.addItemToBottle(position, bagAdapter, bottleAdapter);
            }
        });
        bottleAdapter = new AlchemistAdapter(this, true);
        houseRecyclerAlchemistBottle.setLayoutManager(new GridLayoutManager(this, 3));
        houseRecyclerAlchemistBottle.setAdapter(bottleAdapter);
        bottleAdapter.setOnItemClickListener(new AlchemistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mPresenter.removeFromBottle(position, bagAdapter, bottleAdapter);
            }
        });
        houseAlchemistBtn.setmEnabled(false);
    }

    @Override
    public void CanAlchemist() {
        houseAlchemistBtn.setmEnabled(true);
    }

    @Override
    public void CanNotAlchemist() {
        houseAlchemistBtn.setmEnabled(false);
    }

    @OnClick(R.id.houseAlchemistBtn)
    public void houseAlchemistBtn(View v) {
        mPresenter.alchemist(bagAdapter, bottleAdapter);
        houseAlchemistBtn.setmEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.removeAllFromBottle();
    }
}
