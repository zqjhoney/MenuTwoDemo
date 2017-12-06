package com.zhzao.menutwodemo.modle;

import com.zhzao.menutwodemo.entity.UserInfo;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.RetorfitFactory;

import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/30.
 */

public class PerModle {

    public void getPerMsg(String path, Map<String,String> map, MyCallback<UserInfo> callback){
        RetorfitFactory.getInstance().getConsumer(path,map,callback);
    }

    //上传头像
    public  void upPhoto(String path, String param, List<MultipartBody.Part> part, Consumer<ResponseBody> consumer){
        RetorfitFactory.getInstance().uploadConsumer(path,part,consumer);
    }
}
