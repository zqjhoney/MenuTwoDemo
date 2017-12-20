package com.zhzao.menutwodemo.modle;

import com.zhzao.menutwodemo.entity.UserLie;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.RetorfitFactory;

import java.util.Map;

/**
 * Created by 张乔君 on 2017/12/18.
 */

public class UserModle {
    public void getUserVideos(String path, Map<String,String> map, MyCallback<UserLie> callback){
        RetorfitFactory.getInstance().getConsumer(path,map,callback);
    }
}
