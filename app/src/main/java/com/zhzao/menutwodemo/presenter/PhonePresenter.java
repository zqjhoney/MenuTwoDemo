package com.zhzao.menutwodemo.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.modle.PhoneModle;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.PhoneView;
import com.zzhao.utils.Base.BasePresenter;
import com.zzhao.utils.utils.ToastShow;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class PhonePresenter extends BasePresenter<PhoneView> {
    private PhoneModle modle;
    private PhoneView view;
    private Map<String,String> map;
    public PhonePresenter(PhoneView view) {
        super(view);
        modle=new PhoneModle();
        this.view=view;
        map=new HashMap<>();
    }


    //登录方法
    public void login(String phone,String pwd){
        if(TextUtils.isEmpty(phone)){
            view.toast("手机号不能为空");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            view.toast("密码不能为空");
            return;
        }

        map.put("mobile",phone);
        map.put("password",pwd);
        view.showLoading();
        modle.login(Api.LOGIN, map, new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(ResponseBody value) {
                try {
                    String msg=value.string();
                    view.showLoading();
                    view.success(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable e) {
              //  view.failure("异常"+e);
                Logger.init().logLevel(LogLevel.FULL).methodCount(3);
                Logger.v("异常"+e);
            }

            @Override
            public void onComplete() {
            }
        });

    }



}
