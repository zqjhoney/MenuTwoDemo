package com.zhzao.menutwodemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.adapter.RecommendLeftAdapter;
import com.zzhao.utils.Base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张乔君 on 2017/11/25.
 */

public class Sub1 extends BaseFragment implements XBanner.XBannerAdapter {
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    private ArrayList<Integer> list;

    @Override
    protected void initView() {
        list = new ArrayList<>();
        list.add(R.drawable.raw_1500258840);
        list.add(R.drawable.raw_1500258881);
        list.add(R.drawable.raw_1500258901);
        list.add(R.drawable.raw_1500259026);
        View head = View.inflate(getActivity(), R.layout.recyclerhead, null);
        XBanner leftXbanner=head.findViewById(R.id.xbanner);
        leftXbanner.setData(list);//添加数据
        leftXbanner.setmAdapter(this);//添加适配器
        leftXbanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        xRecyclerView.addHeaderView(head);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        xRecyclerView.setAdapter(new RecommendLeftAdapter(getActivity()));
    }

    @Override
    protected void click(View v) {

    }

    @Override
    protected int setLayoutView() {
        return R.layout.f1_sub;
    }



    @Override
    public void loadBanner(XBanner xBanner, View view, int i) {
        Glide.with(this).load(list.get(i)).into((ImageView) view);
    }
}
