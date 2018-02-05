package com.heiman.hmdemov1.modle;
/**
 * Copyright ©深圳市海曼科技有限公司
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Author :    肖力
 * @Email : 554674787@qq.com
 * @Phone : 18312377810
 * @Time :  2017/3/7 09:54
 * @Description :
 */
public class EchoRemote {
    /**
     * CID : 30011
     * PL : {"2.1.1.8.4.19.0":[{"code":"红外码值","ispower":0,"zip":1,"objectId":"objectId"},{"code":"红外码值","ispower":0,"zip":1,"objectId":"objectId"}]}
     * SID : 12345
     * SN : 123456
     * TEID : 100012461
     */

    private int CID;
    private PLBean PL;
    private String SID;
    private int SN;
    private String TEID;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public PLBean getPL() {
        return PL;
    }

    public void setPL(PLBean PL) {
        this.PL = PL;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
    }

    public String getTEID() {
        return TEID;
    }

    public void setTEID(String TEID) {
        this.TEID = TEID;
    }

    public static class PLBean {
        @SerializedName("2.1.1.8.4.19.0")
        private List<Bean> value;

        public List<Bean> getValue() {
            return value;
        }

        public void setValue(List<Bean> value) {
            this.value = value;
        }

        public static class Bean {
            /**
             * code : 红外码值
             * ispower : 0
             * zip : 1
             * objectId : objectId
             */

            private String code;
            private int ispower;
            private int zip;
            private String objectId;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getIspower() {
                return ispower;
            }

            public void setIspower(int ispower) {
                this.ispower = ispower;
            }

            public int getZip() {
                return zip;
            }

            public void setZip(int zip) {
                this.zip = zip;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }
        }
    }
}
