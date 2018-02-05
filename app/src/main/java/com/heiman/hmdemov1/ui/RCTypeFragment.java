package com.heiman.hmdemov1.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.heiman.hmapisdkv1.back.Dialogback;
import com.heiman.hmapisdkv1.data.HmAgent;
import com.heiman.hmapisdkv1.data.HmRemotebleHttpManage;
import com.heiman.hmapisdkv1.modle.Remoteble;
import com.heiman.hmapisdkv1.utils.Convert;
import com.heiman.hmapisdkv1.utils.HmUtils;
import com.heiman.hmdemov1.R;
import com.heiman.hmdemov1.adapter.ItemTextAdapter;
import com.heiman.hmdemov1.manage.SupportedDeviceManage;
import com.heiman.hmdemov1.modle.MainModle;
import com.heiman.hmdemov1.modle.SupportedDevice;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author : 肖力 by mac
 * @Time :  2018/2/1 上午10:19
 * @Description :
 * @Modify record :
 */
public class RCTypeFragment extends SupportFragment {
    @BindView(R.id.mListView)
    ListView mListView;
    private ItemTextAdapter mAdapter;
    private List<MainModle> mainModleList;
    private static final String REGION = "region";
    private String region;

    private final int MESSAGE_GET_DATA = 1000;

    public static RCTypeFragment newInstance(String region) {
        Bundle args = new Bundle();
        RCTypeFragment fragment = new RCTypeFragment();
        fragment.setArguments(args);
        args.putString(REGION, region);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        if (args != null) {
            region = args.getString(REGION);
        }
        initEven();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initEven() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainModle mainModle = mainModleList.get(i);
                HmAgent.getLogger().i(mainModle.getName());
                start(BrandFragment.newInstance(region, mainModle.getSupportedDevice().getKey()));
            }
        });
    }

    private void initData() {
        mainModleList = new ArrayList<>();
        mAdapter = new ItemTextAdapter(getActivity(), mainModleList);
        mListView.setAdapter(mAdapter);
        HmRemotebleHttpManage.getInstance().getSupportedDevice(region, new Dialogback<Remoteble<Object>>() {
            @Override
            public void onSuccess(Response<Remoteble<Object>> response) {
                SupportedDeviceManage.getInstance().clearSupportedDevice();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(Convert.toJson(response.body().data));
                    JSONObject deviceName15 = jsonObject.getJSONObject("deviceName15");
                    JSONObject deviceName30 = jsonObject.getJSONObject("deviceName30");
                    JSONObject Comment = jsonObject.getJSONObject("Comment");
                    JSONObject deviceNameCN = jsonObject.getJSONObject("deviceNameCN");


                    Iterator<?> iterator15 = deviceName15.keys();
                    while (iterator15.hasNext()) { // 遍历每个key
                        String key = (String) iterator15.next();
                        String vale = deviceName15.getString(key);
                        SupportedDevice supportedDevice = SupportedDeviceManage.getInstance().getSupportedDevice(key);
                        if (supportedDevice == null) {
                            supportedDevice = new SupportedDevice();
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedDevice.setDeviceName15(vale);
                        }
                        supportedDevice.setKey(key);
                        SupportedDeviceManage.getInstance().addSupportedDevice(supportedDevice);
                    }

                    Iterator<?> iterator30 = deviceName30.keys();
                    while (iterator30.hasNext()) { // 遍历每个key
                        String key = (String) iterator30.next();
                        String vale = deviceName30.getString(key);
                        SupportedDevice supportedDevice = SupportedDeviceManage.getInstance().getSupportedDevice(key);
                        if (supportedDevice == null) {
                            supportedDevice = new SupportedDevice();
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedDevice.setDeviceName30(vale);
                        }
                        supportedDevice.setKey(key);
                        SupportedDeviceManage.getInstance().addSupportedDevice(supportedDevice);
                    }

                    Iterator<?> iteratorcn = deviceNameCN.keys();
                    while (iteratorcn.hasNext()) { // 遍历每个key
                        String key = (String) iteratorcn.next();
                        String vale = deviceNameCN.getString(key);
                        SupportedDevice supportedDevice = SupportedDeviceManage.getInstance().getSupportedDevice(key);
                        if (supportedDevice == null) {
                            supportedDevice = new SupportedDevice();
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedDevice.setDeviceNameCN(vale);
                        }
                        supportedDevice.setKey(key);
                        SupportedDeviceManage.getInstance().addSupportedDevice(supportedDevice);

                    }

                    Iterator<?> iteratorcnComment = Comment.keys();
                    while (iteratorcnComment.hasNext()) { // 遍历每个key
                        String key = (String) iteratorcnComment.next();
                        String vale = Comment.getString(key);
                        SupportedDevice supportedDevice = SupportedDeviceManage.getInstance().getSupportedDevice(key);
                        if (supportedDevice == null) {
                            supportedDevice = new SupportedDevice();
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedDevice.setComment(vale);
                        }
                        supportedDevice.setKey(key);
                        SupportedDeviceManage.getInstance().addSupportedDevice(supportedDevice);

                    }
                    mHandler.sendEmptyMessage(MESSAGE_GET_DATA);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MESSAGE_GET_DATA:
                    List<SupportedDevice> supportedDeviceList = SupportedDeviceManage.getInstance().getSupportedDevice();
                    for (SupportedDevice supportedDevice : supportedDeviceList) {
                        String name = "";
                        if (!HmUtils.isEmptyString(supportedDevice.getDeviceNameCN())) {
                            name = supportedDevice.getDeviceNameCN();
                        }
                        name += supportedDevice.getDeviceName15();
                        MainModle mainModle = new MainModle(name);
                        mainModle.setSupportedDevice(supportedDevice);
                        mainModleList.add(mainModle);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
