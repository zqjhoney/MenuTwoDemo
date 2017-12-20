package com.zhzao.menutwodemo.view;

import com.zhzao.menutwodemo.entity.VideoBean;

/**
 * Created by 张乔君 on 2017/12/2.
 */

public interface ShowVideoView {
    void success(VideoBean msg);
    void failure(String msg);
    void  success(String msg);

    void showLoading();
    void hideLoading();
    void toast(String msg);
}
