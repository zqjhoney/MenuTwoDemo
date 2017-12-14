package com.zhzao.menutwodemo;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class BottomActivity extends AppCompatActivity {

    private int fistposition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        BottomNavigationBar bar=findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBar bar2=findViewById(R.id.bottom_navigation_bar2);
        BottomNavigationBar bar3=findViewById(R.id.bottom_navigation_bar3);

        bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bar.addItem(new BottomNavigationItem(R.drawable.ic_empty, "推荐")
                .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.ic_error)))
                .setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.ic_empty, "段子")
                        .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_error)))
                .addItem(new BottomNavigationItem(R.drawable.ic_empty, "视频")
                        .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_error)))
                .initialise();
        bar3.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bar3.addItem(new BottomNavigationItem(R.drawable.ic_empty, "推荐")
                .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.ic_error)))
                .setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.ic_empty, "段子")
                        .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_error)))
                .addItem(new BottomNavigationItem(R.drawable.ic_empty, "视频")
                        .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_error)))
                .initialise();
        bar2.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bar2.addItem(new BottomNavigationItem(R.drawable.ic_empty, "推荐")
                .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.ic_error)))
                .setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.ic_empty, "段子")
                        .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_error)))
                .addItem(new BottomNavigationItem(R.drawable.ic_empty, "视频")
                        .setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_error)))
                .initialise();

    }
}
