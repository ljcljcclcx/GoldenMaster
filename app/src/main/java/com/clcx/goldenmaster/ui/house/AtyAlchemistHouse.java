package com.clcx.goldenmaster.ui.house;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.R;
import com.clcx.goldenmaster.adapters.AlchemistAdapter;
import com.clcx.goldenmaster.adapters.HouseBagAdapter;
import com.clcx.goldenmaster.adapters.MarketAdapter;
import com.clcx.goldenmaster.basement.BaseActivity;
import com.clcx.goldenmaster.basement.tools.AnimationTools;
import com.clcx.goldenmaster.basement.tools.ImageUtil;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.MathClcxUtil;
import com.clcx.goldenmaster.basement.util.NotifClcxUtil;
import com.clcx.goldenmaster.basement.util.ToastClcxUtil;
import com.clcx.goldenmaster.beans.AlchemistaAction;
import com.clcx.goldenmaster.customview.AlertPoint;
import com.clcx.goldenmaster.customview.AlwaysMarqueeTextView;
import com.clcx.goldenmaster.customview.EnergeBar;
import com.clcx.goldenmaster.factories.MaterialFactory;
import com.clcx.goldenmaster.service.MessageLocalService;
import com.clcx.goldenmaster.service.MessageProService;
import com.clcx.goldenmaster.ui.Rest.AtyRest;
import com.clcx.goldenmaster.ui.alchemist.AtyAlchemit;
import com.clcx.goldenmaster.ui.appraisal.AtyAppraisal;
import com.clcx.goldenmaster.ui.bag.AtyBag;
import com.clcx.goldenmaster.ui.headselector.ClipActivity;
import com.clcx.goldenmaster.ui.investment.AtyInvestment;
import com.clcx.goldenmaster.ui.market.AtyMarket;
import com.clcx.goldenmaster.ui.message.AtyMessage;
import com.clcx.goldenmaster.ui.message.MessageModel;
import com.clcx.goldenmaster.ui.mission.AtyMission;
import com.clcx.goldenmaster.ui.searchmap.AtySearchmap;

import org.json.JSONArray;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AtyAlchemistHouse extends BaseActivity<PresenterAlHouse, HouseContract.Model> implements HouseContract
        .View {
    @Bind(R.id.houseBag)
    TextView houseBag;
    @Bind(R.id.houseMessage)
    RelativeLayout houseMessage;
    @Bind(R.id.houseRest)
    TextView houseRest;
    @Bind(R.id.houseCollection)
    TextView houseCollection;
    @Bind(R.id.houseMission)
    TextView houseMission;
    @Bind(R.id.houseSold)
    TextView houseSold;
    @Bind(R.id.houseAlchemist)
    TextView houseAlchemist;
    @Bind(R.id.houseMoneyExp)
    TextView houseMoneyExp;
    @Bind(R.id.houseState)
    TextView houseState;
    @Bind(R.id.houseInvestment)
    TextView houseInvestment;
    @Bind(R.id.houseAppraisal)
    TextView houseAppraisal;
    @Bind(R.id.houseStateImage)
    ImageView houseStateImage;
    @Bind(R.id.houseEnergeBar)
    EnergeBar houseEnergeBar;
    @Bind(R.id.houseMarqueeTv)
    AlwaysMarqueeTextView houseMarqueeTv;
    @Bind(R.id.houseMessageAlertPoint)
    AlertPoint houseMessageAlertPoint;


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
            case R.id.houseCollection:
                mPresenter.testItems();
                startActivity(new Intent(this, AtySearchmap.class));
                break;
            case R.id.houseBag:
                startActivity(new Intent(this, AtyBag.class));
                break;
            case R.id.houseMessage:
                startActivity(new Intent(this, AtyMessage.class));
                break;
            case R.id.houseRest:
                startActivity(new Intent(this, AtyRest.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Config.cacheAlchemista(this, Config.getAlchemista());
    }

    @Override
    public void setTextviewState() {
        if (houseState != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(" <font color='#ff0000'>");
            sb.append("【" + AlchemistaAction.builder().getAlchemistaNickName() + "】");
            sb.append("</font>");
            sb.append(Config.getAlchemista().getName());
            houseState.setText(Html.fromHtml(sb.toString()));
            houseMoneyExp.setText("经验值：" + Config.getAlchemista().getExp()
                    + "  资产：$" + MathClcxUtil.getInstance().moneyFormat(Config.getAlchemista().getMoney()));
        }
        if (houseEnergeBar != null) {
            houseEnergeBar.initUI(100, Config.getAlchemista().getEnerge());
        }
        houseMarqueeTv.setText(this, Config.getTodayNews().getContent());


    }

    @Override
    public void initView() {
        houseEnergeBar.initUI(100, Config.getAlchemista().getEnerge());

        houseBag.setOnClickListener(this);
        houseRest.setOnClickListener(this);
        houseMessage.setOnClickListener(this);
        houseCollection.setOnClickListener(this);
        houseSold.setOnClickListener(this);

//        houseBag.setBackgroundResource(R.drawable.text_border);
//        houseRest.setBackgroundResource(R.drawable.text_border);
//        houseMessage.setBackgroundResource(R.drawable.text_border);
//        houseCollection.setBackgroundResource(R.drawable.text_border);
//        houseSold.setBackgroundResource(R.drawable.text_border);
//        houseAlchemist.setBackgroundResource(R.drawable.text_border);
//        houseInvestment.setBackgroundResource(R.drawable.text_border);
//        houseAppraisal.setBackgroundResource(R.drawable.text_border);
//        houseMission.setBackgroundResource(R.drawable.text_border);

        ImageUtil.loadRoundImg(houseStateImage, Config.getInnerPath("headImage") + File.separator + "AAA.jpg");
        mPresenter.initUI();
        // 开启服务
        startService(new Intent(this,
                MessageLocalService.class));
        startService(new Intent(this,
                MessageProService.class));
        houseMessageAlertPoint.setMessageCount(MessageModel.getMessages().size());

    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageUtil.loadRoundImg(houseStateImage, Config.getInnerPath("headImage") + File.separator + "AAA.jpg");
        Glide.get(this).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(AtyAlchemistHouse.this).clearDiskCache();
            }
        });
        mPresenter.initUI();
        //初始化信息条数
        if (houseMessageAlertPoint != null) {
            houseMessageAlertPoint.setMessageCount(MessageModel.getMessages().size());
        }
    }

    @OnClick(R.id.houseStateImage)
    public void houseStateImage(View v) {
        startActivity(new Intent(this, ClipActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //写下你希望按下返回键达到的效果代码，不写则不会有反应
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.houseInvestment)
    public void houseInvestment(View v) {
        startActivity(new Intent(this, AtyInvestment.class));
    }

    @OnClick(R.id.houseAppraisal)
    public void houseAppraisal(View v) {
        startActivity(new Intent(this, AtyAppraisal.class));
    }

    @OnClick(R.id.houseAlchemist)
    public void houseAlchemist(View v) {
        startActivity(new Intent(this, AtyAlchemit.class));
//        ActivityCompat.startActivity(this, new Intent(this, AtyAlchemit.class),
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this, houseStateImage, AtyAlchemit.TRANS_IMAGE)
//                        .toBundle());

    }


    @OnClick(R.id.houseMission)
    public void houseMission(View v) {
        startActivity(new Intent(this, AtyMission.class));
    }
}
