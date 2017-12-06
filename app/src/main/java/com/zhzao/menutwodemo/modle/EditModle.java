package com.zhzao.menutwodemo.modle;

import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.RetorfitFactory;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/29.
 */

public class EditModle {

    public void getEditMsg(String path,Map<String, RequestBody> map, List<MultipartBody.Part> list, Consumer<ResponseBody> consumer){
        RetorfitFactory.getInstance().uploadConsumer(path,map,list,consumer);
    }
}
