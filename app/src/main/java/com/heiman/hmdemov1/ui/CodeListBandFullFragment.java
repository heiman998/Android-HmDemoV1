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
import com.heiman.hmdemov1.manage.CodeListBandFuilManage;
import com.heiman.hmdemov1.manage.SupportedBrandManage;
import com.heiman.hmdemov1.modle.CodeListBandFull;
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
 * @Time :  2018/2/1 上午11:12
 * @Description :
 * @Modify record :
 */
public class CodeListBandFullFragment extends SupportFragment {
    @BindView(R.id.mListView)
    ListView mListView;
    private ItemTextAdapter mAdapter;
    private List<MainModle> mainModleList;
    private static final String REGION = "region";
    private static final String RCTYPE = "rctype";
    private static final String BRANDID = "brandId";
    private String region, rctype, brandId;

    private final int MESSAGE_GET_DATA = 1000;

    public static CodeListBandFullFragment newInstance(String region, String rctype, String brandId) {
        Bundle args = new Bundle();
        CodeListBandFullFragment fragment = new CodeListBandFullFragment();
        fragment.setArguments(args);
        args.putString(REGION, region);
        args.putString(RCTYPE, rctype);
        args.putString(BRANDID, brandId);
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
                start(GetCodeFragment.newInstance(region, rctype, brandId, mainModle.getCodeListBandFull().getKey()));
            }
        });
    }

    private void initData() {
        mainModleList = new ArrayList<>();
        mAdapter = new ItemTextAdapter(getActivity(), mainModleList);
        mListView.setAdapter(mAdapter);
        HmRemotebleHttpManage.getInstance().getCodeListBandFull(region, rctype,
                brandId, new Dialogback<Remoteble<Object>>() {
                    @Override
                    public void onSuccess(Response<Remoteble<Object>> response) {
                        CodeListBandFuilManage.getInstance().clearCodeListBandFull();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(Convert.toJson(response.body().data));
                            JSONObject codeNum = jsonObject.getJSONObject("codeNum");
                            JSONObject rank = jsonObject.getJSONObject("rank");
                            JSONObject year = jsonObject.getJSONObject("year");
                            Iterator<?> iterators = codeNum.keys();
                            while (iterators.hasNext()) {
                                String key = (String) iterators.next();
                                String vale = codeNum.getString(key);
                                CodeListBandFull codeListBandFull = CodeListBandFuilManage.getInstance().getCodeListBandFull(key);
                                if (codeListBandFull == null) {
                                    codeListBandFull = new CodeListBandFull();
                                }
                                if (!HmUtils.isEmptyString(vale)) {
                                    codeListBandFull.setCodeNum(vale);
                                }
                                codeListBandFull.setKey(key);
                                CodeListBandFuilManage.getInstance().addCodeListBandFull(codeListBandFull);
                            }
                            Iterator<?> iteratorsrank = rank.keys();
                            while (iteratorsrank.hasNext()) {
                                String key = (String) iteratorsrank.next();
                                String vale = rank.getString(key);
                                CodeListBandFull codeListBandFull = CodeListBandFuilManage.getInstance().getCodeListBandFull(key);
                                if (codeListBandFull == null) {
                                    codeListBandFull = new CodeListBandFull();
                                }
                                if (!HmUtils.isEmptyString(vale)) {
                                    codeListBandFull.setRank(vale);
                                }
                                codeListBandFull.setKey(key);
                                CodeListBandFuilManage.getInstance().addCodeListBandFull(codeListBandFull);
                            }
                            Iterator<?> iteratorsyear = year.keys();
                            while (iteratorsyear.hasNext()) {
                                String key = (String) iteratorsyear.next();
                                String vale = year.getString(key);
                                CodeListBandFull codeListBandFull = CodeListBandFuilManage.getInstance().getCodeListBandFull(key);
                                if (codeListBandFull == null) {
                                    codeListBandFull = new CodeListBandFull();
                                }
                                if (!HmUtils.isEmptyString(vale)) {
                                    codeListBandFull.setYear(vale);
                                }
                                codeListBandFull.setKey(key);
                                CodeListBandFuilManage.getInstance().addCodeListBandFull(codeListBandFull);
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
                    List<CodeListBandFull> codeListBandFullList = CodeListBandFuilManage.getInstance().getCodeListBandFull();
                    for (CodeListBandFull codeListBandFull : codeListBandFullList) {
                        String name = "";
                        if (!HmUtils.isEmptyString(codeListBandFull.getCodeName())) {
                            name = codeListBandFull.getCodeName();
                        }
                        if (!HmUtils.isEmptyString(codeListBandFull.getCodeNum())) {
                            name += codeListBandFull.getCodeNum();
                        }
                        if (!HmUtils.isEmptyString(codeListBandFull.getYear())) {
                            name += codeListBandFull.getYear();
                        }
                        MainModle mainModle= new MainModle(name);
                        mainModle.setCodeListBandFull(codeListBandFull);
                        mainModleList.add(mainModle);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
