package com.zhzao.menutwodemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.zhzao.menutwodemo.service.DemoIntentService;
import com.zhzao.menutwodemo.service.DemoPushService;
import com.zzhao.utils.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.other)
    TextView other;

    @Override
    public Boolean isFull() {
        return true;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        other.setOnClickListener(this);
    }

    @Override
    public void initdata() {


    }


    @Override
    public void setClick(View view) {

        switch (view.getId()) {
            case R.id.other:
                startActivity(new Intent(this, WelcomePhoneActivity.class));
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
