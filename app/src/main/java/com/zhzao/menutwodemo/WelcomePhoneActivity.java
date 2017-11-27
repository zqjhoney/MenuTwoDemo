package com.zhzao.menutwodemo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zzhao.utils.Base.BaseActivity;

public class WelcomePhoneActivity extends BaseActivity {


    private TextView no_uesr;

    @Override
    public int getLayout() {
        return R.layout.activity_welcome_phone;
    }

    @Override
    public void initview() {
        no_uesr = findViewById(R.id.un_user);
        no_uesr.setOnClickListener(this);
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
            case R.id.un_user:
                    startActivity(new Intent(this,MainActivity.class));
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
