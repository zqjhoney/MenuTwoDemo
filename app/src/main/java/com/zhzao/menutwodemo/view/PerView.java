package com.zhzao.menutwodemo.view;

import com.zhzao.menutwodemo.entity.UserInfo;

/**
 * Created by 张乔君 on 2017/11/30.
 */

public interface PerView {

    void success(UserInfo msg);
    void failure(String msg);

    void success(String msg);
    void showLoading();
    void hideLoading();

    void toast(String msg);
}
