package com.heiman.hmdemov1.manage;
 

import com.heiman.hmapisdkv1.utils.HmUtils;
import com.heiman.hmdemov1.modle.CodeListBandFull;

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
public class CodeListBandFuilManage {
    private static CodeListBandFuilManage instance;
    public static ConcurrentHashMap<String, CodeListBandFull> supportedDeviceConcurrentHashMap = new ConcurrentHashMap<>();

    public static CodeListBandFuilManage getInstance() {
        if (instance == null) {
            instance = new CodeListBandFuilManage();
        }
        return instance;
    }

    public static ArrayList<CodeListBandFull> supportedDeviceList = new ArrayList<>();

    public synchronized ArrayList<CodeListBandFull> getCodeListBandFull() {
        supportedDeviceList.clear();
        Iterator<Map.Entry<String, CodeListBandFull>> iter = supportedDeviceConcurrentHashMap.entrySet()
                .iterator();
        while (iter.hasNext()) {
            Map.Entry<String, CodeListBandFull> entry = iter.next();
            supportedDeviceList.add(entry.getValue());
        }
        return supportedDeviceList;
    }

    public CodeListBandFull getCodeListBandFull(String key) {
        if (HmUtils.isEmptyString(key)) {
            return new CodeListBandFull();
        } else {
            return supportedDeviceConcurrentHashMap.get(key);
        }
    }

    public void addCodeListBandFull(CodeListBandFull supportedDevice) {
        CodeListBandFull homes = supportedDeviceConcurrentHashMap.get(supportedDevice.getKey());
        if (homes != null) { // 如果已经保存过设备，就不add
            supportedDeviceConcurrentHashMap.put(supportedDevice.getKey(), supportedDevice);
            // supportedDevice.save();
            return;
        }
        supportedDeviceConcurrentHashMap.put(supportedDevice.getKey(), supportedDevice);

    }

    public void removeCodeListBandFull(String id) {
        supportedDeviceConcurrentHashMap.remove(id);
    }

    public void removeCodeListBandFull(CodeListBandFull supportedDevice) {
        supportedDeviceConcurrentHashMap.remove(supportedDevice.getKey());
    }

    public void updateCodeListBandFull(CodeListBandFull supportedDevice) {
        supportedDeviceConcurrentHashMap.remove(supportedDevice.getKey());
        supportedDeviceConcurrentHashMap.put(supportedDevice.getKey(), supportedDevice);
        // supportedDevice.save();
    }

    public void clearCodeListBandFull() {
        supportedDeviceConcurrentHashMap.clear();
    }


}
