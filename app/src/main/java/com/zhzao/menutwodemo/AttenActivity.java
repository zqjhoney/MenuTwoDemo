package com.zhzao.menutwodemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.onetime.platform.R;
import com.zhzao.menutwodemo.adapter.AttenAdapter;
import com.zhzao.menutwodemo.entity.AttenLie;
import com.zhzao.menutwodemo.entity.VideoBean;
import com.zhzao.menutwodemo.presenter.ShowVideoPresenter;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.ShowVideoView;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.utils.ToastShow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttenActivity extends BaseActivity implements ShowVideoView {

    @BindView(R.id.atten_lv)
    ListView attenLv;
    private ShowVideoPresenter presenter;
    private List<AttenLie.DataBean> data;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_atten;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        presenter = new ShowVideoPresenter(this);
        String uid = SharePreUtils.getShareprefervalue("uid");
        presenter.getAtten(uid);
        attenLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String uid=data.get(position).getUid()+"";
                Intent in=new Intent(AttenActivity.this,Per_homepage_Activity.class);
                in.putExtra("uuid",uid);
                startActivity(in);

            }
        });


    }

    @Override
    public void initdata() {

    }

    @Override
    public void setClick(View view) {

    }

    @Override
    public void success(String msg) {
        try {
            JSONObject oo=new JSONObject(msg);
            String code=oo.getString("code");
            String m=oo.getString("msg");
            if("0".equals(code)){
                Gson gson=new Gson();
                AttenLie attenLie = gson.fromJson(msg, AttenLie.class);
                data = attenLie.getData();
                AttenAdapter adapter=new AttenAdapter((ArrayList<AttenLie.DataBean>) data,AttenActivity.this);
                attenLv.setAdapter(adapter);

            }else{
                toast(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void success(VideoBean msg) {

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
