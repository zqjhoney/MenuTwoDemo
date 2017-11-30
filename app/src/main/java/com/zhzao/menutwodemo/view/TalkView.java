package com.zhzao.menutwodemo.view;

import com.zhzao.menutwodemo.entity.TalkBean;
import com.zzhao.utils.Base.BaseView;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public interface TalkView{

    void success(String msg);
    void failure(String msg);

    void showLoading();
    void hideLoading();
}
