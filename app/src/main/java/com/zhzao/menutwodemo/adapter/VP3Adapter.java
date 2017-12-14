package com.zhzao.menutwodemo.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhzao.menutwodemo.fragment.VideoSub1;
import com.zhzao.menutwodemo.fragment.VideoSub2;

/**
 * Created by 张乔君 on 2017/11/29.
 */

public class VP3Adapter  extends FragmentPagerAdapter {

    private String[] list={"热门","附近"};

    public VP3Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag=new VideoSub1();
                break;
            case 1:
                frag=new VideoSub2();
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
