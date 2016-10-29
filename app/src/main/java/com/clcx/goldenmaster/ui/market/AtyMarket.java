package com.clcx.goldenmaster.ui.market;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.customview.CapacityBean;
import com.clcx.goldenmaster.customview.CapacityChartView;

import butterknife.Bind;
import butterknife.OnClick;

import static android.R.attr.layout_height;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyMarket extends BaseActivity<MarketPresenter, MarketModel> implements MarketContract.View {
    @Bind(R.id.houseMarketBg)
    LinearLayout houseMarketBg;
    @Bind(R.id.houseRecyclerMarket)
    RecyclerView houseRecyclerMarket;

    @Bind(R.id.houseMarketOrderPrice)
    TextView houseMarketOrderPrice;
    @Bind(R.id.houseMarketOrderQiwei)
    TextView houseMarketOrderQiwei;
    @Bind(R.id.houseMarketOrderYanse)
    TextView houseMarketOrderYanse;
    @Bind(R.id.houseMarketOrderJiazhi)
    TextView houseMarketOrderJiazhi;
    @Bind(R.id.houseMarketOrderDuxing)
    TextView houseMarketOrderDuxing;
    @Bind(R.id.houseMarketOrderWaiguan)
    TextView houseMarketOrderWaiguan;
    @Bind(R.id.houseMarketOrderCIji)
    TextView houseMarketOrderCIji;
    private MarketAdapter marketAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_market;
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
        //市场的adapter
        marketAdapter = new MarketAdapter(this);
        houseRecyclerMarket.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        houseRecyclerMarket.setAdapter(marketAdapter);
        marketAdapter.setItems(mModel.getMarketItems());
        marketAdapter.setOnItemClickListener(new MarketAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                MarketItem item = (MarketItem) marketAdapter.getItem(position);
                createChecfinalkDialog(item.getItem(), position);
            }
        });
    }

    private void createChecfinalkDialog(AlchemiItem item, final int position) {
        View view = View.inflate(this, R.layout.alertdialog_market, null);
        final TextView marketDialogTv = (TextView) view.findViewById(R.id.marketDialogTv);
        final CapacityChartView marketDialogCapacity = (CapacityChartView) view.findViewById(R.id.marketDialogCapacity);
        marketDialogTv.setText(item.getIntro());
        marketDialogCapacity.setData(Config.setCapacities(item), 15);
        marketDialogCapacity.setOnCapacityTopPointTouchListener(new CapacityChartView
                .OnCapacityTopPointTouchListener() {
            @Override
            public void touchCapacity(CapacityBean bean) {
                ToastClcxUtil.getInstance().showToast(bean.getCapacityName() + "：" + bean
                        .getCapacityPoint());
            }
        });

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("购买", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.buyItem(position);
                    }
                })
                .setNegativeButton("取消", null)
                .create();

        dialog.show();

    }

    @OnClick(R.id.houseMarketOrderPrice)
    public void houseMarketOrderPrice(View v) {
        setOrderHighLight((TextView) v);
        mPresenter.orderByPrice(marketAdapter);
    }

    @OnClick(R.id.houseMarketOrderQiwei)
    public void houseMarketOrderQiwei(View v) {
        setOrderHighLight((TextView) v);
        mPresenter.orderByQiwei(marketAdapter);
    }

    @OnClick(R.id.houseMarketOrderYanse)
    public void houseMarketOrderYanse(View v) {
        setOrderHighLight((TextView) v);
        mPresenter.orderByYanse(marketAdapter);
    }

    @OnClick(R.id.houseMarketOrderCIji)
    public void houseMarketOrderCIji(View v) {
        setOrderHighLight((TextView) v);
        mPresenter.orderByCiji(marketAdapter);
    }

    @OnClick(R.id.houseMarketOrderWaiguan)
    public void houseMarketOrderWaiguan(View v) {
        setOrderHighLight((TextView) v);
        mPresenter.orderByWaiguan(marketAdapter);
    }

    @OnClick(R.id.houseMarketOrderDuxing)
    public void houseMarketOrderDuxing(View v) {
        setOrderHighLight((TextView) v);
        mPresenter.orderByDuxing(marketAdapter);
    }

    @OnClick(R.id.houseMarketOrderJiazhi)
    public void houseMarketOrderJiazhi(View v) {
        setOrderHighLight((TextView) v);
        mPresenter.orderByJiazhi(marketAdapter);
    }

    /**
     * 设置排序高亮显示
     */
    private void setOrderHighLight(TextView v) {
        houseMarketOrderPrice.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        houseMarketOrderQiwei.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        houseMarketOrderYanse.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        houseMarketOrderJiazhi.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        houseMarketOrderDuxing.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        houseMarketOrderWaiguan.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        houseMarketOrderCIji.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        v.setShadowLayer(15.0f, 0, 0, Color.RED);
    }

    @Override
    public MarketAdapter getAdapter() {
        return marketAdapter;
    }
}
