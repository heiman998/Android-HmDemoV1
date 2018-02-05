package com.heiman.hmdemov1.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.heiman.hmapisdkv1.back.Dialogback;
import com.heiman.hmapisdkv1.data.HmAgent;
import com.heiman.hmapisdkv1.data.HmRemotebleHttpManage;
import com.heiman.hmapisdkv1.enumbase.HmRcDevieceType;
import com.heiman.hmapisdkv1.modle.Remoteble;
import com.heiman.hmapisdkv1.utils.Convert;
import com.heiman.hmapisdkv1.utils.HmUtils;
import com.heiman.hmdemov1.R;
import com.heiman.hmdemov1.adapter.ItemTextAdapter;
import com.heiman.hmdemov1.manage.SupportedKeyManage;
import com.heiman.hmdemov1.modle.MainModle;
import com.heiman.hmdemov1.modle.SupportedKey;
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
 * @Time :  2018/2/1 下午1:34
 * @Description :
 * @Modify record :
 */
public class GetCodeFragment extends SupportFragment {

    @BindView(R.id.mListView)
    ListView mListView;
    private ItemTextAdapter mAdapter;
    private List<MainModle> mainModleList;
    private static final String REGION = "region";
    private static final String RCTYPE = "rctype";
    private static final String BRANDID = "brandId";
    private static final String CODENUM = "codeNum";
    private String region, rctype, brandId, codeNum;

    private final int MESSAGE_GET_DATA = 1000;
    private static final int MESSAGE_ADD_IR = 1200;
    private static final int MESSAGE_ADD_IR_CODE = 1201;
    private static final int MESSAGE_ADD_ALL_IR_CODE = 1202;
    private static final int MESSAGE_SEND_CODE = 1000;
    private static final int MESSAGE_UP_KEEP = 1001;
    private static final int MESSAGE_UP_DATA = 1002;
    private static final int MESSAGE_GET_NAME = 1003;
    private static final int MESSAGE_GET_CODE_LIST_BAND = 1004;
    private static final int MESSAGE_GET_KEY_ID = 1005;
    private static final int MESSAGE_GET_ALL_CODE = 1203;
    private SupportedKey supportedKey;

