package com.zhzao.menutwodemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhzao.menutwodemo.entity.TalkBean;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.utils.ToastShow;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class Photo_shopActivity extends BaseActivity {


    @BindView(R.id.photot_vp)
    ViewPager phototVp;
    private  String[] list;
    private int pos;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_photo_shop;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        list = (String[])getIntent().getExtras().getSerializable("arr");
        pos = getIntent().getExtras().getInt("pos");
        if (list != null) {
            phototVp.setAdapter(new MyAdapter());
            phototVp.setCurrentItem(pos);
        }else{
            ToastShow.showToast(Photo_shopActivity.this,"没有传进来图片哟");
        }
    }

    @Override
    public void initdata() {

    }

    @Override
    public void setClick(View view) {

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

    public class MyAdapter extends PagerAdapter{

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View view=View.inflate(Photo_shopActivity.this,R.layout.photot,null);
            PhotoView pv=view.findViewById(R.id.pv);
            Glide.with(Photo_shopActivity.this).load(list[position]).into(pv);
            pv.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    Photo_shopActivity.this.finish();//单击关闭
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
          container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }


}
