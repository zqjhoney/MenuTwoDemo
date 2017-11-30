package com.zhzao.menutwodemo.modle;

import com.zhzao.menutwodemo.utils.RetorfitFactory;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class TalkModle {

    public void getTalkMsg(String path, Map<String,String> map, Observer<ResponseBody> Observer){
        RetorfitFactory.getInstance().getObserver(path,map,Observer);
    }

}
