package com.zzhao.utils.Base;

import android.app.Application;
import android.content.Context;

/**
 * Created by 张乔君 on 2017/11/13.
 */

public class BaseApp extends Application{
    public static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=this;
    }
}
