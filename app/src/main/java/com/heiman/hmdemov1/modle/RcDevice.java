package com.heiman.hmdemov1.modle;

import com.google.gson.annotations.Expose;


/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/16 下午1:40
 * @Description :
 * @Modify record :
 */
public class RcDevice  {
    /**
     * zip : 3
     * updateAt : 2017-09-21T17:03:06.551Z
     * irID : sfd
     * _region : 1
     * deviceMac : A020A61387B6
     * rmode : asf
     * bName : adf
     * type : 1
     * creator : 0
     * irData : {"a":0,"b":100,"c":100000,"d":1505983428214,"e":-1,"f":"255.255.255.255","g":2,"h":"A020A61387B6","i":5987,"j":1999245901,"k":"","l":"160edcb03fc9ba00160edcb03fc9ba01","m":1,"n":1,"o":-1,"p":true,"r":735362,"s":-1,"t":0,"v":0,"w":0,"x":0}
     * createAt : 2017-09-21T17:03:06.551Z
     * isPower : 2
     * objectId : 59c3804a90124f3971afb1e8
     * name : sdf
     * CodeNum : sfd
     * temp : 25
     */
    @Expose(deserialize = true, serialize = false)
    private String updateAt;//更新时间
    @Expose(deserialize = true, serialize = false)
    private String createAt;//创建时间
    @Expose(deserialize = true, serialize = false)
    private String objectId;//设备id
    @Expose(deserialize = true, serialize = false)
    private int _region;//
    @Expose(deserialize = true, serialize = false)
    private int creator;//创建者
    private int type;//设备类型
    //    @Expose
//    private String rmode;//设备类型标识码
//    @Expose
//    private String bName;//设备类型名称
    @Expose(deserialize = true, serialize = false)
    private String temp;//空调，当前温度
    private String deviceMac;//设备mac
    private String CodeNum;//码库数值
    private String name;//虚拟设备名称
    private String irID;//码库id
    @Expose(deserialize = true, serialize = false)
    private String isPower;//开关状态
    private String irData;//码库值
    private int zip;//压缩码库方式
    private String Region;//Region

    @Expose(deserialize = true, serialize = false)
    private String roomDeviceId = "";//房间设备id
    @Expose(deserialize = true, serialize = false)
    private String roomID = "";//房间ID


    @Expose(deserialize = true, serialize = false)
    private int template = 0;//选择模板

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public String getRoomDeviceId() {
        return roomDeviceId;
    }

    public void setRoomDeviceId(String roomDeviceId) {
        this.roomDeviceId = roomDeviceId;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getIrID() {
        return irID;
    }

    public void setIrID(String irID) {
        this.irID = irID;
    }

    public int get_region() {
        return _region;
    }

    public void set_region(int _region) {
        this._region = _region;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getIrData() {
        return irData;
    }

    public void setIrData(String irData) {
        this.irData = irData;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getIsPower() {
        return isPower;
    }

    public void setIsPower(String isPower) {
        this.isPower = isPower;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeNum() {
        return CodeNum;
    }

    public void setCodeNum(String CodeNum) {
        this.CodeNum = CodeNum;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

}
