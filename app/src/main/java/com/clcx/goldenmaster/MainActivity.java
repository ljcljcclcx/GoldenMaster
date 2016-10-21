package com.clcx.goldenmaster;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.clcx.goldenmaster.activies.AtyCreator;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.LogCLCXUtils;
import com.clcx.goldenmaster.tools.AnimationTools;
import com.clcx.goldenmaster.tools.IAnimationTools;

public class MainActivity extends BaseActivity {

    private TextView mainaty_title;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        mainaty_title = (TextView) findViewById(R.id.mainaty_title);
        try {
            mainaty_title.setText("炼金师\nV" + getPackageManager().getPackageInfo
                    (getPackageName(), 0).versionName);
        } catch (Exception e) {

        }

        animationShow();

    }

    private void animationShow() {
        AnimationTools.getInstance().alphaAnimation(0.0f, 1.0f).setDuration(500).start(mainaty_title, new
                IAnimationTools
                        () {
                    @Override
                    public void animateEnd() {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                SystemClock.sleep(1000);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animationDismiss();
                                    }
                                });
                            }
                        }).start();

                    }
                });
    }

    private void animationDismiss() {
        LogCLCXUtils.e("animationDismiss");
        AnimationTools.getInstance().alphaAnimation(1.0f, 0.0f).setDuration(500).setFillAfter(true).start
                (mainaty_title, new IAnimationTools() {
                    @Override
                    public void animateEnd() {
                        startActivity(new Intent(MainActivity.this, AtyCreator.class));
                        finish();
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
