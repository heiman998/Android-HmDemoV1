package com.heiman.hmdemov1.modle;

/**
 * @Author : 肖力 by mac
 * @Time :  2018/1/29 上午8:53
 * @Description :
 * @Modify record :
 */
public class MainModle {

    private String name;
    private SupportedDevice SupportedDevice;
    private SupportedBrand supportedBrand;
    private CodeListBandFull codeListBandFull;

    public void setCodeListBandFull(CodeListBandFull codeListBandFull) {
        this.codeListBandFull = codeListBandFull;
    }

    public CodeListBandFull getCodeListBandFull() {
        return codeListBandFull;
    }

    public SupportedBrand getSupportedBrand() {
        return supportedBrand;
    }

    public void setSupportedBrand(SupportedBrand supportedBrand) {
        this.supportedBrand = supportedBrand;
    }

    public SupportedDevice getSupportedDevice() {
        return SupportedDevice;
    }

    public void setSupportedDevice(SupportedDevice supportedDevice) {
        SupportedDevice = supportedDevice;
    }

    public MainModle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
