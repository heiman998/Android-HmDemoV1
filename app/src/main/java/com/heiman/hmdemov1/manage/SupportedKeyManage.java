package com.heiman.hmdemov1.manage;
 

import com.heiman.hmapisdkv1.utils.HmUtils;
import com.heiman.hmdemov1.modle.SupportedKey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/7 上午11:49
 * @Description :
 * @Modify record :
 */
public class SupportedKeyManage {
    private static SupportedKeyManage instance;
    public static ConcurrentHashMap<String, SupportedKey> supportedKeyConcurrentHashMap = new ConcurrentHashMap<>();

    public static SupportedKeyManage getInstance() {
        if (instance == null) {
            instance = new SupportedKeyManage();
        }
        return instance;
    }

    public static ArrayList<SupportedKey> supportedKeyList = new ArrayList<>();

    public synchronized ArrayList<SupportedKey> getSupportedKey() {
        supportedKeyList.clear();
        Iterator<Map.Entry<String, SupportedKey>> iter = supportedKeyConcurrentHashMap.entrySet()
                .iterator();
        while (iter.hasNext()) {
            Map.Entry<String, SupportedKey> entry = iter.next();
            supportedKeyList.add(entry.getValue());
        }
        return supportedKeyList;
    }


    public SupportedKey getSupportedKey(String key) {
        if (HmUtils.isEmptyString(key)) {
            return new SupportedKey();
        } else {
            return supportedKeyConcurrentHashMap.get(key);
        }
    }

    public SupportedKey getSupportedKeyID(String keyID) {
        SupportedKey supportedKey = null;
        if (HmUtils.isEmptyString(keyID)) {

            return supportedKey;
        }
        Iterator<Map.Entry<String, SupportedKey>> iter = supportedKeyConcurrentHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, SupportedKey> entry = iter.next();
            if (entry != null) {
                if (entry.getValue() != null) {

                    if (!HmUtils.isEmptyString(entry.getValue().getKeyId())) {

                        if (entry.getValue().getKeyId().equals(keyID)) {

                            supportedKey = entry.getValue();
                            break;
                        }
                    }
                }
            }
        }

        return supportedKey;
    }

    public SupportedKey getSupportedKey7(String key7) {
        SupportedKey supportedKey = null;
        Iterator<Map.Entry<String, SupportedKey>> iter = supportedKeyConcurrentHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, SupportedKey> entry = iter.next();
            if (!HmUtils.isEmptyString(entry.getValue().getKeyLabel7()) &&
                    !HmUtils.isEmptyString(key7)) {
                if (entry.getValue().getKeyLabel7().equals(key7)) {
                    supportedKey = entry.getValue();
                    break;
                }
            }
        }
        return supportedKey;
    }

    /**
     * 获取空调码库
     *
     * @param temp  温度
     * @param fan   风
     * @param mode  模式
     * @param power 开关
     * @param swing 风速
     * @return
     */
    public SupportedKey getSupportedAC(String temp, String fan, String mode, String power, String swing) {

        if (HmUtils.isEmptyString(temp) &&
                HmUtils.isEmptyString(fan) &&
                HmUtils.isEmptyString(mode) &&
                HmUtils.isEmptyString(power) &&
                HmUtils.isEmptyString(swing)) {
            return new SupportedKey();
        }


        SupportedKey supportedKey = null;
        for (Map.Entry<String, SupportedKey> entry : supportedKeyConcurrentHashMap.entrySet()) {
            if (!HmUtils.isEmptyString(entry.getValue().getStPower()) && entry.getValue().getStPower().equals(power)) {
                if (power.equals("OFF")) {
                    supportedKey = entry.getValue();
                    break;
                } else {
                    if (!HmUtils.isEmptyString(entry.getValue().getStMode()) && entry.getValue().getStMode().equals(mode)) {
                        switch (mode) {
                            case "HEAT":
                            case "COOL":
                                if (!HmUtils.isEmptyString(entry.getValue().getStFan()) && entry.getValue().getStFan().equals(fan)) {

                                    if (!HmUtils.isEmptyString(entry.getValue().getStSwing()) && entry.getValue().getStSwing().equals(swing)) {

                                        if (!HmUtils.isEmptyString(entry.getValue().getStTemp()) && entry.getValue().getStTemp().equals(temp)) {

                                            supportedKey = entry.getValue();
                                            break;
                                        }
                                    }
                                }
                                break;
                            case "AUTO":
                            case "DRY":
                            case "FAN":
                                supportedKey = entry.getValue();
                                break;
                        }
                    }


                }
            }
        }
        return supportedKey;
    }

    public void addSupportedKey(SupportedKey supportedKey) {
        SupportedKey homes = supportedKeyConcurrentHashMap.get(supportedKey.getKey());
        if (homes != null) { // 如果已经保存过设备，就不add
            supportedKeyConcurrentHashMap.put(supportedKey.getKey(), supportedKey);
            // supportedKey.save();
            return;
        }
        supportedKeyConcurrentHashMap.put(supportedKey.getKey(), supportedKey);

    }

    public void removeSupportedKey(String id) {
        supportedKeyConcurrentHashMap.remove(id);
    }

    public void removeSupportedKey(SupportedKey supportedKey) {
        supportedKeyConcurrentHashMap.remove(supportedKey.getKey());
    }

    public void updateSupportedKey(SupportedKey supportedKey) {
        supportedKeyConcurrentHashMap.remove(supportedKey.getKey());
        supportedKeyConcurrentHashMap.put(supportedKey.getKey(), supportedKey);
        // supportedKey.save();
    }

    public void clearSupportedKey() {
        supportedKeyConcurrentHashMap.clear();
    }


}
