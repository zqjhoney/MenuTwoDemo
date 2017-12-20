package com.zhzao.menutwodemo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.onetime.platform.R;
import com.zhzao.menutwodemo.adapter.VPadapter;
import com.zzhao.utils.Base.BaseFragment;

import butterknife.BindView;

/**
 * Created by 张乔君 on 2017/11/25.
 */

public class F1 extends BaseFragment {


    @BindView(R.id.f1_tablayout)
    TabLayout f1Tablayout;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected void initView() {
        vp.setAdapter(new VPadapter(getActivity().getSupportFragmentManager()));
        f1Tablayout.setupWithViewPager(vp);
    }

    @Override
    protected void initData() {

//        ViewTreeObserver observer = f1Tablayout.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                设置透明渐变等效果
//            }
//        });
    }

    @Override
    protected void click(View v) {
    }

    @Override
    protected int setLayoutView() {
        return R.layout.f1;
    }




}
