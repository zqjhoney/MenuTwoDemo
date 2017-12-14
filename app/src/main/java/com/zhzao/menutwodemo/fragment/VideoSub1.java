package com.zhzao.menutwodemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.adapter.VideoHostAdapter;
import com.zhzao.menutwodemo.entity.VideoHost;
import com.zhzao.menutwodemo.presenter.VideoDescPresenter;
import com.zhzao.menutwodemo.view.VideoDescView;
import com.zzhao.utils.Base.BaseFragment;
import com.zzhao.utils.utils.ToastShow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张乔君 on 2017/11/29.
 */

public class VideoSub1 extends BaseFragment implements VideoDescView {


    @BindView(R.id.f3_xRlv)
    XRecyclerView f3XRlv;
    @BindView(R.id.f3_bar)
    ProgressBar f3Bar;
    Unbinder unbinder;
    private int page=1;
    private VideoDescPresenter presenter;

    @Override
    protected void initView() {

        presenter = new VideoDescPresenter(this);
        f3XRlv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
         presenter.getHodetVideo(page+"");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void click(View v) {

    }

    @Override
    protected int setLayoutView() {
        return R.layout.f3_sub;
    }

    @Override
    public void success(String msg) {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void success(VideoHost host) {
        //todo 展示
        if(host.getData().size()>0){
            f3XRlv.setAdapter(new VideoHostAdapter((ArrayList<VideoHost.DataBean>) host.getData(),getActivity()));
        }else{
            ToastShow.showToast(getActivity(),host.getMsg());
        }


    }

    @Override
    public void showLoading() {
        f3Bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        f3Bar.setVisibility(View.GONE);
    }


}
