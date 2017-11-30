package com.zhzao.menutwodemo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.adapter.VP3Adapter;
import com.zzhao.utils.Base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张乔君 on 2017/11/25.
 */

public class F3 extends BaseFragment {

    @BindView(R.id.f3_tablayout)
    TabLayout f3Tablayout;
    @BindView(R.id.f3_vp)
    ViewPager f3Vp;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        f3Vp.setAdapter(new VP3Adapter(getActivity().getSupportFragmentManager()));
        f3Tablayout.setupWithViewPager(f3Vp);
    }

    @Override
    protected void click(View v) {

    }

    @Override
    protected int setLayoutView() {
        return R.layout.f3;
    }

}
