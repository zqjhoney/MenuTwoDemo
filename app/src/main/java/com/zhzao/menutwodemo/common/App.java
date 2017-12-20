package com.zhzao.menutwodemo.common;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by 张乔君 on 2017/11/23.
 */

public class App extends MultiDexApplication {
   public static Context mContext;
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        mContext=this;
        LeakCanary.install(this);
        CrashReport.initCrashReport(getApplicationContext(), "8c1fee9067", true);//bugly
        LeakCanary.install(this);//检测内存泄漏

        Logger.init("mytag")    //LOG TAG默认是PRETTYLOGGER
                .methodCount(3)                 // 决定打印多少行（每一行代表一个方法）默认：2
                .hideThreadInfo()               // 隐藏线程信息 默认：显示
                .logLevel(LogLevel.NONE)        // 是否显示Log 默认：LogLevel.FULL（全部显示）
                .methodOffset(2) ;              // 默认：0
         //       .logAdapter(new AndroidLogAdapter()); //可以自己构造适配器默认：AndroidLogAdapter

        //崩溃要重启的
     //   UncaughtExceptionHandlerImpl.getInstance().init(this, BuildConfig.DEBUG, true, 0, MainActivity.class);
       // 禁止重启
        // UncaughtExceptionHandlerImpl.getInstance().init(this,BuildConfig.DEBUG);

    }
}
