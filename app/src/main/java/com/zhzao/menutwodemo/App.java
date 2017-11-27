package com.zhzao.menutwodemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by 张乔君 on 2017/11/23.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        CrashReport.initCrashReport(getApplicationContext(), "8c1fee9067", true);//bugly
        LeakCanary.install(this);//检测内存泄漏
    }
}
