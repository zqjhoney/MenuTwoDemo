package com.zhzao.menutwodemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onetime.platform.R;
import com.zhzao.menutwodemo.Photo_shopActivity;
import com.zhzao.menutwodemo.entity.TalkBean;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.TalkHolder> {
    private Context context;
    private ArrayList<TalkBean.DataBean> list;
    private Talk_subAdapter ada;

    public TalkAdapter(Context context,ArrayList<TalkBean.DataBean> list) {
        this.context = context;
        this.list=list;
    }

    public void addAll(ArrayList<TalkBean.DataBean> li){
        if(list!=null && list.size()>0){
            list.addAll(li);
        }
    }

    @Override
    public TalkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.f2_item,null);
        return new TalkHolder(view);
    }

    @Override
    public void onBindViewHolder(final TalkHolder holder, final int position) {
        holder.name.setText(list.get(position).getUser().getNickname());
        holder.time.setText(list.get(position).getCreateTime());
        holder.content.setText(list.get(position).getContent());

         if(list.get(position).isShow()){
        holder.p1.setVisibility(View.VISIBLE);
        holder.p2.setVisibility(View.VISIBLE);
        holder.p3.setVisibility(View.VISIBLE);
        holder.view.setImageResource(R.drawable.icon_packup);
        ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat( holder.p1, "translationX", 0,-95);
        ObjectAnimator animatorEdit = ObjectAnimator.ofFloat( holder.p2, "translationX", 0,-185);
        ObjectAnimator animatorSend = ObjectAnimator.ofFloat( holder.p3, "translationX", 0,-295);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(20);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
        animatorSet.start();
    }else{
        holder.p1.setVisibility(View.GONE);
        holder.p2.setVisibility(View.GONE);
        holder.p3.setVisibility(View.GONE);
        holder.view.setImageResource(R.drawable.icon_open);
    }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!list.get(position).isShow()){
                    list.get(position).setShow(true);
                    holder.p1.setVisibility(View.VISIBLE);
                    holder.p2.setVisibility(View.VISIBLE);
                    holder.p3.setVisibility(View.VISIBLE);
                    holder.view.animate().rotation(360).setDuration(200).start();
                    holder.view.setImageResource(R.drawable.icon_packup);
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat( holder.p1, "translationX", 0,-95);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat( holder.p2, "translationX", 0,-185);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat( holder.p3, "translationX", 0,-295);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(200);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();

                }else{
                    list.get(position).setShow(false);
                    holder.view.animate().rotation(0).setDuration(200).start();
                    holder.view.setImageResource(R.drawable.icon_open);
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.p1, "translationX",-95,0);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.p2, "translationX", -185,0);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat(holder.p3, "translationX",-295,0);
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


//视频
        Glide.with(context).load(list.get(position).getUser().getIcon()).into(holder.ic);
        String imgUrls = list.get(position).getImgUrls();


        if(imgUrls==null){
        }else{
            final String[] arr=imgUrls.split("\\|");
            if(arr.length==4){
                holder.gv_recyclerview.setLayoutManager(new GridLayoutManager(context,2));
            }else if(arr.length==2){
                holder.gv_recyclerview.setLayoutManager(new GridLayoutManager(context,2));
            }else if(arr.length==1){
                holder.gv_recyclerview.setLayoutManager(new GridLayoutManager(context,1));
            } else {
                holder.gv_recyclerview.setLayoutManager(new GridLayoutManager(context,3));
            }
            ada = new Talk_subAdapter(context, arr);
            holder.gv_recyclerview.setAdapter(ada);
            ada.setOnItemClickListener(new Talk_subAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v,int pos) {
                    //跳转到展示图片页面
                    Intent in=new Intent(context, Photo_shopActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("pos",pos);
                    bundle.putSerializable("arr",arr);
                    in.putExtras(bundle);
                    context.startActivity(in);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return list.size()>0?list.size():0;
    }


    class TalkHolder extends RecyclerView.ViewHolder{

        TextView content;
        TextView name;
        TextView time;
        CircleImageView ic;
        RecyclerView gv_recyclerview;

        RelativeLayout p1;
        RelativeLayout p2;
        RelativeLayout p3;
        ImageView view;
        public TalkHolder(View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.talk_content);
            name=itemView.findViewById(R.id.talk_name);
            time=itemView.findViewById(R.id.talk_time);
            ic=itemView.findViewById(R.id.talk_ic);
            gv_recyclerview=itemView.findViewById(R.id.gv_recyclerview);

            p1=itemView.findViewById(R.id.photo11);
            p2=itemView.findViewById(R.id.photo21);
            p3=itemView.findViewById(R.id.photo31);

            view=itemView.findViewById(R.id.host_open1);



        }
    }
}
