package com.zhzao.menutwodemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhzao.menutwodemo.fragment.F1;
import com.zhzao.menutwodemo.fragment.F2;
import com.zhzao.menutwodemo.fragment.Sub1;
import com.zhzao.menutwodemo.fragment.Sub2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张乔君 on 2017/11/25.
 */

public class VPadapter extends FragmentPagerAdapter {


    private String[] list={"热门","关注"};

    public VPadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag=new Sub1();
                break;
            case 1:
                frag=new Sub2();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list[position];
    }
}
