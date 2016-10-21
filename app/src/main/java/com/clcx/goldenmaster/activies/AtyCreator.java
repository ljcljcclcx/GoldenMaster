package com.clcx.goldenmaster.activies;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.ToastClcxUtil;
import com.clcx.goldenmaster.beans.Alchemista;

/**
 * Created by ljc123 on 2016/10/21.
 * 创建炼金师
 */
public class AtyCreator extends BaseActivity {
    private Button btnCreator;
    private EditText etCreator;

    @Override
    public int bindLayout() {
        return R.layout.activity_creator;
    }

    @Override
    public void initView(View view) {
        btnCreator = (Button) findViewById(R.id.btnCreator);
        etCreator = (EditText) findViewById(R.id.etCreator);
        etCreator.setText(Config.getAlchemista(this) == null ? "" : Config.getAlchemista
                (this).getName());

        btnCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etCreator.getText().toString())) {
                    ToastClcxUtil.getInstance().showToast(AtyCreator.this, "名字不能为空");
                } else {
                    Config.cacheAlchemista(AtyCreator.this, new Alchemista(etCreator.getText().toString()));
                    startActivity(new Intent(AtyCreator.this, AtyAlchemistHouse.class));
                    finish();
                }
            }
        });
    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {

    }
}
