package com.zzhao.utils.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张乔君 on 2017/11/29.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;

    /**
     * 根view
     */
    protected View mRootView;
    private Unbinder bind;


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        mActivity = getActivity();
    }
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        if(mRootView==null){
            mRootView = View.inflate(mActivity,setLayoutView(),null);
                   // inflater.inflate(setLayoutView(), container, false);
        }
        bind = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(getArguments());//有创参数的时候
        initView();
        initData();

    }
    //可选择实现的方法
    /**
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected  void initData(Bundle arguments){};
    /**
     * 初始化View
     */
    protected abstract   void initView();
    protected abstract   void initData();
    @Override
    public void onClick(View v) {
        click(v);
    }
    /**
     * 设置监听事件
     */
    protected abstract void click(View v);
    protected abstract int setLayoutView();
    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
