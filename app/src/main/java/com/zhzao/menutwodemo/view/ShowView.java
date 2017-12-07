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
    private boolean isShow=false;

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
        initview();
    }

    private void initview() {
        iv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(isShow){//要关闭
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
            isShow=false;
        }else{//要展开
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
            isShow=true;
        }


        }

}
