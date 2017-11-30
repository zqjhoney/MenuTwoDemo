package com.zhzao.menutwodemo.modle;

import com.zhzao.menutwodemo.utils.RetorfitFactory;
import com.zzhao.utils.Base.BaseModle;
import com.zzhao.utils.Base.BasePresenter;
import com.zzhao.utils.Base.ICallBack;

import java.util.Map;

import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class PhoneModle{

    //规定都是post请求方法
    public void login(String path,  Map<String, String> map, Observer<ResponseBody> observer) {

        RetorfitFactory retorfitFactory = RetorfitFactory.getInstance();
        retorfitFactory.getObserver(path,map,observer);

    }
}
