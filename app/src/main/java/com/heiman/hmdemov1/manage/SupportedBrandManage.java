package com.heiman.hmdemov1.manage;


import com.heiman.hmapisdkv1.utils.HmUtils;
import com.heiman.hmdemov1.modle.SupportedBrand;

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
public class SupportedBrandManage {
    private static SupportedBrandManage instance;
    public static ConcurrentHashMap<String, SupportedBrand> supportedDeviceConcurrentHashMap = new ConcurrentHashMap<>();

    public static SupportedBrandManage getInstance() {
        if (instance == null) {
            instance = new SupportedBrandManage();
        }
        return instance;
    }

    public static ArrayList<SupportedBrand> supportedDeviceList = new ArrayList<>();

    public synchronized ArrayList<SupportedBrand> getSupportedDevice() {
        supportedDeviceList.clear();
        Iterator<Map.Entry<String, SupportedBrand>> iter = supportedDeviceConcurrentHashMap.entrySet()
                .iterator();
        while (iter.hasNext()) {
            Map.Entry<String, SupportedBrand> entry = iter.next();
            supportedDeviceList.add(entry.getValue());
        }
        return supportedDeviceList;
    }

    public SupportedBrand getSupportedDevice(String key) {
        if (HmUtils.isEmptyString(key)) {
            return new SupportedBrand();
        } else {
            return supportedDeviceConcurrentHashMap.get(key);
        }
    }

    public void addSupportedDevice(SupportedBrand supportedBrand) {
        SupportedBrand homes = supportedDeviceConcurrentHashMap.get(supportedBrand.getKey());
        if (homes != null) { // 如果已经保存过设备，就不add
            supportedDeviceConcurrentHashMap.put(supportedBrand.getKey(), supportedBrand);
            // supportedBrand.save();
            return;
        }
        supportedDeviceConcurrentHashMap.put(supportedBrand.getKey(), supportedBrand);

    }

    public void removeSupportedDevice(String id) {
        supportedDeviceConcurrentHashMap.remove(id);
    }

    public void removeSupportedDevice(SupportedBrand supportedBrand) {
        supportedDeviceConcurrentHashMap.remove(supportedBrand.getKey());
    }

    public void updateSupportedDevice(SupportedBrand supportedBrand) {
        supportedDeviceConcurrentHashMap.remove(supportedBrand.getKey());
        supportedDeviceConcurrentHashMap.put(supportedBrand.getKey(), supportedBrand);
        // supportedBrand.save();
    }

    public void clearSupportedDevice() {
        supportedDeviceConcurrentHashMap.clear();
    }


}
