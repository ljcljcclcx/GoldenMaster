package com.clcx.goldenmaster.ui.creator;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.ui.house.AtyAlchemistHouse;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.Alchemista;

import butterknife.Bind;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyCreator extends BaseActivity<PreCreator, CreatorModel> implements CreatorContract.View {
    @Bind(R.id.btnCreator)
    Button btnCreator;
    @Bind(R.id.btnDelete)
    Button btnDelete;
    @Bind(R.id.etCreator)
    EditText etCreator;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreator:
                if (Config.getAlchemista() != null) {
                    startActivity(new Intent(AtyCreator.this, AtyAlchemistHouse.class));
                    finish();
                } else {
                    if (TextUtils.isEmpty(etCreator.getText().toString())) {
                        ToastClcxUtil.getInstance().showToast("名字不能为空");
                    } else {
                        Alchemista alc = new Alchemista(etCreator.getText().toString());
                        Config.cacheAlchemista(AtyCreator.this, alc);
                        alc.setEnerge(100);
                        Config.cacheAlchemista(AtyCreator.this, alc);
                        Config.newDay();
                        startActivity(new Intent(AtyCreator.this, AtyAlchemistHouse.class));
                        finish();
                    }
                }

                break;
            case R.id.btnDelete:
                mPresenter.deleteAlchemista();
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_creator;
    }

    @Override
    public void initView() {
        etCreator.setText(Config.getAlchemista() == null ? "" : Config.getAlchemista
                ().getName());
        btnCreator.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        if (Config.getAlchemista() != null) {
            forbidEditText();
        } else {
            releaseEditText();
        }
    }


    @Override
    public void forbidEditText() {
        etCreator.setEnabled(false);
        btnCreator.setText("进入游戏");
    }

    @Override
    public void releaseEditText() {
        etCreator.setEnabled(true);
        etCreator.setText("");
        btnCreator.setText("创建人物");
    }
}
