package com.zhzao.menutwodemo.modle;

import com.zhzao.menutwodemo.entity.VideoBean;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.RetorfitFactory;

import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/12/2.
 */

public class ShowVideoModle {

    public  void getVideoMsg(String path, Map<String,String> map, MyCallback<VideoBean> callback){
        RetorfitFactory.getInstance().getObserver(path,map,callback);
    }

    public void getAttenMsg(String path, Map<String,String> map, Consumer<ResponseBody> res){
        RetorfitFactory.getInstance().getConsumer(path,map,res);
    }


}
