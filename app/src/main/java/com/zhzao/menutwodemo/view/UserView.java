package com.zhzao.menutwodemo.view;

import com.zhzao.menutwodemo.entity.UserLie;

/**
 * Created by 张乔君 on 2017/12/18.
 */

public interface UserView {
    void success(String msg);
    void failure(String msg);

    void showLoading();
    void hideLoading();
    void toast(String msg);
    void success(UserLie userLie);
}
