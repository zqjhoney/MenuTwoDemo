package com.zhzao.menutwodemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onetime.platform.R;
import com.zzhao.utils.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Sliding_menuRightActivity extends BaseActivity {
    @BindView(R.id.video_Msg)
    LinearLayout videoMsg;
    @BindView(R.id.talk_Msg)
    LinearLayout talkMsg;
    @BindView(R.id.end_cancle)
    TextView endCancle;
    private Unbinder bind;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_sliding_menu_right;
    }

    @Override
    public void initview() {
        bind = ButterKnife.bind(this);
        videoMsg.setOnClickListener(this);
        talkMsg.setOnClickListener(this);
        endCancle.setOnClickListener(this);//后退
    }

    @Override
    public void initdata() {
    }

    @Override
    public void setClick(View view) {

        switch (view.getId()) {
            case R.id.video_Msg:
                startActivity(new Intent(this, Video_EditActivity.class));
                break;
            case R.id.talk_Msg:
                startActivity(new Intent(this, EditTalkActivity.class));
                break;
            case R.id.end_cancle:
                this.finish();
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
