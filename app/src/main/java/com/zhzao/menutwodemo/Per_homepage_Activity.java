package com.zhzao.menutwodemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.onetime.platform.R;
import com.zhzao.menutwodemo.adapter.Copy1Adapter;
import com.zhzao.menutwodemo.entity.UserLie;
import com.zhzao.menutwodemo.presenter.UserPresenter;
import com.zhzao.menutwodemo.view.UserView;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.utils.ToastShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Per_homepage_Activity extends BaseActivity implements UserView{


    @BindView(R.id.p_ic)
    CircleImageView pIc;//个人的头像
    @BindView(R.id.fan_num)
    TextView fanNum;//粉丝数量
    @BindView(R.id.attention_num)
    TextView attentionNum;//关注数量
    @BindView(R.id.pre_atten)
    TextView preAtten;//关注
    @BindView(R.id.ok_atten)
    TextView okAtten;//已关注
    @BindView(R.id.per_home_lv)
    ListView perLv;//列表
    @BindView(R.id.rl_atten)
    RelativeLayout rlAtten;
    @BindView(R.id.send_msg)
    ImageView send_msg;

    private int page=1;
    private UserPresenter presenter;

    @Override
    public Boolean isFull() {
        return false;
    }
    @Override
    public int getLayout() {
        return R.layout.activity_per_homepage;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        String uuid = getIntent().getStringExtra("uuid");
        rlAtten.setOnClickListener(this);
        presenter = new UserPresenter(this);
        presenter.getMsg(uuid,page+"");
        send_msg.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }
    @Override
    public void success(UserLie userLie) {
        List<UserLie.DataBean> list=userLie.getData();
        Copy1Adapter ada=new Copy1Adapter((ArrayList<UserLie.DataBean>) list,this);
        perLv.setAdapter(ada);

    }

    @Override
    public void setClick(View view) {
        switch (view.getId()){
            case R.id.rl_atten:

                break;
            case R.id.send_msg:

                break;

        }

    }

    @Override
    public void success(String msg) {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toast(String msg) {
        ToastShow.showToast(this,msg);
    }



}
