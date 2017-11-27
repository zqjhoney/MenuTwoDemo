package com.zzhao.utils.Base;

/**
 * Created by 张乔君 on 2017/11/14.
 */

public class BasePresenter<T> {
    T view;

    //绑定
    public BasePresenter(T view) {
        this.view = view;
    }

    //解绑
    public void disPatch(){
       this.view=null;
    }
}
