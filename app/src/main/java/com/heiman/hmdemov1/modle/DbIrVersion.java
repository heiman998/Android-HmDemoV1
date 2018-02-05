package com.heiman.hmdemov1.modle;

import com.google.gson.annotations.SerializedName;

/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/8 上午10:01
 * @Description :
 * @Modify record :
 */
public class DbIrVersion {

    /**
     * CtrlCode : 1
     * dbFormat : V04.07
     * Region : GLOBAL
     * ver : 16.2.003.4
     * ver_keyId_ZWave : “4”
     * ver_keyId_hdmiCEC : 1.3a
     * “dataFormat” : “0.1”
     */

    private String CtrlCode;
    private String dbFormat;
    private String Region;
    private String ver;
    private String ver_keyId_ZWave;
    private String ver_keyId_hdmiCEC;
    @SerializedName("dataFormat")
    private String DataFormat12; // FIXME check this code

    public String getCtrlCode() {
        return CtrlCode;
    }

    public void setCtrlCode(String ctrlCode) {
        CtrlCode = ctrlCode;
    }

    public String getDbFormat() {
        return dbFormat;
    }

    public void setDbFormat(String dbFormat) {
        this.dbFormat = dbFormat;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getVer_keyId_ZWave() {
        return ver_keyId_ZWave;
    }

    public void setVer_keyId_ZWave(String ver_keyId_ZWave) {
        this.ver_keyId_ZWave = ver_keyId_ZWave;
    }

    public String getVer_keyId_hdmiCEC() {
        return ver_keyId_hdmiCEC;
    }

    public void setVer_keyId_hdmiCEC(String ver_keyId_hdmiCEC) {
        this.ver_keyId_hdmiCEC = ver_keyId_hdmiCEC;
    }

    public String getDataFormat12() {
        return DataFormat12;
    }

    public void setDataFormat12(String dataFormat12) {
        DataFormat12 = dataFormat12;
    }
}
