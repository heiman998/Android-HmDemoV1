package com.heiman.hmdemov1.manage;


import com.heiman.hmapisdkv1.utils.HmUtils;
import com.heiman.hmdemov1.modle.SupportedDevice;

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
public class SupportedDeviceManage {
    private static SupportedDeviceManage instance;
    public static ConcurrentHashMap<String, SupportedDevice> supportedDeviceConcurrentHashMap = new ConcurrentHashMap<>();

    public static SupportedDeviceManage getInstance() {
        if (instance == null) {
            instance = new SupportedDeviceManage();
        }
        return instance;
    }

    public static ArrayList<SupportedDevice> supportedDeviceList = new ArrayList<>();

    public synchronized ArrayList<SupportedDevice> getSupportedDevice() {
        supportedDeviceList.clear();
        Iterator<Map.Entry<String, SupportedDevice>> iter = supportedDeviceConcurrentHashMap.entrySet()
                .iterator();
        while (iter.hasNext()) {
            Map.Entry<String, SupportedDevice> entry = iter.next();
            supportedDeviceList.add(entry.getValue());
        }
        return supportedDeviceList;
    }

    public SupportedDevice getSupportedDevice(String key) {
        if (HmUtils.isEmptyString(key)) {
            return new SupportedDevice();
        } else {
            return supportedDeviceConcurrentHashMap.get(key);
        }
    }

    public void addSupportedDevice(SupportedDevice supportedDevice) {
        SupportedDevice homes = supportedDeviceConcurrentHashMap.get(supportedDevice.getKey());
        if (homes != null) { // 如果已经保存过设备，就不add
            supportedDeviceConcurrentHashMap.put(supportedDevice.getKey(), supportedDevice);
            // supportedDevice.save();
            return;
        }
        supportedDeviceConcurrentHashMap.put(supportedDevice.getKey(), supportedDevice);

    }

    public void removeSupportedDevice(String id) {
        supportedDeviceConcurrentHashMap.remove(id);
    }

    public void removeSupportedDevice(SupportedDevice supportedDevice) {
        supportedDeviceConcurrentHashMap.remove(supportedDevice.getKey());
    }

    public void updateSupportedDevice(SupportedDevice supportedDevice) {
        supportedDeviceConcurrentHashMap.remove(supportedDevice.getKey());
        supportedDeviceConcurrentHashMap.put(supportedDevice.getKey(), supportedDevice);
        // supportedDevice.save();
    }

    public void clearSupportedDevice() {
        supportedDeviceConcurrentHashMap.clear();
    }


}
