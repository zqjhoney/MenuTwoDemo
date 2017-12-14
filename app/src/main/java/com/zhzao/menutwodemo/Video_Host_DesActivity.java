package com.zhzao.menutwodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhzao.menutwodemo.entity.VideoHost;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.utils.ToastShow;

import java.io.Serializable;

public class Video_Host_DesActivity extends BaseActivity {


    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_video_host_des;
    }

    @Override
    public void initview() {
        Bundle extras = getIntent().getExtras();
        VideoHost.DataBean person = (VideoHost.DataBean) extras.getSerializable("person");
        ToastShow.showToast(this,person.getUser().getNickname());

    }

    @Override
    public void initdata() {

    }

    @Override
    public void setClick(View view) {

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
