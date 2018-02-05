package com.heiman.hmdemov1.modle;

import com.google.gson.annotations.Expose;
import com.heiman.hmapisdkv1.utils.HmUtils;

/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/8 上午9:48
 * @Description :
 * @Modify record :
 */
public class SupportedKey {
    @Expose
    private String KeyLabel7;//IR function key name in maximum of 7 characters
    @Expose
    private String KeyLabel12;//IR function key name in maximum of 12 characters
    @Expose
    private String Comment;//Additional description
    @Expose
    private String stPower;//The IR function key status of power
    @Expose
    private String stMode;//The IR function key status of mode
    @Expose
    private String stTemp;//The IR function key status of temperature
    @Expose
    private String stFan;//The IR function key status of fan
    @Expose
    private String stSwing;//The IR function key status of swing
    @Expose
    private String keyId_Z_Wave;//Z-Wave SimpleAV key ID
    @Expose
    private String keyId_hdmiCEC;//HDMI CEC key ID
    @Expose
    private boolean isAC;//是否是AC
    @Expose
    private String key;
    @Expose
    private String keyId;
    @Expose
    private String irData;//Data for UIRD engine to generate IR signal
    @Expose
    private String keyId_alt;
    @Expose
    private String datald;
    @Expose
    private String CodeNumx;

    @Expose
    private int listNumb = 0;//排序序列号

    public int getListNumb() {
//        if (!HmUtils.isEmptyString(KeyLabel7)) {
//            if (KeyLabel7.equals(Constant.RC_KEY.POWER)) {
//                listNumb = Integer.MAX_VALUE;
//            } else if (KeyLabel7.equals(Constant.RC_KEY.INPUT)) {
//                listNumb = Integer.MAX_VALUE - 1;
//            } else if (KeyLabel7.equals(Constant.RC_KEY.MUTE)) {
//                listNumb = Integer.MAX_VALUE - 2;
//            } else if (KeyLabel7.equals(Constant.RC_KEY.BACK)) {
//                listNumb = Integer.MAX_VALUE - 4;
//            } else if (KeyLabel7.equals(Constant.RC_KEY.MENU)) {
//                listNumb = Integer.MAX_VALUE - 3;
//            } else if (KeyLabel7.equals(Constant.RC_KEY.PLAY)) {
//                listNumb = Integer.MAX_VALUE - 5;
//            } else if (KeyLabel7.equals(Constant.RC_KEY.PAUSE)) {
//                listNumb = Integer.MAX_VALUE - 6;
//            } else if (KeyLabel7.equals(Constant.RC_KEY.STOP)) {
//                listNumb = Integer.MAX_VALUE - 7;
//            } else if (KeyLabel7.equals(Constant.RC_KEY.HOME)) {
//                listNumb = Integer.MAX_VALUE - 1;
//            }
//
//        }
        return listNumb;
    }

    public String getKeyId_alt() {
        return keyId_alt;
    }

    public void setKeyId_alt(String keyId_alt) {
        this.keyId_alt = keyId_alt;
    }

    public String getDatald() {
        return datald;
    }

    public void setDatald(String datald) {
        this.datald = datald;
    }

    public String getCodeNumx() {
        return CodeNumx;
    }

    public void setCodeNumx(String codeNumx) {
        CodeNumx = codeNumx;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getIrData() {
        return irData;
    }

    public void setIrData(String irData) {
        this.irData = irData;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyLabel7() {
        return KeyLabel7;
    }

    public void setKeyLabel7(String keyLabel7) {
        KeyLabel7 = keyLabel7;
    }

    public String getKeyLabel12() {
        return KeyLabel12;
    }

    public void setKeyLabel12(String keyLabel12) {
        KeyLabel12 = keyLabel12;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
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

    public String getKeyId_Z_Wave() {
        return keyId_Z_Wave;
    }

    public void setKeyId_Z_Wave(String keyId_Z_Wave) {
        this.keyId_Z_Wave = keyId_Z_Wave;
    }

    public String getKeyId_hdmiCEC() {
        return keyId_hdmiCEC;
    }

    public void setKeyId_hdmiCEC(String keyId_hdmiCEC) {
        this.keyId_hdmiCEC = keyId_hdmiCEC;
    }

    public boolean isAC() {
        return isAC;
    }

    public void setAC(boolean AC) {
        isAC = AC;
    }
}
