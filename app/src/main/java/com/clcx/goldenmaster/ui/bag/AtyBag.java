package com.clcx.goldenmaster.ui.bag;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlItemProduct;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.ui.alchemist.AtyAlchemit;
import com.clcx.goldenmaster.ui.appraisal.AppraisalModel;
import com.clcx.goldenmaster.ui.appraisal.AppraisalType;
import com.clcx.goldenmaster.ui.appraisal.AtyAppraisal;
import com.clcx.goldenmaster.ui.creator.CreatorContract;
import com.clcx.goldenmaster.ui.creator.CreatorModel;
import com.clcx.goldenmaster.ui.creator.PreCreator;
import com.clcx.goldenmaster.ui.house.AtyAlchemistHouse;
import com.clcx.goldenmaster.ui.market.MarketModel;

import butterknife.Bind;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyBag extends BaseActivity<BagPresenter, BagModel> implements BagContract.View {
    @Bind(R.id.houseRecyclerBag)
    RecyclerView houseRecyclerBag;
    private HouseBagAdapter adapter;

    private boolean isApprasal;

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
    protected void onDestroy() {
        super.onDestroy();

        /**
         * 如果来自鉴定，则返回时弹出取消鉴定，并返还金钱
         */
        if (isApprasal) {
            AlchemistaAction.builder().getMoney(getIntent().getIntExtra("appralsalmoney", 0));
            ToastClcxUtil.getInstance().showToast("鉴定取消，返还金钱");
        }
    }

    @Override
    public void initView() {
        isApprasal = getIntent().getBooleanExtra("isappralsal", false);
        //背包的adapter
        adapter = new HouseBagAdapter(this);
        adapter.setItems(Config.getAlchemista().getBag());
        houseRecyclerBag.setLayoutManager(new LinearLayoutManager(this));
        houseRecyclerBag.setAdapter(adapter);
        adapter.setOnItemClickListener(new HouseBagAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
                final AlchemiItem item = (AlchemiItem) adapter.getItem(position);
                if (isApprasal) {
                    //判断如果来自于鉴定，则直接鉴定
                    if (item instanceof AlItemProduct && !item.isAppraisal()) {
                        AlItemProduct product = (AlItemProduct) item;
                        AppraisalType type = AppraisalModel.appraisalProduct(product, getIntent().getIntExtra
                                ("apprasalLevel", 0));
                        product.reName(type);
                        AlchemistaAction.builder().putItemToBag(product);
                        AlchemistaAction.builder().lostItem(position);
                        ToastClcxUtil.getInstance().showToast("鉴定完成！鉴定结果为：【" + type.getName() + "药剂】，价值提升+" + ((type
                                .getScore() * 100) - 100) + "%");
                        isApprasal = false;
                        finish();

                    } else {
                        ToastClcxUtil.getInstance().showToast("不可鉴定材料或是已鉴定药剂！");
                    }
                } else {
                    //否则显示上架等
                    AlertDialog dialog = new AlertDialog.Builder(AtyBag.this)
                            .setItems(new String[]{"上架", "炼金", "鉴定"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            createPutMarketView(item, position);
                                            break;
                                        case 1:
                                            startActivity(new Intent(AtyBag.this, AtyAlchemit.class));
                                            finish();
                                            break;
                                        case 2:
                                            startActivity(new Intent(AtyBag.this, AtyAppraisal.class));
                                            finish();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }).create();
                    dialog.show();
                }
            }
        });
    }

    private void createPutMarketView(final AlchemiItem item, final int location) {
        View view = View.inflate(this, R.layout.alertdialog_bag, null);
        final EditText etBagAlertDialogView = (EditText) view.findViewById(R.id.etBagAlertDialogView);
        final SeekBar skbBagAlertDialogView = (SeekBar) view.findViewById(R.id.skbBagAlertDialogView);
        skbBagAlertDialogView.setMax(item.getPrice() * 2);
        skbBagAlertDialogView.setProgress(item.getPrice());
        etBagAlertDialogView.setText("$" + skbBagAlertDialogView.getProgress());
        skbBagAlertDialogView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etBagAlertDialogView.setText("$" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("上架", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MarketModel.putItemToMarket(new MarketItem(item, skbBagAlertDialogView.getProgress(), Config
                                .getAlchemista().getName()));
                        mPresenter.sellItem(location, adapter);
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
    }

}
