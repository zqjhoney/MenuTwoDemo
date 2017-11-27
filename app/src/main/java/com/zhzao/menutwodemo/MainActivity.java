package com.zhzao.menutwodemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhzao.menutwodemo.fragment.F1;
import com.zhzao.menutwodemo.fragment.F2;
import com.zhzao.menutwodemo.fragment.F3;
import com.zzhao.utils.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.main_head)
    RelativeLayout mainHead;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.main_tuijian)
    RadioButton mainTuijian;
    @BindView(R.id.main_duanzi)
    RadioButton mainDuanzi;
    @BindView(R.id.main_shipin)
    RadioButton mainShipin;
    @BindView(R.id.main_bottom)
    RadioGroup mainBottom;
    @BindView(R.id.main_left)
    ImageView mainLeft;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.shezhi)
    ImageView shezhi;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.wenjian)
    ImageView wenjian;
    private F1 f1;
    private F2 f2;
    private F3 f3;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        changeImageSize();
        mainShipin.setOnClickListener(this);
        mainTuijian.setOnClickListener(this);
        mainDuanzi.setOnClickListener(this);
        mainLeft.setOnClickListener(this);
        shezhi.setOnClickListener(this);

        f1 = new F1();
        f2 = new F2();
        f3 = new F3();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f3).commit();
//简单注释
            //是啊空间大s4444
        getSupportFragmentManager().beginTransaction().show(f1).commit();
        getSupportFragmentManager().beginTransaction().hide(f2).commit();
        getSupportFragmentManager().beginTransaction().hide(f3).commit();


    }

    @Override
    public void initdata() {
        mainTuijian.setTextColor(Color.BLUE);
        mainTuijian.setChecked(true);

    }

    @Override
    public void toastShow() {

    }

    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.main_duanzi://段子
                mainDuanzi.setTextColor(Color.BLUE);
                mainTuijian.setTextColor(Color.BLACK);
                mainShipin.setTextColor(Color.BLACK);
                titleName.setText("段子");
                getSupportFragmentManager().beginTransaction().show(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f3).commit();

                break;
            case R.id.main_shipin://视频
                mainDuanzi.setTextColor(Color.BLACK);
                mainTuijian.setTextColor(Color.BLACK);
                mainShipin.setTextColor(Color.BLUE);
                titleName.setText("视频");
                getSupportFragmentManager().beginTransaction().show(f3).commit();
                getSupportFragmentManager().beginTransaction().hide(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f2).commit();

                break;
            case R.id.main_tuijian://推荐
                mainDuanzi.setTextColor(Color.BLACK);
                mainTuijian.setTextColor(Color.BLUE);
                mainShipin.setTextColor(Color.BLACK);
                titleName.setText("推荐");
                getSupportFragmentManager().beginTransaction().show(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f3).commit();

                break;
            case R.id.main_left:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.shezhi://设置
                startActivity(new Intent(this, Sliding_menuActivity.class));
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

    private void changeImageSize() {
        //定义底部标签图片大小
        Drawable drawableFirst = getResources().getDrawable(R.drawable.tuijian);
        drawableFirst.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        mainTuijian.setCompoundDrawables(null, drawableFirst, null, null);//只放上面

        Drawable drawableSearch = getResources().getDrawable(R.drawable.duanzi);
        drawableSearch.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        mainDuanzi.setCompoundDrawables(null, drawableSearch, null, null);//只放上面

        Drawable drawableMe = getResources().getDrawable(R.drawable.shipin);
        drawableMe.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        mainShipin.setCompoundDrawables(null, drawableMe, null, null);//只放上面
    }


}
