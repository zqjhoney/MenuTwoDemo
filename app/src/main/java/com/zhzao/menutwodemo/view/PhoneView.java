package com.zhzao.menutwodemo.view;

import com.zzhao.utils.Base.BaseView;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public interface PhoneView extends BaseView {
    void success(String msg);
    void failure(String msg);
    void toast(String msg);
}
