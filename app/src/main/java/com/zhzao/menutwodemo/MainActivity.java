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

import com.bumptech.glide.Glide;
import com.zhzao.menutwodemo.common.App;
import com.zhzao.menutwodemo.fragment.F1;
import com.zhzao.menutwodemo.fragment.F2;
import com.zhzao.menutwodemo.fragment.F3;
import com.zhzao.menutwodemo.utils.MapUtils;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zzhao.utils.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

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
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.shezhi)
    ImageView shezhi;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.wenjian)
    ImageView wenjian;
    @BindView(R.id.main_right)
    ImageView mainRight;
    @BindView(R.id.start_photo)
    CircleImageView startPhoto;
    @BindView(R.id.main_left)
    CircleImageView mainLeft;
    @BindView(R.id.start_name)
    TextView startName;
    private F1 f1;
    private F2 f2;
    private F3 f3;
    private Unbinder bind;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initview() {
        bind = ButterKnife.bind(this);
//        String sha1 = MapUtils.getSHA1(this);
//        System.out.println("xxxx"+sha1);
        changeImageSize();
        boolean pageboolean = getIntent().getBooleanExtra("page", false);
        mainShipin.setOnClickListener(this);
        mainTuijian.setOnClickListener(this);
        mainDuanzi.setOnClickListener(this);
        mainLeft.setOnClickListener(this);
        shezhi.setOnClickListener(this);
        mainRight.setOnClickListener(this);
        startPhoto.setOnClickListener(this);

        f1 = new F1();
        f2 = new F2();
        f3 = new F3();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f3).commit();
        if (pageboolean) {
            getSupportFragmentManager().beginTransaction().show(f2).commit();
            getSupportFragmentManager().beginTransaction().hide(f1).commit();
            getSupportFragmentManager().beginTransaction().hide(f3).commit();
            mainDuanzi.setTextColor(Color.BLUE);
            mainDuanzi.setChecked(true);
        } else {
            getSupportFragmentManager().beginTransaction().show(f1).commit();
            getSupportFragmentManager().beginTransaction().hide(f2).commit();
            getSupportFragmentManager().beginTransaction().hide(f3).commit();
            mainTuijian.setTextColor(Color.BLUE);
            mainTuijian.setChecked(true);
        }
    }

    @Override
    public void initdata() {
        String icon = SharePreUtils.getShareprefervalue("icon");
        String name = SharePreUtils.getShareprefervalue("knname");
        if (icon != null) {
            Glide.with(this).load(icon).into(mainLeft);
            Glide.with(this).load(icon).into(startPhoto);
            startName.setText(name);
        }
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
                startActivity(new Intent(this, Sliding_menuLeftActivity.class));
                break;
            case R.id.main_right:
                startActivity(new Intent(this, Sliding_menuRightActivity.class));
                break;
            case R.id.start_photo:
                startActivity(new Intent(this, Per_Msg_Activity.class));
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
