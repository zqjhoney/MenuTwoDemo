package com.zhzao.menutwodemo.entity;

import android.graphics.PorterDuff;

/**
 * Created by 张乔君 on 2017/11/30.
 */

public class FilterBean {
    private int img;
    private String title;

    public FilterBean(int img, String title) {
        this.img = img;
        this.title = title;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
