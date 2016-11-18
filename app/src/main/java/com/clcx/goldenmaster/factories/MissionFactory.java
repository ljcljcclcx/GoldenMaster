package com.clcx.goldenmaster.factories;

import com.clcx.goldenmaster.beans.AlItemMaterial;
import com.clcx.goldenmaster.beans.MissionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/30.
 */

public class MissionFactory {
    public static final int MISSION_ID_MAKE_PRODUCT = 0;
    public static final int MISSION_ID_INVESTMENT = 1;
    public static final int MISSION_ID_SOLD_ITEM = 2;

    public static final List<MissionBean> missions = new ArrayList<>();

    static {
        missions.add(new MissionBean("任务：进行一次投资", 1, MISSION_ID_INVESTMENT, 50));
        missions.add(new MissionBean("任务：制造一瓶试剂", 1, MISSION_ID_MAKE_PRODUCT, 75));
        missions.add(new MissionBean("任务：出售十个材料", 10, MISSION_ID_SOLD_ITEM, 100));
        missions.add(new MissionBean("任务：制造三瓶试剂", 3, MISSION_ID_MAKE_PRODUCT, 150));
        missions.add(new MissionBean("任务：进行三次投资", 3, MISSION_ID_INVESTMENT, 200));
    }
}
