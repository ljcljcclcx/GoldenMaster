package com.clcx.goldenmaster.ui.house;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.tools.AnimationTools;
import com.clcx.goldenmaster.basement.tools.ImageUtil;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.factories.MaterialFactory;
import com.clcx.goldenmaster.ui.alchemist.AtyAlchemit;
import com.clcx.goldenmaster.ui.bag.AtyBag;
import com.clcx.goldenmaster.ui.market.AtyMarket;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AtyAlchemistHouse extends BaseActivity<PresenterAlHouse, HouseContract.Model> implements HouseContract
        .View {
    @Bind(R.id.houseBag)
    TextView houseBag;
    @Bind(R.id.houseNews)
    TextView houseNews;
    @Bind(R.id.houseRest)
    TextView houseRest;
    @Bind(R.id.houseCollection)
    TextView houseCollection;
    @Bind(R.id.houseSold)
    TextView houseSold;
    @Bind(R.id.houseAlchemist)
    TextView houseAlchemist;
    @Bind(R.id.houseState)
    TextView houseState;
    @Bind(R.id.houseStateImage)
    ImageView houseStateImage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_house;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.houseSold:
                startActivity(new Intent(this, AtyMarket.class));
                break;
            case R.id.houseAlchemist:
                startActivity(new Intent(this, AtyAlchemit.class));
                break;
            case R.id.houseCollection:
                mPresenter.testItems();
                break;
            case R.id.houseBag:
                startActivity(new Intent(this, AtyBag.class));
                break;
            case R.id.houseNews:
                break;
            case R.id.houseRest:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Config.cacheAlchemista(this, Config.getAlchemista(this));
    }

    @Override
    public void setTextviewState() {
        houseState.setText(Config.getAlchemista(this).getName()
                + "\n经验值：" + Config.getAlchemista(this).getExp()
                + "\n资产：$" + Config.getAlchemista(this).getMoney());
    }

    @Override
    public void setRecyclerViewAdapter() {

    }


    @Override
    public void turnToNews() {

    }

    @Override
    public void turnToRest() {

    }

    @Override
    public void turnToCollection() {

    }


    @Override
    public void initView() {
        houseBag.setOnClickListener(this);
        houseRest.setOnClickListener(this);
        houseNews.setOnClickListener(this);
        houseCollection.setOnClickListener(this);
        houseSold.setOnClickListener(this);
        houseAlchemist.setOnClickListener(this);

        houseBag.setBackgroundColor(Color.rgb(200, 100, 100));
        houseRest.setBackgroundColor(Color.rgb(200, 100, 50));
        houseNews.setBackgroundColor(Color.rgb(100, 100, 200));
        houseCollection.setBackgroundColor(Color.rgb(100, 100, 100));
        houseSold.setBackgroundColor(Color.rgb(100, 200, 100));
        houseAlchemist.setBackgroundColor(Color.rgb(150, 150, 50));

        ImageUtil.loadRoundImg(houseStateImage, "http://tupian.qqjay" +
                ".com/tou3/2016/0725/cb00091099ffbf09f4861f2bbb5dd993.jpg");

        mPresenter.initUI();
    }


}
