package com.zhzao.menutwodemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 张乔君 on 2017/12/5.
 */

public class PromissActivity  extends AppCompatActivity{
    private static final int INTERNET_CODE=100;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //判断版本号在6.0 （23）以上，
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.INTERNET},INTERNET_CODE);

        }else{
            //不需要查询的时候进行的操作

            new Runnable(){
                @Override
                public void run() {
                    Looper.prepare();
                    Handler handler=new Handler(Looper.myLooper());
                    Looper.loop();

                }
            };
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode== INTERNET_CODE){//同意请求权限

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //同意权限后需要进行的操作
            }else if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                //权限在手机上被禁止，可能无法询问开启，智能提醒用户手动到设置变更
            }

        }else{
            //拒绝请求权限
        }
    }
}
