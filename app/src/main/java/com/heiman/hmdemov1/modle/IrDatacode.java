package com.heiman.hmdemov1.modle;

/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/8 上午9:43
 * @Description :
 * @Modify record :
 */
public class IrDatacode {

    private String irData;//Data for UIRD engine to generate IR signal

    private String stPower;//<Remark>

    private String stMode;//<Remark>

    private String stTemp;//<Remark>

    private String stFan;//<Remark>

    private String stSwing;//<Remark>

    private String keyId;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIrData() {
        return irData;
    }

    public void setIrData(String irData) {
        this.irData = irData;
    }

    public String getStPower() {
        return stPower;
    }

    public void setStPower(String stPower) {
        this.stPower = stPower;
    }

    public String getStMode() {
        return stMode;
    }

    public void setStMode(String stMode) {
        this.stMode = stMode;
    }

    public String getStTemp() {
        return stTemp;
    }

    public void setStTemp(String stTemp) {
        this.stTemp = stTemp;
    }

    public String getStFan() {
        return stFan;
    }

    public void setStFan(String stFan) {
        this.stFan = stFan;
    }

    public String getStSwing() {
        return stSwing;
    }

    public void setStSwing(String stSwing) {
        this.stSwing = stSwing;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}
