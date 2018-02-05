package com.heiman.hmdemov1.modle;

/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/8 上午9:32
 * @Description :
 * @Modify record :
 */
public class CodeListBandFull {

    private String codeNum; //Code set number of IR code

    private String rank;//The ranking of a code set in brand and device category

    private String year;//Calendar year of a code/brand

    private String key;

    private String codeName;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
