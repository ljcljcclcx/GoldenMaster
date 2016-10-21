package com.clcx.goldenmaster.activies;

import android.view.View;
import android.widget.Button;

import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.basement.BaseActivity;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AtyAlchemistHouse extends BaseActivity {

    private Button houseAlchemist;
    private Button houseSold;

    @Override
    public int bindLayout() {
        return R.layout.activity_house;
    }

    @Override
    public void initView(View view) {
        houseAlchemist = (Button) findViewById(R.id.houseAlchemist);
        houseSold = (Button) findViewById(R.id.houseSold);

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
