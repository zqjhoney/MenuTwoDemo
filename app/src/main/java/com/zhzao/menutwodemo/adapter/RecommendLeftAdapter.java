package com.zhzao.menutwodemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhzao.menutwodemo.R;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class RecommendLeftAdapter extends RecyclerView.Adapter<RecommendLeftAdapter.MyHolder> {

    private Context context;
    private Boolean flag=false;

    public RecommendLeftAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_one,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag){
                    holder.p1.setVisibility(View.VISIBLE);
                    holder.p2.setVisibility(View.VISIBLE);
                    holder.p3.setVisibility(View.VISIBLE);
                    holder.open.animate().rotation(360).setDuration(200).start();
                    holder.open.setImageResource(R.drawable.icon_packup);
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat( holder.p1, "translationX", 0,-80);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat( holder.p2, "translationX", 0,-170);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat( holder.p3, "translationX", 0,-260);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(200);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();
                    flag=true;
                }else{
                    flag=false;
                    holder.open.animate().rotation(0).setDuration(200).start();
                    holder.open.setImageResource(R.drawable.icon_open);
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.p1, "translationX",-80,0);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.p2, "translationX", -170,0);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat(holder.p3, "translationX",-260,0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(200);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            holder.p1.setVisibility(View.GONE);
                            holder.p2.setVisibility(View.GONE);
                            holder.p3.setVisibility(View.GONE);
                        }
                    });

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }



    class MyHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView time;
        TextView title;
        ImageView open;
        RelativeLayout p1;
        RelativeLayout p2;
        RelativeLayout p3;

        public MyHolder(View itemView) {
            super(itemView);
            open=itemView.findViewById(R.id.host_open);
            name=itemView.findViewById(R.id.host_name);
            title=itemView.findViewById(R.id.host_title);
            time=itemView.findViewById(R.id.host_time);

            p1=itemView.findViewById(R.id.photo1);
            p2=itemView.findViewById(R.id.photo2);
            p3=itemView.findViewById(R.id.photo3);
        }
    }
}
