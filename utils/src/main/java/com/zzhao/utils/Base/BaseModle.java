package com.zzhao.utils.Base;

import java.util.Map;

/**
 * Created by 张乔君 on 2017/11/14.
 */

public interface BaseModle {
    void login(String url, String method, Map<String,Object> map,ICallBack callBack);
}
