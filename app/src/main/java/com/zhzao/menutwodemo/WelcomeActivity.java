package com.zhzao.menutwodemo;
import com.igexin.sdk.PushManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhzao.menutwodemo.service.DemoIntentService;
import com.zhzao.menutwodemo.service.DemoPushService;
import com.zzhao.utils.Base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    private TextView other;

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initview() {
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        other= findViewById(R.id.other);
        other.setOnClickListener(this);


    }

    @Override
    public void initdata() {


    }

    @Override
    public void toastShow() {

    }

    @Override
    public void setClick(View view) {

        switch (view.getId()){
            case R.id.other:
                startActivity(new Intent(this,WelcomePhoneActivity.class));
                break;
        }
    }

    @Override
    public void success(String msg) {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
