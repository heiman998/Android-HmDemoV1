package com.heiman.hmdemov1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.heiman.hmapisdkv1.data.HmAgent;
import com.heiman.hmapisdkv1.enumbase.HmDeviceType;
import com.heiman.hmapisdkv1.enumbase.HmOID;
import com.heiman.hmapisdkv1.modle.RcTimer;
import com.heiman.hmapisdkv1.modle.SendCode;
import com.heiman.hmapisdkv1.modle.TempAlarm;
import com.heiman.hmdemov1.Constant;
import com.heiman.hmdemov1.R;
import com.heiman.hmdemov1.adapter.ItemTextAdapter;
import com.heiman.hmdemov1.modle.MainModle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mListView)
    ListView mListView;
    private ItemTextAdapter mAdapter;
    private List<MainModle> mainModleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initEven();
    }

    private void initEven() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /**
                 * 以下获取和设置过程除了获取秘钥以外不需要秘钥其他都必须要填写秘钥。该秘钥的由来就是通过HmAgent.getInstance().getDeviceKey获取
                 */
                MainModle mainModle = mainModleList.get(i);

                HmAgent.getLogger().i(mainModle.getName());
                String data = "";
                switch (i) {
                    case 0: {
                        List<String> OID = new ArrayList<>();
                        OID.add(HmOID.DEVICE_GET_BASIC_INFORMATION.getOid());
                        data = HmAgent.getInstance().getOID("123456789012345", 1, OID);
                    }
                    break;
                    case 1: {
                        List<String> OID = new ArrayList<>();
                        OID.add(HmOID.RC_GET_TEMP.getOid());
                        data = HmAgent.getInstance().getOID("123456789012345", 1, OID);
                    }
                    break;
                    case 2: {
                        List<String> OID = new ArrayList<>();
                        OID.add(HmOID.RC_GET_TIMER.getOid());
                        data = HmAgent.getInstance().getOID("123456789012345", 123456, OID);
                    }
                    break;
                    case 3: {
                        data = HmAgent.getInstance().getDeviceKey(123456);
                    }
                    break;
                    case 4: {
                        /**
                         * 时区输入格式为  +00:00
                         */
                        data = HmAgent.getInstance().setTimerZone("123456789012345", 123456, "+08:00");
                    }
                    break;
                    case 5: {
                        data = HmAgent.getInstance().setDeviceName("123456789012345", 123456, "名字");
                    }
                    break;
                    case 6: {
                        SendCode sendCode = new SendCode();
                        sendCode.setCode("这里填写具体的获取到的控制码库");
                        sendCode.setOD("这里填写相关的虚拟设备Id");
                        sendCode.setOF(1);//开为1 关为0.其他为2
                        sendCode.setZip(3);//学习的码库为2 下载下来的为3.
                        data = HmAgent.getInstance().setSendCode("123456789012345", 123456, sendCode);
                    }
                    break;
                    case 7: {
                        data = HmAgent.getInstance().setRcStudy("123456789012345", 123456, "这里填写相关的虚拟设备Id");
                    }
                    break;
                    case 8: {
                        data = HmAgent.getInstance().setEchoSendCode("123456789012345", 123456, null);
                    }
                    break;
                    case 9: {
                        data = HmAgent.getInstance().deleteCode("123456789012345", 123456, "这里填写相关的虚拟设备Id");
                    }
                    break;
                    case 10: {
                        RcTimer rcTimer = new RcTimer();
//                        rcTimer.setCODEE();
//                        rcTimer.setCODES();
//                        rcTimer.setOD();
//                        rcTimer.setTE();
//                        rcTimer.setTS();
//                        rcTimer.setWf();
                        data = HmAgent.getInstance().setRcTimer("123456789012345", 123456, rcTimer);
                    }
                    break;
                    case 11: {
                        TempAlarm tempAlarm = new TempAlarm();
                        tempAlarm.setCklow(10);//温度报警下限
                        tempAlarm.setCkup(20);//温度报警上限
                        tempAlarm.setT_ckvalid_low(0);//温度下限报警使能 1为开启 0为关闭
                        tempAlarm.setT_ckvalid_up(1);//温度上限报警使能 1为开启 0为关闭
                        data = HmAgent.getInstance().setRcTempAlarm("123456789012345", 123456, tempAlarm);
                    }
                    break;
                    case 12: {
                        //码库是储存在我们服务器上的。所以必须通过http请求去获取数据
                        startActivity(new Intent(MainActivity.this, HttpActivity.class));
                    }
                    break;
                    case 13: {
                        Bundle paramBundle = new Bundle();
                        paramBundle.putInt(Constant.TYPE, HmDeviceType.DEVICE_WIFI_RC.getValue());
                        Intent localIntent = new Intent(MainActivity.this, SmarLinkActivity.class);
                        localIntent.putExtras(paramBundle);
                        startActivity(localIntent);
                    }
                    break;
                }
                HmAgent.getLogger().i("data:" + data);
            }
        });
    }

    private void initData() {
        mainModleList = new ArrayList<>();
        mainModleList.add(new MainModle("获取基本信息"));
        mainModleList.add(new MainModle("获取温度"));
        mainModleList.add(new MainModle("获取定时器"));
        mainModleList.add(new MainModle("获取设备秘钥"));
        mainModleList.add(new MainModle("设置时区"));
        mainModleList.add(new MainModle("修改设备名称"));
        mainModleList.add(new MainModle("发送红外码库"));
        mainModleList.add(new MainModle("设置设备进入红外学习功能"));
        mainModleList.add(new MainModle("发送ECho"));
        mainModleList.add(new MainModle("删除ECHO子设备"));
        mainModleList.add(new MainModle("设置RC定时器时间"));
        mainModleList.add(new MainModle("报警阈值设置"));
        mainModleList.add(new MainModle("进入HTTP获取码库界面"));
        mainModleList.add(new MainModle("设备进入配网模式"));
        mAdapter = new ItemTextAdapter(this, mainModleList);
        mListView.setAdapter(mAdapter);
    }
}
