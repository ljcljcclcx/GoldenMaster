package com.clcx.goldenmaster.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public interface IAlchemistaAction {
    void alchemist(List<AlchemiItem> alitems);

    void putItemToBag(AlchemiItem item);

    void soldItem(AlchemiItem item);

    void lostItem(int location);//失去物品

    void lockUnlockItem(int location);//锁定物品

    void putItemToShop(AlchemiItem item);

    String getAlchemistaNickName();//获取称号

    void getMoney(int price);

    void reduceEnerge(int reduce);//减少体力

    void addExp(int exp);//增加经验值
}
