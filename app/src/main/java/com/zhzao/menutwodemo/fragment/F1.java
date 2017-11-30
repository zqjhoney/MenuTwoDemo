package com.zhzao.menutwodemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.adapter.VPadapter;
import com.zzhao.utils.Base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    }
    @Override
    protected void click(View v) {
    }
    @Override
    protected int setLayoutView() {
        return R.layout.f1;
    }



}
