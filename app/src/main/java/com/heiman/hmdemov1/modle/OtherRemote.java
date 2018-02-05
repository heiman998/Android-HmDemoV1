package com.heiman.hmdemov1.modle;/**
 * Created by hp on 2016/11/24.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 作者：肖力
 * 邮箱：554674787@qq.com
 * 时间：2016/11/24 13:49
 */
public class OtherRemote {


    /**
     * short : 01140BF08F7
     * src : 012615151540ffff04000154AA02FD10EFffff0C150005780001544015000578
     * kn : 8
     */
    @Expose
    @SerializedName("short")
    private String shortX;      //短码云端测试用

    @Expose
    private String src;         //码库

    @Expose
    private String kn;          //按键名字

    @Expose
    private int ImageIcon;      //自定义图片

    @Expose
    private String key;         //这是KEY

    @Expose
    private String zip;         //是否压缩

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getImageIcon() {
        return ImageIcon;
    }

    public void setImageIcon(int imageIcon) {
        ImageIcon = imageIcon;
    }

    public String getShortX() {
        return shortX;
    }

    public void setShortX(String shortX) {
        this.shortX = shortX;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getKn() {
        return kn;
    }

    public void setKn(String kn) {
        this.kn = kn;
    }
}
