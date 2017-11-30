package com.zhzao.menutwodemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.adapter.TalkAdapter;
import com.zhzao.menutwodemo.entity.TalkBean;
import com.zhzao.menutwodemo.presenter.TalkPresenter;
import com.zhzao.menutwodemo.view.TalkView;
import com.zzhao.utils.Base.BaseFragment;
import com.zzhao.utils.utils.ToastShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张乔君 on 2017/11/25.
 */

public class F2 extends BaseFragment implements TalkView, XRecyclerView.LoadingListener {
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.f_bar)
    ProgressBar fBar;
    private TalkPresenter presenter;
    private int page = 1;
    private TalkAdapter ada;
    private List<TalkBean.DataBean> data;

    @Override
    protected void initView() {
        presenter = new TalkPresenter(this);
        presenter.getMsg(page+"");//请求数据
    }

    @Override
    protected void initData() {
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        xRecyclerView.setLoadingListener(this);
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
        Gson gson=new Gson();
        System.out.println("xxxxxxxresponse"+msg);
        TalkBean bean = gson.fromJson(msg, TalkBean.class);
        data = bean.getData();
        if(ada==null){
            ada = new TalkAdapter(getActivity(), (ArrayList<TalkBean.DataBean>) data);
            xRecyclerView.setAdapter(ada);
        }else{
            ada.addAll((ArrayList<TalkBean.DataBean>) data);
            ada.notifyDataSetChanged();//刷新
        }

    }

    @Override
    public void failure(String msg) {
        ToastShow.showToast(getActivity(),msg);
    }

    @Override
    public void showLoading() {
        fBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        fBar.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        if(ada!=null){
            ada=null;
        }
        if(data.size()>0){
            data.clear();
        }
        page=0;
        presenter.getMsg(page+"");
        xRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getMsg(page+"");
        xRecyclerView.loadMoreComplete();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.disPatch();
    }
}
