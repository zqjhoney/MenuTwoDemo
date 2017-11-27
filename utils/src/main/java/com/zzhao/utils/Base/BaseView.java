package com.zzhao.utils.Base;

/**
 * Created by 张乔君 on 2017/11/14.
 */

public interface BaseView {
    void success(String msg);
    void failure(String msg);

    void showLoading();
    void hideLoading();
}
