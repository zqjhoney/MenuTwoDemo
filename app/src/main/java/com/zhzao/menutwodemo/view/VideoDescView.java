package com.zhzao.menutwodemo.view;

import com.zhzao.menutwodemo.entity.VideoHost;

/**
 * Created by 张乔君 on 2017/12/10.
 */

public interface VideoDescView {
    void success(String msg);
    void failure(String msg);
    void success(VideoHost host);

    void showLoading();
    void hideLoading();
}
