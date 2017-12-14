package com.zhzao.menutwodemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhzao.menutwodemo.R;


/**
 * Created by 张乔君 on 2017/12/7.
 */

public class ShowView extends LinearLayout implements View.OnClickListener {

    private RelativeLayout r1;
    private RelativeLayout r2;
    private RelativeLayout r3;
    private ImageView iv;
    private boolean isShow;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public ShowView(Context context) {
        this(context,null);
    }

    public ShowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view= View.inflate(context,R.layout.view,this);
        r1 = view.findViewById(R.id.view1);
        r2 = view.findViewById(R.id.view2);
        r3 = view.findViewById(R.id.view3);

        iv = view.findViewById(R.id.view);
        iv1 = view.findViewById(R.id.msm_iv);
        iv2 = view.findViewById(R.id.show_iv);
        iv3 = view.findViewById(R.id.like_iv);
        initview();
    }

    private void initview() {
        iv.setOnClickListener(this);
        iv1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickiv.onitem1();
            }
        });
        iv2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickiv.onitem2();
            }
        });
        iv3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickiv.onitem3();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(isShow){//要关闭
          open();
        }else{//要展开
          shut();
        }
        isShow=onClickiv.changeBoolean();
        }

    public void shut() {
        r1.setVisibility(View.VISIBLE);
        r2.setVisibility(View.VISIBLE);
        r3.setVisibility(View.VISIBLE);
        iv.animate().rotation(360).setDuration(200).start();
        iv.setImageResource(R.drawable.icon_packup);
        ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat( r1, "translationX", 0,-80);
        ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(r2, "translationX", 0,-170);
        ObjectAnimator animatorSend = ObjectAnimator.ofFloat( r3, "translationX", 0,-260);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
        animatorSet.start();
    }

    public void open() {
        iv.animate().rotation(0).setDuration(200).start();
        iv.setImageResource(R.drawable.icon_open);
        ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(r1, "translationX",-80,0);
        ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(r2, "translationX", -170,0);
        ObjectAnimator animatorSend = ObjectAnimator.ofFloat(r3, "translationX",-260,0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                r1.setVisibility(View.GONE);
                r2.setVisibility(View.GONE);
                r3.setVisibility(View.GONE);
            }
        });
    }

    public void  setImageView1(int id1,int id2,int id3){
            iv1.setImageResource(id1);
            iv2.setImageResource(id2);
            iv3.setImageResource(id3);
    }

    private OnClickiv onClickiv;

    public void setOnClickiv(OnClickiv onClickiv) {
        this.onClickiv = onClickiv;
    }

    public interface  OnClickiv{
        void onitem1();
        void onitem2();
        void onitem3();
        boolean changeBoolean();

    }


}
