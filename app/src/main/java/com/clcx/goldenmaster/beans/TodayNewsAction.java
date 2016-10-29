package com.clcx.goldenmaster.beans;

import com.clcx.goldenmaster.basement.util.MathClcxUtil;
import com.clcx.goldenmaster.factories.MaterialFactory;

/**
 * Created by ljc123 on 2016/10/26.
 */

public class TodayNewsAction {
    private static TodayNewsAction instance = null;

    public static TodayNewsAction getInstance() {
        if (instance == null) {
            synchronized (TodayNewsAction.class) {
                if (instance == null) {
                    instance = new TodayNewsAction();
                }
            }
        }
        return instance;
    }

    public TodayNews createNews() {
        int largeRandom = MathClcxUtil.getInstance().randomInt(3);
        int smallRandom = MathClcxUtil.getInstance().randomInt(3);
        int state[] = new int[]{0, 1, 2, 3, 4, 5};
        int stateLenght = state.length;
        int largeUp[] = new int[largeRandom];
        int smallUp[] = new int[smallRandom];
        for (int a = 0; a < largeRandom; a++) {
            int r = MathClcxUtil.getInstance().randomInt(stateLenght);
            largeUp[a] = state[r];
            state[r] = state[stateLenght - 1];
            stateLenght--;
        }
        for (int a = 0; a < smallRandom; a++) {
            int r = MathClcxUtil.getInstance().randomInt(stateLenght);
            smallUp[a] = state[r];
            state[r] = state[stateLenght - 1];
            stateLenght--;
        }
        TodayNews news = new TodayNews("今日炼金界特大新闻！", largeUp, smallUp);
        return news;
    }
}
