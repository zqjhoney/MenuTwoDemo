package com.zhzao.menutwodemo.presenter;

import android.text.TextUtils;

import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.modle.EditModle;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.view.EditView;
import com.zzhao.utils.Base.BasePresenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/29.
 */

public class EditPresenter extends BasePresenter<EditView> {
    private EditModle modle;
    private EditView view;
    private final Map<String, String> map;

    public EditPresenter(EditView view) {
        super(view);
        this.view=view;
        modle=new EditModle();
        map = new HashMap<>();
    }


    public void editMsg(String msg,String uid){
        if(TextUtils.isEmpty(msg)){
                view.toast("内容不能为空");
                return;
        }
        view.showLoading();
        map.put("uid",uid);
        map.put("content",msg);
        modle.getEditMsg(Api.PUBLIC, map, new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody value) {
                try {
                    view.hideLoading();
                    view.success(value.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                Logger.getLogger("EditPresenter:s"+"异常"+e);
            }

            @Override
            public void onComplete() {
            }
        });

    }
}
