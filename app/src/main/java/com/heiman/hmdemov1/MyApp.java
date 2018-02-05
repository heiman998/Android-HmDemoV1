package com.heiman.hmdemov1;

import android.app.Application;
import android.support.annotation.NonNull;

import com.heiman.hmapisdkv1.back.onHmDeviceBack;
import com.heiman.hmapisdkv1.data.HmAgent;
import com.heiman.hmapisdkv1.modle.DeviceBasicInfo;
import com.heiman.hmapisdkv1.modle.RcTimer;
import com.heiman.hmapisdkv1.modle.SendCode;
import com.heiman.hmapisdkv1.modle.Temp;
import com.heiman.hmapisdkv1.modle.TempAlarm;

import java.util.List;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * @Author : 肖力 by mac
 * @Time :  2018/1/29 上午10:32
 * @Description :
 * @Modify record :
 */
public class MyApp extends Application implements onHmDeviceBack {


    @Override
    public void onCreate() {
        super.onCreate();
        //可以在登录之后，在这里初始化SDK，传入相应的用户昵称以及用户ID
        HmAgent.getInstance().init(this, "用户昵称", "用户ID");
        //这里放到数据接收后解码即可。
        HmAgent.getInstance().hmDecoer("数据","秘钥",this);
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(@NonNull Exception e) {
                    }
                })
                .install();

    }

    @Override
    public void onAesKey(String s, int i) {

    }

    @Override
    public void onTemp(Temp temp, int i) {

    }

    @Override
    public void onRcTimer(RcTimer rcTimer, int i) {

    }

    @Override
    public void onDeviceBasiInfo(DeviceBasicInfo deviceBasicInfo, int i) {

    }

    @Override
    public void onSendCode(SendCode sendCode, int i) {

    }

    @Override
    public void onStudyCode(SendCode sendCode, int i) {

    }

    @Override
    public void onEchoCode(List<SendCode> list, int i) {

    }

    @Override
    public void onTempAlarm(TempAlarm tempAlarm, int i) {

    }

    @Override
    public void onDeleceEchoCode(int i, int i1) {

    }

    @Override
    public void onError(int i, int i1) {

    }
}
