package com.heiman.hmdemov1.modle;/**
 * Created by hp on 2016/11/24.
 */

import java.util.Map;

/**
 * 作者：肖力
 * 邮箱：554674787@qq.com
 * 时间：2016/11/24 13:54
 */
public class Remote {
    private Map<String,OtherRemote> otherRemoteMap;

    public Map<String, OtherRemote> getOtherRemoteMap() {
        return otherRemoteMap;
    }

    public void setOtherRemoteMap(Map<String, OtherRemote> otherRemoteMap) {
        this.otherRemoteMap = otherRemoteMap;
    }
}
