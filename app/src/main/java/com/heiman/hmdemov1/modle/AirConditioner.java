package com.heiman.hmdemov1.modle;

/**
 * @Author : 肖力 by mac
 * @Time :  2017/9/27 上午11:48
 * @Description :
 * @Modify record :
 */
public class AirConditioner {

    private static int Temp = 25;              //温度:  制冷16-31，制热16-31
    private static int Mode = 0;               //模式:  自动  0， 制冷  1，抽湿  2, 送风  3, 制热  4  AUTO COOL DRY FAN HEAT
    private static boolean ispower = true;     //开关 ON OFF
    private static int Fan = 0;                //风量:  s(1-3) AUTO
    private static int Swing = 0;              //风向是否摆动 0不摆动 1为摆动  OFF ON
    private static int onoff = 2;

    public AirConditioner(int Temp, int Mode, boolean ispower, int Fan, int Swing, int onoff) {
        this.Temp = Temp;
        this.Mode = Mode;
        this.ispower = ispower;
        this.Fan = Fan;
        this.Swing = Swing;
        this.onoff = onoff;
    }

    public static class MODE_TYPE {
        public static final int AUTO = 2;                           //自动
        public static final int COOL = 0;                           //制冷
        public static final int DRY = 4;                            //抽湿
        public static final int FAN = 3;                            //送风
        public static final int HEAT = 1;                           //制热
    }

    public static class POWER_TYPE {
        public static final boolean ON = true;                           //打开
        public static final boolean OFF = false;                         //关闭
    }

    public static class FAN_TYPE {
        public static final int ONE = 1;                           //1
        public static final int TWO = 2;                           //2
        public static final int THREE = 3;                         //3
        public static final int AUTO = 0;                          //自动
    }

    public static class SWING_TYPE {
        public static final int ON = 0;                            //0不摆动
        public static final int OFF = 1;                           //1为摆动
    }

    public static String getStringMode() {
        switch (Mode) {
            case MODE_TYPE.AUTO:
                return "AUTO";
            case MODE_TYPE.COOL:
                return "COOL";
            case MODE_TYPE.DRY:
                return "DRY";
            case MODE_TYPE.FAN:
                return "FAN";
            case MODE_TYPE.HEAT:
                return "HEAT";
            default:
                return "AUTO";
        }
    }

    public static int getIntMode(String mode) {
        switch (mode) {
            case "AUTO":
                return MODE_TYPE.AUTO;
            case "COOL":
                return MODE_TYPE.COOL;
            case "DRY":
                return MODE_TYPE.DRY;
            case "FAN":
                return MODE_TYPE.FAN;
            case "HEAT":
                return MODE_TYPE.HEAT;
            default:
                return MODE_TYPE.AUTO;
        }
    }

    public static String getStringPower() {
        if (ispower) {
            return "ON";
        } else {
            return "OFF";
        }
    }

    public static boolean getBooleanPower(String power) {
        switch (power) {
            case "ON":
                return POWER_TYPE.ON;
            case "OFF":
                return POWER_TYPE.OFF;
            default:
                return POWER_TYPE.ON;
        }
    }

    public static String getStringFan() {
        switch (Fan) {
            case FAN_TYPE.AUTO:
                return "FAN AUTO";
            case FAN_TYPE.ONE:
                return "FAN LOW";
            case FAN_TYPE.TWO:
                return "FAN MID";
            case FAN_TYPE.THREE:
                return "FAN HI";
            default:
                return "FAN AUTO";
        }
    }

    public static int getIntFan(String fan) {
        switch (fan) {
            case "FAN AUTO":
                return FAN_TYPE.AUTO;
            case "FAN LOW":
                return FAN_TYPE.ONE;
            case "FAN MID":
                return FAN_TYPE.TWO;
            case "FAN HI":
                return FAN_TYPE.THREE;
            default:
                return FAN_TYPE.AUTO;
        }
    }

    public static String getStringSwing() {
        switch (Swing) {
            case SWING_TYPE.OFF:
                return "ON";
            case SWING_TYPE.ON:
                return "OFF";
            default:
                return "ON";
        }
    }

    public static int getIntSwing(String swing) {
        switch (swing) {
            case "ON":
                return SWING_TYPE.OFF;
            case "OFF":
                return SWING_TYPE.ON;
            default:
                return SWING_TYPE.OFF;
        }
    }

    public String toString() {
        return "AirConditioner:[Temp:" + Temp +
                "\tMode:" + Mode +
                "\tStringMode:" + getStringMode() +
                "\tispower:" + ispower +
                "\tFan:" + Fan +
                "\tStringFan:" + getStringFan() +
                "\tSwing:" + Swing +
                "\tStringSwing:" + getStringSwing() +
                "\tonoff:" + onoff +
                "\tStringPower:" + getStringPower() + "]";
    }

    public static int getTemp() {
        return Temp;
    }

    public static void setTemp(int temp) {
        Temp = temp;
    }

    public static int getMode() {
        return Mode;
    }

    public static void setMode(int mode) {
        Mode = mode;
    }

    public static boolean ispower() {
        return ispower;
    }

    public static void setIspower(boolean ispower) {
        AirConditioner.ispower = ispower;
    }

    public static int getFan() {
        return Fan;
    }

    public static void setFan(int fan) {
        Fan = fan;
    }

    public static int getSwing() {
        return Swing;
    }

    public static void setSwing(int swing) {
        Swing = swing;
    }

    public static int getOnoff() {
        return onoff;
    }

    public static void setOnoff(int onoff) {
        AirConditioner.onoff = onoff;
    }
}
