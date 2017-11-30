package com.zhzao.menutwodemo.modle;

import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.RetorfitFactory;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/29.
 */

public class EditModle {

    public void getEditMsg(String path, Map<String,String> map, Observer<ResponseBody> observer){
        RetorfitFactory.getInstance().getObserver(path,map,observer);
    }
}
