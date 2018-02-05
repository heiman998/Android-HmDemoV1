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
import com.heiman.hmdemov1.manage.SupportedBrandManage;
import com.heiman.hmdemov1.modle.MainModle;
import com.heiman.hmdemov1.modle.SupportedBrand;
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
 * @Time :  2018/2/1 上午10:47
 * @Description :
 * @Modify record :
 */
public class BrandFragment extends SupportFragment {
    @BindView(R.id.mListView)
    ListView mListView;
    private ItemTextAdapter mAdapter;
    private List<MainModle> mainModleList;
    private static final String REGION = "region";
    private static final String RCTYPE = "rctype";
    private String region, rctype;

    private final int MESSAGE_GET_DATA = 1000;

    public static BrandFragment newInstance(String region, String rctype) {
        Bundle args = new Bundle();
        BrandFragment fragment = new BrandFragment();
        fragment.setArguments(args);
        args.putString(REGION, region);
        args.putString(RCTYPE, rctype);
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
                start(CodeListBandFullFragment.newInstance(region, rctype, mainModle.getSupportedBrand().getKey()));
            }
        });
    }

    private void initData() {
        mainModleList = new ArrayList<>();
        mAdapter = new ItemTextAdapter(getActivity(), mainModleList);
        mListView.setAdapter(mAdapter);
        HmRemotebleHttpManage.getInstance().getSupportedBrand(region, rctype,
                "",
                "",
                "", new Dialogback<Remoteble<Object>>() {
                    @Override
                    public void onSuccess(Response<Remoteble<Object>> response) {
                        SupportedBrandManage.getInstance().clearSupportedDevice();

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(Convert.toJson(response.body().data));
                            JSONObject brandName = jsonObject.getJSONObject("brandName");
                            JSONObject brandNameCN = jsonObject.getJSONObject("brandNameCN");
                            JSONObject brandNameZH = jsonObject.getJSONObject("brandNameZH");
                            JSONObject isManuf = jsonObject.getJSONObject("isManuf");

                            Iterator<?> iterator15 = brandName.keys();
                            while (iterator15.hasNext()) {
                                String key = (String) iterator15.next();
                                String vale = brandName.getString(key);
                                SupportedBrand supportedDevice = SupportedBrandManage.getInstance().getSupportedDevice(key);
                                if (supportedDevice == null) {
                                    supportedDevice = new SupportedBrand();
                                }
                                if (!HmUtils.isEmptyString(vale)) {
                                    supportedDevice.setBrandName(vale);
                                }
                                supportedDevice.setKey(key);
                                SupportedBrandManage.getInstance().addSupportedDevice(supportedDevice);
                            }

                            Iterator<?> iterator30 = brandNameCN.keys();
                            while (iterator30.hasNext()) {
                                String key = (String) iterator30.next();
                                String vale = brandNameCN.getString(key);
                                SupportedBrand supportedDevice = SupportedBrandManage.getInstance().getSupportedDevice(key);
                                if (supportedDevice == null) {
                                    supportedDevice = new SupportedBrand();
                                }
                                if (!HmUtils.isEmptyString(vale)) {
                                    supportedDevice.setBrandNameCN(vale);
                                }
                                supportedDevice.setKey(key);
                                SupportedBrandManage.getInstance().addSupportedDevice(supportedDevice);
                            }

                            Iterator<?> iteratorcn = brandNameZH.keys();
                            while (iteratorcn.hasNext()) {
                                String key = (String) iteratorcn.next();
                                String vale = brandNameZH.getString(key);
                                SupportedBrand supportedDevice = SupportedBrandManage.getInstance().getSupportedDevice(key);
                                if (supportedDevice == null) {
                                    supportedDevice = new SupportedBrand();
                                }
                                if (!HmUtils.isEmptyString(vale)) {
                                    supportedDevice.setBrandNameZH(vale);
                                }
                                supportedDevice.setKey(key);
                                SupportedBrandManage.getInstance().addSupportedDevice(supportedDevice);

                            }

                            Iterator<?> iteratorcnComment = isManuf.keys();
                            while (iteratorcnComment.hasNext()) {
                                String key = (String) iteratorcnComment.next();
                                String vale = isManuf.getString(key);
                                SupportedBrand supportedDevice = SupportedBrandManage.getInstance().getSupportedDevice(key);
                                if (supportedDevice == null) {
                                    supportedDevice = new SupportedBrand();
                                }
                                if (!HmUtils.isEmptyString(vale)) {
                                    supportedDevice.setIsManuf(vale);
                                }
                                supportedDevice.setKey(key);
                                SupportedBrandManage.getInstance().addSupportedDevice(supportedDevice);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(MESSAGE_GET_DATA);
                    }
                });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MESSAGE_GET_DATA:
                    List<SupportedBrand> supportedDevice = SupportedBrandManage.getInstance().getSupportedDevice();
                    for (SupportedBrand supportedBrand : supportedDevice) {
                        String name = "";
                        if (!HmUtils.isEmptyString(supportedBrand.getBrandNameCN())) {
                            name = supportedBrand.getBrandNameCN();
                        }
                        name += supportedBrand.getBrandName();
                        MainModle mainModle=new MainModle(name);
                        mainModle.setSupportedBrand(supportedBrand);
                        mainModleList.add(mainModle);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
