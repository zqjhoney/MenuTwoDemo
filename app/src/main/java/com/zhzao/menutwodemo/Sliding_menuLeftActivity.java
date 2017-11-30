package com.zhzao.menutwodemo;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzhao.utils.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Sliding_menuLeftActivity extends BaseActivity {


    @BindView(R.id.back_back)
    TextView backBack;
    @BindView(R.id.versionCode_check)
    RelativeLayout versionCodeCheck;
    @BindView(R.id.idea_back)
    RelativeLayout ideaBack;
    @BindView(R.id.about_project)
    RelativeLayout aboutProject;
    @BindView(R.id.clear_cache)
    RelativeLayout clearCache;
    private Unbinder bind;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_sliding_menu;
    }

    @Override
    public void initview() {
        bind = ButterKnife.bind(this);
        backBack.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }


    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.back_back:
                this.finish();
                break;
            case R.id.versionCode_check://版本更新

                break;
            case R.id.clear_cache://清除缓存

                break;
            case R.id.about_project://关于一刻钟

                break;
            case R.id.idea_back://意见反馈

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
