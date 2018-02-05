package com.heiman.hmdemov1.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.heiman.hmdemov1.modle.MainModle;
import com.heiman.hmdemov1.modle.Region;
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
 * @Time :  2018/2/1 上午9:57
 * @Description :
 * @Modify record :
 */
public class RegionFragment extends SupportFragment {
    @BindView(R.id.mListView)
    ListView mListView;
    private ItemTextAdapter mAdapter;
    private List<MainModle> mainModleList;

    public static RegionFragment newInstance() {
        Bundle args = new Bundle();
        RegionFragment fragment = new RegionFragment();
        fragment.setArguments(args);
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
        initEven();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

//        if (findChildFragment(RCTypeFragment.class) == null) {
//            loadRootFragment(R.id.fl_first_container, RCTypeFragment.newInstance());
//        }
    }

    private void initEven() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainModle mainModle = mainModleList.get(i);
                HmAgent.getLogger().i(mainModle.getName());
                switch (i) {
                    case 0: {
                        HmRemotebleHttpManage.getInstance().getRegion(new Dialogback<Remoteble<Object>>() {
                            @Override
                            public void onSuccess(Response<Remoteble<Object>> response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(Convert.toJson(response.body().data));
                                    JSONObject region = jsonObject.getJSONObject("region");
                                    Iterator<?> iterator = region.keys();// 应用迭代器Iterator 获取所有的key值
                                    while (iterator.hasNext()) { // 遍历每个key
                                        String key = (String) iterator.next();
                                        String vale = region.getString(key);
                                        Region region1 = new Region();
                                        if (!HmUtils.isEmptyString(vale)) {
                                            region1.setRegion(vale);
                                            mainModleList.add(new MainModle(vale));
                                        }
                                    }

                                    mAdapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                    }
                    break;

                    default:
                        start(RCTypeFragment.newInstance(mainModle.getName()));
                        break;
                }
            }
        });
    }

    private void initData() {
        mainModleList = new ArrayList<>();
        mainModleList.add(new MainModle("获取地区信息"));
//        mainModleList.add(new MainModle("获取支持的设备"));
//        mainModleList.add(new MainModle("主要的品牌列表"));
//        mainModleList.add(new MainModle("获得完整的代码清单选择设备类别和品牌"));
//        mainModleList.add(new MainModle("得到一个红外数据选择的关键代码"));
//        mainModleList.add(new MainModle("得到完整的红外数据的键选定的代码"));
        mAdapter = new ItemTextAdapter(getActivity(), mainModleList);
        mListView.setAdapter(mAdapter);
    }

}
