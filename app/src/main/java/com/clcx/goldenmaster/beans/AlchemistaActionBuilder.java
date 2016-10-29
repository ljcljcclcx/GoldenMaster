package com.clcx.goldenmaster.beans;

import android.content.Context;

import com.clcx.goldenmaster.Config;
import com.clcx.goldenmaster.basement.util.LogCLCXUtils;
import com.clcx.goldenmaster.basement.util.MathClcxUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AlchemistaActionBuilder implements IAlchemistaAction {

    private Alchemista alchemista;
    private Context context;

    public AlchemistaActionBuilder(Context context, Alchemista alchemista) {
        this.alchemista = alchemista;
        this.context = context;
    }

    private static final String[] PRODUCT_LASTNAME_RANDOM = {"α", "β", "γ", "∑", "XY"};

    /**
     * 开始炼金
     *
     * @param alitems
     */
    @Override
    public void alchemist(List<AlchemiItem> alitems) {
        List<String> preNames = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();
        for (int b = 0; b < alitems.size(); b++) {
            if (!alitems.get(b).isAppraisal()) {
                preNames.add(alitems.get(b).getPrename());
                lastNames.add(alitems.get(b).getLastName());
            }
        }
        int randomInt = MathClcxUtil.getInstance().randomInt(alitems.size());
        String productPreName = preNames.size() > 0 ? preNames.get(randomInt) : "炼金师";
        if (lastNames.size() > 0) {
            lastNames.remove(randomInt);
        }
        randomInt = MathClcxUtil.getInstance().randomInt(lastNames.size());
        String productLastName = lastNames.size() > 0 ? lastNames.get(randomInt) :
                PRODUCT_LASTNAME_RANDOM[MathClcxUtil.getInstance().randomInt(PRODUCT_LASTNAME_RANDOM.length)];
        if (productLastName.length() >= 2) {
            if (!productLastName.substring(productLastName.length() - 2).equals("试剂")) {
                productLastName = productLastName + "试剂";
            }
        } else {
            productLastName = productLastName + "试剂";
        }

        float[] productProperties = new float[AlchemiItem.MAX_PROPERTY_COUNT];
        //计算所有属性结果之和
        for (int a = 0; a < alitems.size(); a++) {
            for (int i = 0; i < AlchemiItem.MAX_PROPERTY_COUNT; i++) {
                productProperties[i] += alitems.get(a).getPropertiesPoint()[i];
            }
        }
        //打印测试结果
//        for (int i = 0; i < productProperties.length; i++) {
//            LogCLCXUtils.e(alitems.get(0).getProperties()[i] + ":" + productProperties[i]);
//        }
        //计算平均值
        for (int i = 0; i < AlchemiItem.MAX_PROPERTY_COUNT; i++) {
            productProperties[i] = (int) ((float) productProperties[i] / (float) alitems.size());
        }
        //打印测试结果
//        for (int i = 0; i < productProperties.length; i++) {
//            LogCLCXUtils.e("平均：" + alitems.get(0).getProperties()[i] + ":" + productProperties[i]);
//        }
        List<Integer> resultGain = new ArrayList<>();
        List<Integer> resultRestrain = new ArrayList<>();
        //计算增益影响，并刷新增益值
        for (int a = 0; a < alitems.size(); a++) {
            //增益
            for (int add = 0; add < alitems.get(a).getGainStateId().length; add++) {
                productProperties[alitems.get(a).getGainStateId()[add]]++;
                addState(resultGain, alitems.get(a).getGainStateId()[add]);
            }
            //减益
            for (int add = 0; add < alitems.get(a).getRestrainStateId().length; add++) {
                productProperties[alitems.get(a).getRestrainStateId()[add]]--;
                if (productProperties[alitems.get(a).getRestrainStateId()[add]] <= 1.0f) {
                    productProperties[alitems.get(a).getRestrainStateId()[add]] = 1.0f;
                }
                addState(resultRestrain, alitems.get(a).getRestrainStateId()[add]);
            }
        }
        //抵消增减益
        for (int a = 0; a < resultGain.size(); a++) {
            for (int b = 0; b < resultRestrain.size(); b++) {
                if (resultGain.get(a) == resultRestrain.get(b)) {
                    resultGain.remove(a);
                    resultRestrain.remove(b);
                    a--;
                    b--;
                    break;
                }
            }
        }
        //最终增减益
        int[] finalGain = new int[resultGain.size()];
        int[] finalRestrain = new int[resultRestrain.size()];
        for (int a = 0; a < resultGain.size(); a++) {
            finalGain[a] = resultGain.get(a);
        }
        for (int b = 0; b < resultRestrain.size(); b++) {
            finalRestrain[b] = resultRestrain.get(b);
        }

//打印测试结果
//        for (int i = 0; i < productProperties.length; i++) {
//            LogCLCXUtils.e("最终：" + alitems.get(0).getProperties()[i] + ":" + productProperties[i]);
//        }

        AlchemiItem product = new AlItemProduct(productPreName, productLastName, productProperties
                , finalGain
                , finalRestrain
                , Config.getAlchemista().getExp());

        putItemToBag(product);
    }

    private void addState(List<Integer> list, int stateId) {
        for (Integer i : list) {
            if (i == stateId) {
                return;
            }
        }
        list.add(stateId);
    }

    @Override
    public void putItemToBag(AlchemiItem item) {
        alchemista.getBag().add(item);
        Config.cacheAlchemista(context, alchemista);
    }

    @Override
    public void soldItem(AlchemiItem item) {
        alchemista.getBag().remove(item);
        Config.cacheAlchemista(context, alchemista);
    }

    @Override
    public void lostItem(int location) {
        alchemista.getBag().remove(location);
        for (AlchemiItem a : alchemista.getBag()) {
            LogCLCXUtils.e(a.getName());
        }
        Config.cacheAlchemista(context, alchemista);
    }

    @Override
    public void putItemToShop(AlchemiItem item) {
        alchemista.getBag().remove(item);
        Config.cacheAlchemista(context, alchemista);
    }

    @Override
    public String getAlchemistaNickName() {
        if (alchemista.getExp() < 5000) {
            return "炼金师学徒";
        } else if (alchemista.getExp() >= 5000 && alchemista.getExp() < 15000) {
            return "中级炼金师";
        } else {
            return "高级炼金师";
        }
    }

    @Override
    public void getMoney(int price) {
        alchemista.addMoney(price);
        Config.cacheAlchemista(context, alchemista);
    }

    @Override
    public void reduceEnerge(int reduce) {
        alchemista.setEnerge(alchemista.getEnerge() - reduce);
        if (alchemista.getEnerge() >= 100) {
            alchemista.setEnerge(100);
        }
        Config.cacheAlchemista(context, alchemista);
    }

    @Override
    public void addExp(int exp) {
        alchemista.addExp(exp);
        Config.cacheAlchemista(context, alchemista);
    }
}
