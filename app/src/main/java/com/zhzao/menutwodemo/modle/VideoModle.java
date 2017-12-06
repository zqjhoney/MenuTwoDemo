package com.zhzao.menutwodemo.modle;

import com.igexin.sdk.PushBuildConfig;
import com.zhzao.menutwodemo.utils.RetorfitFactory;

import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/12/1.
 */

public class VideoModle {

    public void videoUpload(String path, List<MultipartBody.Part> list, Consumer<ResponseBody> consumer){
        RetorfitFactory.getInstance().uploadConsumer(path,list,consumer);
    }
}
