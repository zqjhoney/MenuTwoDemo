package com.zhzao.menutwodemo.presenter;

import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.modle.TalkModle;
import com.zhzao.menutwodemo.view.TalkView;
import com.zzhao.utils.Base.BasePresenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class TalkPresenter extends BasePresenter<TalkView> {

    private TalkView view;
    private TalkModle modle;
    private Map<String,String> map;
    public TalkPresenter(TalkView view) {
        super(view);
        this.view=view;
        modle=new TalkModle();
        map=new HashMap<>();
    }


    //请求数据
    public  void getMsg(String page){
            view.showLoading();
            map.put("page",page);
            modle.getTalkMsg(Api.Talk_URL, map, new Observer<ResponseBody>() {
                @Override
                public void onSubscribe(Disposable d) {
                }
                @Override
                public void onNext(ResponseBody value) {
                        view.hideLoading();
                    try {
                        view.success(value.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable e) {
                     Logger.getLogger("异常"+e);
                }

                @Override
                public void onComplete() {
                }
            });

    }
}
