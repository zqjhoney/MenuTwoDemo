package com.zhzao.menutwodemo;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.onetime.platform.R;
import com.zzhao.utils.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class Video_Host_DesActivity extends BaseActivity {


    @BindView(R.id.f3_jcv)
    JCVideoPlayer f3Jcv;

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
        ButterKnife.bind(this);
        String videoUrl = getIntent().getStringExtra("videoUrl");
        String img=getIntent().getStringExtra("img");
        f3Jcv.setUp(videoUrl,"随便");
        System.out.println("xxxxxx"+videoUrl);
        Glide.with(this).load(img).into(f3Jcv.ivThumb);

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
