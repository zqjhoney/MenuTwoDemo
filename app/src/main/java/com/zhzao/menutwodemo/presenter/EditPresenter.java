package com.zhzao.menutwodemo.presenter;

import android.text.TextUtils;

import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.modle.EditModle;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.EditView;
import com.zzhao.utils.Base.BasePresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/29.
 */

public class EditPresenter extends BasePresenter<EditView> {
    private EditModle modle;
    private EditView view;
    private final Map<String, RequestBody> map;
    private ArrayList<MultipartBody.Part> bodylist;

    public EditPresenter(EditView view) {
        super(view);
        this.view=view;
        modle=new EditModle();
        map = new HashMap<>();
    }


    public void editMsg(String msg, String uid, ArrayList<File> files){
        if(TextUtils.isEmpty(msg)){
                view.toast("内容不能为空");
                return;
        }
        view.showLoading();
        bodylist = new ArrayList<>();
        if(files!=null && files.size()>0){
            for (File file : files) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("jokeFiles", file.getName(), requestFile);
                bodylist.add(filePart);
            }
        }
        RequestBody msgBody = RequestBody.create(MediaType.parse("multipart/form-data"), msg);
        RequestBody uidBody = RequestBody.create(MediaType.parse("multipart/form-data"), uid);
        map.put("content", msgBody);//添加参数
        map.put("uid",uidBody);//添加参数

        modle.getEditMsg(Api.PUBLIC, map, bodylist, new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                try {
                    view.hideLoading();
                    view.success(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
