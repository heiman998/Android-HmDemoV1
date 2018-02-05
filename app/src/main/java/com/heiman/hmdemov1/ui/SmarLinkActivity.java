package com.heiman.hmdemov1.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.heiman.hmapisdkv1.data.HmAgent;
import com.heiman.hmapisdkv1.modle.DeviceBean;
import com.heiman.hmapisdkv1.modle.SmartLink;
import com.heiman.hmapisdkv1.smartlinkutils.ConfigCallback;
import com.heiman.hmapisdkv1.smartlinkutils.Smartlink;
import com.heiman.hmapisdkv1.smartlinkutils.SmartlinkFactory;
import com.heiman.hmapisdkv1.utils.HmUtils;
import com.heiman.hmdemov1.Constant;
import com.heiman.hmdemov1.R;

/**
 * @Author : 肖力 by mac
 * @Time :  2017/12/2 上午10:48
 * @Description :
 * @Modify record :
 */
public class SmarLinkActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int MESSAGE_SUBSCRIBE_DEVICE = 1090;
    private TextView tvwifiname;
    private EditText etwifipass;
    private Button btnstart;
    private Smartlink smartlink;
    private boolean isStart = true;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartlink);
        this.btnstart = (Button) findViewById(R.id.btn_start);
        this.etwifipass = (EditText) findViewById(R.id.et_wifi_pass);
        this.tvwifiname = (TextView) findViewById(R.id.tv_wifi_name);
        btnstart.setOnClickListener(this);
        Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        int deviceType = bundle.getInt(Constant.TYPE);
        //这里需要位置权限才可以获取ssid。我这里没有写获取权限的功能。

        HmAgent.getLogger().e(HmUtils.getSSid(this));
        tvwifiname.setText("Wifi名称:" + HmUtils.getSSid(this));
        smartlink = SmartlinkFactory.getSmartlink(this, deviceType, configCallback);
    }
    private final static int MSG_SMARTLINK_FAIL = 10004;
    private final static int MSG_SMARTLINK_SUCCEED = 10000;
    private final static int UPDATE_FIRMWARE_FAIL_TIME = 150 * 1000;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SMARTLINK_SUCCEED: {
                    if (msg.obj == null || !(msg.obj instanceof SmartLink)) {
                        return;
                    }
                    SmartLink link = (SmartLink) msg.obj;
                    smartlink.stopConfig();
                    smartlink.setConfigCallback(null);
                    smartlink.release();
                    Toast.makeText(SmarLinkActivity.this, "配置成功！" + link.getDevice().getMacAddress(), Toast.LENGTH_SHORT).show();

                    finish();
                    break;
                }
                case MESSAGE_SUBSCRIBE_DEVICE:

                    break;
                case MSG_SMARTLINK_FAIL:
                    smartlink.stopConfig();
                    smartlink.setConfigCallback(null);
                    isStart = true;
                    btnstart.setText("开始配网");
                    etwifipass.setFocusable(true);
                    etwifipass.setFocusableInTouchMode(true);
                    etwifipass.requestFocus();
                    Toast.makeText(SmarLinkActivity.this, "配置失败！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                if (isStart) {
                    mHandler.sendEmptyMessageDelayed(MSG_SMARTLINK_FAIL, UPDATE_FIRMWARE_FAIL_TIME);
                    try {
                        smartlink.setConfigCallback(configCallback);
                        smartlink.startConfig(HmUtils.getSSid(this), getBssId(), etwifipass.getText().toString(), UPDATE_FIRMWARE_FAIL_TIME);
                        isStart = false;
                        btnstart.setText("停止配置");
                        etwifipass.setFocusable(false);
                        etwifipass.clearFocus();
                        etwifipass.setFocusableInTouchMode(false);

                        final View focusView = etwifipass;

                        if (focusView != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            assert imm != null;
                            boolean b = imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                            focusView.clearFocus();
                        }

                    } catch (Exception e) {
                        mHandler.removeMessages(MSG_SMARTLINK_FAIL);
                        mHandler.sendEmptyMessage(MSG_SMARTLINK_FAIL);
                    }
                } else {
                    smartlink.stopConfig();
                    smartlink.setConfigCallback(null);
                    isStart = true;
                    btnstart.setText("开始配网");
                    etwifipass.setFocusable(true);
                    etwifipass.setFocusableInTouchMode(true);
                    etwifipass.requestFocus();
                }
                break;
        }
    }

    private ConfigCallback configCallback = new ConfigCallback() {
        @Override
        public void onConfigSucceed(String ip, String mac) {

            Message msg = new Message();
            msg.what = MSG_SMARTLINK_SUCCEED;
            SmartLink link = new SmartLink();
            link.setDevice(new DeviceBean());
            link.getDevice().setMacAddress(mac);
            link.getDevice().setDeviceIP(ip);
            link.getDevice().setDeviceName("");
            msg.obj = link;
            mHandler.sendMessage(msg);
            mHandler.removeMessages(MSG_SMARTLINK_FAIL);
        }

        @Override
        public void onErr() {
            mHandler.removeMessages(MSG_SMARTLINK_FAIL);
            mHandler.sendEmptyMessage(MSG_SMARTLINK_FAIL);
        }
    };

    /**
     * 获得bssId
     *
     * @return bssId
     */
    public String getBssId() {
        String bssId = "";
        try {
            WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
            if (null != info) {
                if (!TextUtils.isEmpty(info.getBSSID())) {
                    bssId = info.getBSSID();
                }
            }
        } catch (Exception e) {
            return bssId;
        }
        return bssId;
    }
}
