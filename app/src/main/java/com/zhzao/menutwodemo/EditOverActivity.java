package com.zhzao.menutwodemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onetime.platform.R;
import com.zzhao.utils.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditOverActivity extends BaseActivity {
    @BindView(R.id.over_cancle)
    TextView overCancle;
    @BindView(R.id.go_look)
    Button goLook;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_edit_over;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        overCancle.setOnClickListener(this);
        goLook.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Override
    public void setClick(View view) {

        switch (view.getId()){
            case R.id.go_look://前往看看
                Intent intent= new Intent(this,MainActivity.class);
                intent.putExtra("page",true);
                startActivity(intent);
                break;
            case R.id.over_cancle://返回
                    startActivity(new Intent(this,Sliding_menuRightActivity.class));
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
