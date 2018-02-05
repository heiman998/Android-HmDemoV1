package com.heiman.hmdemov1.ui;

import android.os.Bundle;

import com.heiman.hmapisdkv1.back.Dialogback;
import com.heiman.hmapisdkv1.data.HmAgent;
import com.heiman.hmapisdkv1.data.HmRemotebleHttpManage;
import com.heiman.hmapisdkv1.modle.Remoteble;
import com.heiman.hmapisdkv1.utils.Convert;
import com.heiman.hmdemov1.R;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @Author : 肖力 by mac
 * @Time :  2018/1/29 上午11:21
 * @Description :
 * @Modify record :
 */
public class HttpActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
//        ButterKnife.bind(this);
        /**
         * 这里需要填入相应的MAC地址
         */
        HmRemotebleHttpManage.getInstance().init("a020a62d4620", new Dialogback<Remoteble<Object>>() {
            @Override
            public void onSuccess(Response<Remoteble<Object>> response) {
                try {
                    JSONObject jsonObject = new JSONObject(Convert.toJson(response.body().data));
                    HmAgent.getLogger().json(jsonObject.toString());
                    JSONObject pa_key = jsonObject.getJSONObject("pa_key");
                    Iterator<?> iterator = pa_key.keys();// 应用迭代器Iterator 获取所有的key值
                    while (iterator.hasNext()) { // 遍历每个key
                        String key = (String) iterator.next();
                        String vale = pa_key.getString(key);
                        HmRemotebleHttpManage.getInstance().setPaKey(vale);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        SupportFragment firstFragment = findFragment(RegionFragment.class);
        if (firstFragment == null) {
            loadRootFragment(R.id.fl_container, RegionFragment.newInstance());
        }
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
