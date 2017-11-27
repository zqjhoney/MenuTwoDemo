package com.zhzao.menutwodemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.adapter.VPadapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张乔君 on 2017/11/25.
 */

public class F1 extends Fragment {


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.f1, container, false);
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewPager vp=view.findViewById(R.id.vp);
        TabLayout tabLayout=view.findViewById(R.id.f1_tablayout);
        vp.setAdapter(new VPadapter(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(vp);
    }


}
