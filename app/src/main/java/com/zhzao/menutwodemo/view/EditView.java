package com.zhzao.menutwodemo.view;

/**
 * Created by 张乔君 on 2017/11/29.
 */

public interface EditView {

    void success(String msg);
    void failure(String msg);

    void showLoading();
    void hideLoading();
    void toast(String msg);
}