    public static GetCodeFragment newInstance(String region, String rctype, String brandId, String codeNum) {
        Bundle args = new Bundle();
        GetCodeFragment fragment = new GetCodeFragment();
        fragment.setArguments(args);
        args.putString(REGION, region);
        args.putString(RCTYPE, rctype);
        args.putString(BRANDID, brandId);
        args.putString(CODENUM, codeNum);
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
            rctype = args.getString(RCTYPE);
            brandId = args.getString(BRANDID);
            codeNum = args.getString(CODENUM);
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
                switch (i) {
                    case 0: {
                        mHandler.sendEmptyMessage(MESSAGE_GET_KEY_ID);
                    }
                    break;
                    case 1: {
                    }
                    break;
                    case 2: {
                        mHandler.sendEmptyMessage(MESSAGE_ADD_IR_CODE);
                    }
                    break;
                }
            }
        });
    }

    private void initData() {
        mainModleList = new ArrayList<>();
        mAdapter = new ItemTextAdapter(getActivity(), mainModleList);
        mainModleList.add(new MainModle("获取所有的按键"));
        mainModleList.add(new MainModle("获取单个码库"));
        mainModleList.add(new MainModle("获取所有码库"));
        mListView.setAdapter(mAdapter);
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MESSAGE_GET_KEY_ID:
                    getKeyID();
                    break;
                case MESSAGE_GET_NAME:
//                    getModeName();
                    break;
                case MESSAGE_GET_CODE_LIST_BAND:
//                    getCodeListBand();
                    break;
                case MESSAGE_ADD_IR:
                    addIR(msg.obj.toString());
                    break;
                case MESSAGE_ADD_IR_CODE:
//                    addIRCode(msg.obj.toString());
                    break;
                case MESSAGE_SEND_CODE:
//                    sendCode("", -1);
                    break;
                case MESSAGE_ADD_ALL_IR_CODE:
                    addAllIrCode(msg.obj.toString());
                    break;
                case MESSAGE_UP_KEEP:
//                    upKeep();
                    break;
                case MESSAGE_UP_DATA:
//                    upData();
                    break;
                case MESSAGE_GET_ALL_CODE:
                    getAllCode();
                    break;

            }
        }
    };

    private void addAllIrCode(String jsonCmd) {
        try {
            JSONObject jsonObject = new JSONObject(jsonCmd);
            if (Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_AC_WINDOW_LCD.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_AC_SPLIT.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_AC_CEILING.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_AC_TOWER_LCD.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_AC_PORTABLE_LCD.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_AC2_NO_LCND.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_WINDOW_NO_LCD.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_TOWER_NO_LCD.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_PORTABLE_NP_LCD.getValue() ||
                    Integer.parseInt(rctype) == HmRcDevieceType.VDEVICE_AC.getValue()) {
                JSONObject AC = jsonObject.getJSONObject("AC");
                try {
                    JSONObject irData = AC.getJSONObject("irData");
                    Iterator<?> irDataIter = irData.keys();
                    while (irDataIter.hasNext()) {
                        String key = (String) irDataIter.next();
                        String vale = irData.getString(key);
                        supportedKey = SupportedKeyManage.getInstance().getSupportedKeyID(key);
                        if (supportedKey == null) {
                            supportedKey = new SupportedKey();
                            supportedKey.setKey(key);
                            supportedKey.setAC(true);
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedKey.setIrData(vale);
                        }
                        SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                    }
                    JSONObject stPower = AC.getJSONObject("stPower");
                    Iterator<?> stPowerIter = stPower.keys();
                    while (stPowerIter.hasNext()) {
                        String key = (String) stPowerIter.next();
                        String vale = stPower.getString(key);
                        supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                        if (supportedKey == null) {
                            supportedKey = new SupportedKey();
                            supportedKey.setKey(key);
                            supportedKey.setAC(true);
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedKey.setStPower(vale);
                        }
                        SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                    }

                    JSONObject stMode = AC.getJSONObject("stMode");
                    Iterator<?> stModeIter = stMode.keys();
                    while (stModeIter.hasNext()) {
                        String key = (String) stModeIter.next();
                        String vale = stMode.getString(key);
                        supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                        if (supportedKey == null) {
                            supportedKey = new SupportedKey();
                            supportedKey.setAC(true);
                            supportedKey.setKey(key);
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedKey.setStMode(vale);
                        }
                        SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                    }
                    JSONObject stTemp = AC.getJSONObject("stTemp");
                    Iterator<?> sstTempIter = stTemp.keys();
                    while (sstTempIter.hasNext()) {
                        String key = (String) sstTempIter.next();
                        String vale = stTemp.getString(key);
                        supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                        if (supportedKey == null) {
                            supportedKey = new SupportedKey();
                            supportedKey.setAC(true);
                            supportedKey.setKey(key);
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedKey.setStTemp(vale);
                        }
                        SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                    }
                    JSONObject stFan = AC.getJSONObject("stFan");
                    Iterator<?> stFanIter = stFan.keys();
                    while (stFanIter.hasNext()) {
                        String key = (String) stFanIter.next();
                        String vale = stFan.getString(key);
                        supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                        if (supportedKey == null) {
                            supportedKey = new SupportedKey();
                            supportedKey.setAC(true);
                            supportedKey.setKey(key);
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedKey.setStFan(vale);
                        }
                        SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                    }
                    JSONObject stSwing = AC.getJSONObject("stSwing");
                    Iterator<?> stSwingIter = stSwing.keys();
                    while (stSwingIter.hasNext()) {
                        String key = (String) stSwingIter.next();
                        String vale = stSwing.getString(key);
                        supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                        if (supportedKey == null) {
                            supportedKey = new SupportedKey();
                            supportedKey.setAC(true);
                            supportedKey.setKey(key);
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedKey.setStSwing(vale);
                        }
                        SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JSONObject AV = jsonObject.getJSONObject("AV");
                try {
                    JSONObject keyId = AV.getJSONObject("keyId");
                    JSONObject irData = AV.getJSONObject("irData");
                    Iterator<?> keyIdIter = keyId.keys();
                    while (keyIdIter.hasNext()) {
                        String key = (String) keyIdIter.next();
                        String vale = keyId.getString(key);

                        supportedKey = SupportedKeyManage.getInstance().getSupportedKey(vale);

                        if (supportedKey == null) {
                            supportedKey = new SupportedKey();
                            supportedKey.setKey(vale);
                        }
                        if (!HmUtils.isEmptyString(key)) {
                            supportedKey.setKeyId(key);
                        }
                        SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                    }
                    Iterator<?> irDataIter = irData.keys();
                    while (irDataIter.hasNext()) {
                        String key = (String) irDataIter.next();
                        String vale = irData.getString(key);
                        supportedKey = SupportedKeyManage.getInstance().getSupportedKeyID(key);
                        if (supportedKey == null) {
                            supportedKey = new SupportedKey();
                            supportedKey.setKey(key);
                        }
                        if (!HmUtils.isEmptyString(vale)) {
                            supportedKey.setIrData(vale);
                        }
                        SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHandler.sendEmptyMessage(MESSAGE_UP_KEEP);
    }

    /**
     * 获取所有码库
     */
    public void getAllCode() {
        HmRemotebleHttpManage.getInstance().getIrDataCode(region,
                rctype, codeNum, new Dialogback<Remoteble<Object>>() {
                    @Override
                    public void onSuccess(Response<Remoteble<Object>> response) {
                        Message msg = new Message();
                        msg.what = MESSAGE_ADD_ALL_IR_CODE;
                        msg.obj = Convert.toJson(response.body().data);
                        mHandler.sendMessage(msg);
                    }
                });
    }

    private void getKeyID() {
        HmRemotebleHttpManage.getInstance().getSupportedKey(region, rctype, codeNum, new Dialogback<Remoteble<Object>>() {
            @Override
            public void onSuccess(Response<Remoteble<Object>> response) {
                Message msg = new Message();
                msg.what = MESSAGE_ADD_IR;
                msg.obj = Convert.toJson(response.body().data);
                mHandler.sendMessage(msg);
            }
        });
    }

    private void addIR(String jsonCmd) {
        try {
            JSONObject jsonObject = new JSONObject(jsonCmd);
            JSONObject AV = jsonObject.getJSONObject("AV");
            try {
                JSONObject KeyLabel7 = AV.getJSONObject("KeyLabel7");
                JSONObject KeyLabel12 = AV.getJSONObject("KeyLabel12");
                JSONObject Comment = AV.getJSONObject("Comment");
                Iterator<?> KeyLabel7Iter = KeyLabel7.keys();
                while (KeyLabel7Iter.hasNext()) {
                    String key = (String) KeyLabel7Iter.next();
                    String vale = KeyLabel7.getString(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale)) {
                        supportedKey.setKeyLabel7(vale);
                    }
                    supportedKey.setAC(false);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }

                Iterator<?> KeyLabel12Iter = KeyLabel12.keys();
                while (KeyLabel12Iter.hasNext()) {
                    String key = (String) KeyLabel12Iter.next();
                    String vale = KeyLabel12.getString(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale)) {
                        supportedKey.setKeyLabel12(vale);
                    }
                    supportedKey.setAC(false);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }

                Iterator<?> CommentIter = Comment.keys();
                while (CommentIter.hasNext()) {
                    String key = (String) CommentIter.next();
                    String vale = Comment.getString(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale)) {
                        supportedKey.setComment(vale);
                    }
                    supportedKey.setAC(false);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject AC = jsonObject.getJSONObject("AC");
            try {
                JSONObject KeyLabel7 = AC.getJSONObject("KeyLabel7");
                JSONObject KeyLabel12 = AC.getJSONObject("KeyLabel12");
                JSONObject Comment = AC.getJSONObject("Comment");
                Iterator<?> KeyLabel7Iter = KeyLabel7.keys();
                while (KeyLabel7Iter.hasNext()) {
                    String key = (String) KeyLabel7Iter.next();
                    String vale = KeyLabel7.getString(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale)) {
                        supportedKey.setKeyLabel7(vale);
                    }
                    supportedKey.setAC(true);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }

                Iterator<?> KeyLabel12Iter = KeyLabel12.keys();
                while (KeyLabel12Iter.hasNext()) {
                    String key = (String) KeyLabel12Iter.next();
                    String vale = KeyLabel12.getString(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale)) {
                        supportedKey.setKeyLabel12(vale);
                    }
                    supportedKey.setAC(true);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }

                Iterator<?> CommentIter = Comment.keys();
                while (CommentIter.hasNext()) {
                    String key = (String) CommentIter.next();
                    String vale = Comment.getString(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale)) {
                        supportedKey.setComment(vale);
                    }
                    supportedKey.setAC(true);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }

                JSONObject stPower = AC.getJSONObject("stPower");
                Iterator<?> stPowerIter = stPower.keys();
                while (stPowerIter.hasNext()) {
                    String key = (String) stPowerIter.next();
                    JSONObject vale = stPower.getJSONObject(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale.toString())) {
                        supportedKey.setStPower(vale.toString());
                    }
                    supportedKey.setAC(true);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }

                JSONObject stMode = AC.getJSONObject("stMode");
                Iterator<?> stModeIter = stMode.keys();
                while (stModeIter.hasNext()) {
                    String key = (String) stModeIter.next();
                    JSONObject vale = stMode.getJSONObject(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale.toString())) {
                        supportedKey.setStMode(vale.toString());
                    }
                    supportedKey.setAC(true);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }
                JSONObject stTemp = AC.getJSONObject("stTemp");
                Iterator<?> sstTempIter = stTemp.keys();
                while (sstTempIter.hasNext()) {
                    String key = (String) sstTempIter.next();
                    JSONObject vale = stTemp.getJSONObject(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale.toString())) {
                        supportedKey.setStTemp(vale.toString());
                    }
                    supportedKey.setAC(true);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }
                JSONObject stFan = AC.getJSONObject("stFan");
                Iterator<?> stFanIter = stFan.keys();
                while (stFanIter.hasNext()) {
                    String key = (String) stFanIter.next();
                    JSONObject vale = stFan.getJSONObject(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale.toString())) {
                        supportedKey.setStFan(vale.toString());
                    }
                    supportedKey.setAC(true);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }
                JSONObject stSwing = AC.getJSONObject("stSwing");
                Iterator<?> stSwingIter = stSwing.keys();
                while (stSwingIter.hasNext()) {
                    String key = (String) stSwingIter.next();
                    JSONObject vale = stSwing.getJSONObject(key);
                    supportedKey = SupportedKeyManage.getInstance().getSupportedKey(key);
                    if (supportedKey == null) {
                        supportedKey = new SupportedKey();
                    }
                    if (!HmUtils.isEmptyString(vale.toString())) {
                        supportedKey.setStSwing(vale.toString());
                    }
                    supportedKey.setAC(true);
                    supportedKey.setKey(key);
                    SupportedKeyManage.getInstance().addSupportedKey(supportedKey);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
