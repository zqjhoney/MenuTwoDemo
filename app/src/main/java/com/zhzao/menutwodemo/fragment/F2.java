package com.zhzao.menutwodemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.onetime.platform.R;
import com.zhzao.menutwodemo.adapter.TalkAdapter;
import com.zhzao.menutwodemo.entity.TalkBean;
import com.zhzao.menutwodemo.presenter.TalkPresenter;
import com.zhzao.menutwodemo.view.TalkView;
import com.zzhao.utils.Base.BaseFragment;
import com.zzhao.utils.utils.ToastShow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by 张乔君 on 2017/11/25.
 */

public class F2 extends BaseFragment implements TalkView, XRecyclerView.LoadingListener {
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.f1_wait)
    GifImageView f1Wait;
    Unbinder unbinder;

    private TalkPresenter presenter;
    private int page = 1;
    private TalkAdapter ada;
    private List<TalkBean.DataBean> data;

    @Override
    protected void initView() {
        presenter = new TalkPresenter(this);
        presenter.getMsg(page + "");//请求数据
    }

    @Override
    protected void initData() {
        xRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        xRecyclerView.setLoadingListener(this);

        GifDrawable gifDrawable = null;
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.dd);
            f1Wait.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void click(View v) {
    }

    @Override
    protected int setLayoutView() {
        return R.layout.f1_sub;
    }

    @Override
    public void success(String msg) {
        //返回成功
        Gson gson = new Gson();
        System.out.println("xxxxxxxresponse" + msg);
        TalkBean bean = gson.fromJson(msg, TalkBean.class);
        data = bean.getData();
        if (ada == null) {
            ada = new TalkAdapter(getActivity(), (ArrayList<TalkBean.DataBean>) data);
            xRecyclerView.setAdapter(ada);
        } else {
            ada.addAll((ArrayList<TalkBean.DataBean>) data);
            ada.notifyDataSetChanged();//刷新
        }

    }

    @Override
    public void failure(String msg) {
        ToastShow.showToast(getActivity(), msg);
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
    public void onRefresh() {
        if (ada != null) {
            ada = null;
        }
        if (data.size() > 0) {
            data.clear();
        }
        page = 0;
        presenter.getMsg(page + "");
        xRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getMsg(page + "");
        xRecyclerView.loadMoreComplete();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.disPatch();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
