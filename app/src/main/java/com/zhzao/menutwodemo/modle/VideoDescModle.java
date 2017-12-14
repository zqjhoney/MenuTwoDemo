package com.zhzao.menutwodemo.modle;

import com.zhzao.menutwodemo.entity.VideoHost;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.RetorfitFactory;

import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/12/10.
 */

public class VideoDescModle {

    public void getHostMsg(String path, Map<String,String> map, MyCallback<VideoHost> myCallback){

        RetorfitFactory.getInstance().getObserver(path,map,myCallback);

    }
    public void getNearMsg(String path, Map<String,String> map, Consumer<ResponseBody> consumer){
RetorfitFactory.getInstance().getConsumer(path,map,consumer);

    }
}
