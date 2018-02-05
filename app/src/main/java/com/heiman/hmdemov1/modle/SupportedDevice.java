package com.heiman.hmdemov1.modle;


/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/4 下午5:48
 * @Description :
 * @Modify record :
 */
public class SupportedDevice {


    /**
     * deviceName15 : {"1":"TV","2":"VCR","5":"DVD","6":"AUD","7":"CD","8":"OTHERS","9":"AVR","10":"TV BOX","11":"MEDIA BOX","12":"SAT/CBL","13":"DVDBR","36":"PROJ","72":"SOUNDBAR","91":"TV_Discrete","93":"SAT_Discrete","96":"AUD_Discrete"}
     * deviceName30 : {"1":"TV","2":"VCR","5":"DVD/DVDR","6":"AUD/HOMETHEATHER","7":"CD/CDR","8":"OTHERS","9":"AVR","10":"TV BOX","11":"STREAMING","12":"SAT/CBL","13":"DVDBR","36":"PROJECTOR","72":"SOUND BAR","91":"TV_Discrete","93":"SAT_Discrete","96":"AUD_Discrete"}
     * Comment : {"1":"All TV","2":"VCR","5":"DVD/ DVDCMB/DVDR/ DVDVCRCMB/ HDD RECORDER","6":"AUD/ AMP/HIFI","7":"CD PLAYER/ CD RECORDER","8":"HOMEAUTO/ GAME/ PC/ WMOUNT/VIDEO CONFERENCE","9":"AVR","10":"DTA/DTV/DVBT/ /TV TUNER/ TV BOX/ HDTV BOX","11":"STREAMING /MEDIA PLAYER","12":"SAT/CBL/DVR/IPTV/PVR","13":"DVDBR","36":"PROJECTOR","72":"SOUND BAR","91":"TV_Discrete","93":"SAT_Discrete","96":"AVR/AUD_Discrete"}
     * deviceNameCN : {"1":"电视","2":"录像机","5":"DVD","6":"家庭影院","7":"CD","8":"其他","9":null,"10":"电视盒","11":"网络盒子","12":"卫星/机顶盒","13":"蓝光DVD","36":"投影仪","72":"音箱","91":null,"93":null,"96":null}
     */

    private String deviceName15;//Suggested name of device category (max. 15 characters)

    private String deviceName30;//Suggested name of device category (max. 30 characters)

    private String Comment;//Additional description

    private String deviceNameCN;//Suggested name of device category (in Chinese)

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDeviceName15() {
        return deviceName15;
    }

    public void setDeviceName15(String deviceName15) {
        this.deviceName15 = deviceName15;
    }

    public String getDeviceName30() {
        return deviceName30;
    }

    public void setDeviceName30(String deviceName30) {
        this.deviceName30 = deviceName30;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getDeviceNameCN() {
        return deviceNameCN;
    }

    public void setDeviceNameCN(String deviceNameCN) {
        this.deviceNameCN = deviceNameCN;
    }
}
