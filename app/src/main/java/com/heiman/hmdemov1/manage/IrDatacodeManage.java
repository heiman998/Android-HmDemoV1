package com.heiman.hmdemov1.manage;

import com.heiman.hmapisdkv1.utils.HmUtils;
import com.heiman.hmdemov1.modle.IrDatacode;

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
public class IrDatacodeManage {
    private static IrDatacodeManage instance;
    public static ConcurrentHashMap<String, IrDatacode> irDatacodeConcurrentHashMap = new ConcurrentHashMap<>();

    public static IrDatacodeManage getInstance() {
        if (instance == null) {
            instance = new IrDatacodeManage();
        }
        return instance;
    }

    public static ArrayList<IrDatacode> irDatacodeList = new ArrayList<>();

    public synchronized ArrayList<IrDatacode> getIrDatacode() {
        irDatacodeList.clear();
        Iterator<Map.Entry<String, IrDatacode>> iter = irDatacodeConcurrentHashMap.entrySet()
                .iterator();
        while (iter.hasNext()) {
            Map.Entry<String, IrDatacode> entry = iter.next();
            irDatacodeList.add(entry.getValue());
        }
        return irDatacodeList;
    }

    public IrDatacode getIrDatacode(String key) {
        if (HmUtils.isEmptyString(key)) {
            return new IrDatacode();
        } else {
            return irDatacodeConcurrentHashMap.get(key);
        }
    }

    public void addIrDatacode(IrDatacode irDatacode) {
        IrDatacode homes = irDatacodeConcurrentHashMap.get(irDatacode.getKeyId());
        if (homes != null) { // 如果已经保存过设备，就不add
            irDatacodeConcurrentHashMap.put(irDatacode.getKeyId(), irDatacode);
            // irDatacode.save();
            return;
        }
        irDatacodeConcurrentHashMap.put(irDatacode.getKeyId(), irDatacode);

    }

    public void removeIrDatacode(String id) {
        irDatacodeConcurrentHashMap.remove(id);
    }

    public void removeIrDatacode(IrDatacode irDatacode) {
        irDatacodeConcurrentHashMap.remove(irDatacode.getKeyId());
    }

    public void updateIrDatacode(IrDatacode irDatacode) {
        irDatacodeConcurrentHashMap.remove(irDatacode.getKeyId());
        irDatacodeConcurrentHashMap.put(irDatacode.getKeyId(), irDatacode);
        // irDatacode.save();
    }

    public void clearIrDatacode() {
        irDatacodeConcurrentHashMap.clear();
    }


}
