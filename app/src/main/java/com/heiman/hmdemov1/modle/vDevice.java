package com.heiman.hmdemov1.modle;/**
 * Created by hp on 2016/11/8.
 */

/**
 * 作者：肖力
 * 邮箱：554674787@qq.com
 * 时间：2016/11/8 08:57
 */
public class vDevice {

    /**
     * devicenam   :主设备MAC
     * vdevicename : 虚拟设备名称
     * rid : GREE_7_YADOF  虚拟设备RID 用于获取所有的码库
     * zip : 1  压缩方式 0不压缩
     * vdeviceid : 100001  虚拟设备ID
     * vdevicetype : 虚拟设备类型
     */
    private String devicemac;
    private String vdevicename;
    private String rid;
    private int zip;
    private int vdeviceid;
    private int vdevicetype;

    public int getVdevicetype() {
        return vdevicetype;
    }

    public void setVdevicetype(int vdevicetype) {
        this.vdevicetype = vdevicetype;
    }

    public String getDevicemac() {
        return devicemac;
    }

    public void setDevicemac(String devicemac) {
        this.devicemac = devicemac;
    }

    public String getVdevicename() {
        return vdevicename;
    }

    public void setVdevicename(String vdevicename) {
        this.vdevicename = vdevicename;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getVdeviceid() {
        return vdeviceid;
    }

    public void setVdeviceid(int vdeviceid) {
        this.vdeviceid = vdeviceid;
    }
}
