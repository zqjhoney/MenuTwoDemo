package com.zhzao.menutwodemo.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.stx.xhb.xbanner.XBanner;
import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.adapter.RecommendAdapter;
import com.zhzao.menutwodemo.entity.VideoBean;
import com.zhzao.menutwodemo.presenter.ShowVideoPresenter;
import com.zhzao.menutwodemo.view.ShowVideoView;
import com.zzhao.utils.Base.BaseFragment;
import com.zzhao.utils.utils.ToastShow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by 张乔君 on 2017/11/25.
 */

public class Sub2 extends BaseFragment implements XBanner.XBannerAdapter,ShowVideoView {
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.f1_wait)
    GifImageView f1Wait;
    private ArrayList<Integer> list;
    private ShowVideoPresenter presenter;
    int type=2;//关注
    int page=1;//页数
    @Override
    protected void initView() {
        list = new ArrayList<>();
        list.add(R.drawable.raw_1500258840);
        list.add(R.drawable.raw_1500258881);
        list.add(R.drawable.raw_1500258901);
        list.add(R.drawable.raw_1500259026);
        View head = View.inflate(getActivity(), R.layout.recyclerhead, null);
        XBanner leftXbanner = head.findViewById(R.id.xbanner);
        leftXbanner.setData(list);//添加数据
        leftXbanner.setmAdapter(this);//添加适配器

        xRecyclerView.addHeaderView(head);
        xRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        xRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        getLoadding();//git动图
    }

    private void getLoadding() {
        GifDrawable gifDrawable = null;
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.dd);
            f1Wait.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        presenter = new ShowVideoPresenter(this);
        presenter.getVideo(type+"",page+"");
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


    @Override
    public void success(VideoBean msg) {
//        List<VideoBean.DataBean> data = msg.getData();
//        xRecyclerView.setAdapter(new RecommendAdapter(getActivity(),data));
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void showLoading() {
        f1Wait.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        f1Wait.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        ToastShow.showToast(getActivity(),msg);
    }
}
