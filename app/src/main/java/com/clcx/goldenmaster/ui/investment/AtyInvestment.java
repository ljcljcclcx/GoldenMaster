package com.clcx.goldenmaster.ui.investment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemiItem;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.beans.MarketItem;
import com.clcx.goldenmaster.customview.CapacityBean;
import com.clcx.goldenmaster.customview.CapacityChartView;
import com.clcx.goldenmaster.ui.market.MarketModel;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyInvestment extends BaseActivity<InvestmentPresenter, InvestmentModel> implements InvestmentContract
        .View {

    @Bind(R.id.investment1)
    TextView investment1;
    @Bind(R.id.investment2)
    TextView investment2;
    @Bind(R.id.investment3)
    TextView investment3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_investment;
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
        setText(investment1, Config.INVESTTYPE[0]);
        setText(investment2, Config.INVESTTYPE[1]);
        setText(investment3, Config.INVESTTYPE[2]);
    }

    private void setText(TextView tv, InvestType type) {
        String str = "【" + type.getIntro() + "】\n投资时间：" + type.getHour() + "小时\n投资回报率：" + (type
                .getFeedback() * 100.0f) + "%/小时";
        tv.setText(str);
    }

    @OnClick(R.id.investment1)
    public void investment1(View v) {
        if (InvestmentModel.judgeIfInvest()) {
            createInvestMarketView(0);
        } else {
            ToastClcxUtil.getInstance().showToast("您已投资过！");
        }

    }

    @OnClick(R.id.investment2)
    public void investment2(View v) {
        if (InvestmentModel.judgeIfInvest()) {
            createInvestMarketView(1);
        } else {
            ToastClcxUtil.getInstance().showToast("您已投资过！");
        }
    }

    @OnClick(R.id.investment3)
    public void investment3(View v) {
        if (InvestmentModel.judgeIfInvest()) {
            createInvestMarketView(2);
        } else {
            ToastClcxUtil.getInstance().showToast("您已投资过！");
        }
    }


    private void createInvestMarketView(final int typeId) {
        View view = View.inflate(this, R.layout.alertdialog_investment, null);
        final EditText etInvestmentAlert = (EditText) view.findViewById(R.id.etInvestmentAlert);
        final SeekBar skbInvestmentAlert = (SeekBar) view.findViewById(R.id.skbInvestmentAlert);
        final TextView tvInvestmentAlertTitle = (TextView) view.findViewById(R.id.tvInvestmentAlertTitle);
        tvInvestmentAlertTitle.setText("【" + Config.INVESTTYPE[typeId].getIntro() + "】");
        skbInvestmentAlert.setMax(Config.getAlchemista().getMoney());
        skbInvestmentAlert.setProgress(0);
        etInvestmentAlert.setText("$" + skbInvestmentAlert.getProgress());
        skbInvestmentAlert.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etInvestmentAlert.setText("$" + progress);
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
                .setPositiveButton("投资", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InvestmentModel.cacheInvestment(typeId, skbInvestmentAlert.getProgress());
                        AlchemistaAction.builder().getMoney(-1 * skbInvestmentAlert.getProgress());
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
    }
}
