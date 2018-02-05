package com.heiman.hmdemov1.modle;

/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/7 下午6:13
 * @Description :
 * @Modify record :
 */
public class SupportedBrand {
    private String brandName;//Brand name in English

    private String brandNameCN;//Brand name in Simplified Chinese

    private String brandNameZH;//Brand name in Traditional Chinese

    private String isManuf;//Manufacture name is specified in brandName parameters

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandNameCN() {
        return brandNameCN;
    }

    public void setBrandNameCN(String brandNameCN) {
        this.brandNameCN = brandNameCN;
    }

    public String getBrandNameZH() {
        return brandNameZH;
    }

    public void setBrandNameZH(String brandNameZH) {
        this.brandNameZH = brandNameZH;
    }

    public String getIsManuf() {
        return isManuf;
    }

    public void setIsManuf(String isManuf) {
        this.isManuf = isManuf;
    }
}
