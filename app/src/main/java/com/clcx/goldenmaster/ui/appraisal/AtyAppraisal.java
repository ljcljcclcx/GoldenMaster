package com.clcx.goldenmaster.ui.appraisal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.ui.bag.AtyBag;

import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyAppraisal extends BaseActivity<AppraisalPresenter, AppraisalModel> implements AppraisalContract
        .View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_appraisal;
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

    @OnClick(R.id.apprisal1)
    public void apprisal1(View v) {
        if (Config.getAlchemista().getMoney() >= 100) {
            AlchemistaAction.builder().getMoney(-100);
            askIfApprasal(100, 0);
        }
    }

    @OnClick(R.id.apprisal2)
    public void apprisal2(View v) {
        if (Config.getAlchemista().getMoney() >= 1000) {
            AlchemistaAction.builder().getMoney(-1000);
            askIfApprasal(1000, 1);
        }
    }

    private void askIfApprasal(final int money, final int level) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("确定要进行鉴定吗？($" + money + ")")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AtyAppraisal.this, AtyBag.class);
                        intent.putExtra("isappralsal", true);
                        intent.putExtra("appralsalmoney", money);
                        intent.putExtra("apprasalLevel", level);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();

    }
}
