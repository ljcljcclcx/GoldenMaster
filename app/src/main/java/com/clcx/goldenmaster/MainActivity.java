package com.clcx.goldenmaster;

import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.clcx.goldenmaster.basement.util.OrderClcxUtil;
import com.clcx.goldenmaster.beans.Alchemista;
import com.clcx.goldenmaster.ui.house.AtyAlchemistHouse;
import com.clcx.goldenmaster.ui.creator.AtyCreator;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.tools.AnimationTools;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.mainaty_title)
    TextView mainaty_title;

    private void animationShow() {
        AnimationTools.getInstance().alphaAnimation(0.0f, 1.0f).setDuration(500).start(mainaty_title, new
                AnimationTools.IAnimationTools
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
        AnimationTools.getInstance().alphaAnimation(1.0f, 0.0f).setDuration(500).setFillAfter(true).start
                (mainaty_title, new AnimationTools.IAnimationTools() {
                    @Override
                    public void animateEnd() {
                        startActivity(new Intent(MainActivity.this, AtyCreator.class));
                        finish();
                    }
                });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainaty_title = (TextView) findViewById(R.id.mainaty_title);
        try {
            mainaty_title.setText("炼金师\nV" + getPackageManager().getPackageInfo
                    (getPackageName(), 0).versionName);
        } catch (Exception e) {

        }
        animationShow();
//        order();
    }

    private void order() {
        List<Integer> intss = new ArrayList<>();
        intss.add(2);
        intss.add(23);
        intss.add(115);
        intss.add(12);
        intss.add(18);
        intss.add(88);
        intss.add(92);
        intss.add(42);
        intss.add(19);
        OrderClcxUtil.getInstance().insertOrder(intss);
        LogCLCXUtils.e(intss + "s");
    }

    private void testRefrection() throws ClassNotFoundException {
        Class tmp = Class.forName("com.clcx.goldenmaster.beans.Alchemista");
        Method method[] = tmp.getMethods();
        for (Method m : method) {
            LogCLCXUtils.e(m.getName());
        }
    }
}

