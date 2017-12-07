package com.zhzao.menutwodemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.entity.VideoBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyHolder>{

    private PlayerView play;
    private Context context;
    private Boolean flag=false;
    private List<VideoBean.DataBean> list;

    public RecommendAdapter(Context context, List<VideoBean.DataBean> list) {
        this.context = context;
        this.list=list;
    }
    public  void addAll( List<VideoBean.DataBean> li){
        list.addAll(li);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.f1_item,null);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.name.setText(list.get(position).getUser().getNickname());
        holder.time.setText(list.get(position).getCreateTime());
        holder.title.setText(list.get(position).getWorkDesc());
        Glide.with(context).load(list.get(position).getUser().getIcon()).into(holder.host_ic);
        StringBuffer bf=new StringBuffer(list.get(position).getVideoUrl());
        String substring = bf.substring(22);
        String videourl = Api.HTTP + substring;
        System.out.println("xxxxxxxxxxxxxxxxxx"+videourl);

        new PlayerView((Activity) context,holder.itemView)
                 .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(context)
                                .load(list.get(position).getCover())
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(videourl)
                .startPlay();
        //      play.stopPlay();


        holder.xh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.xh.isSelected()){
                    holder.xh.setSelected(false);
                    //TODO 取消喜欢
                }else{
                    holder.xh.setSelected(true);
                }
            }
        });
        holder.sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.sc.isSelected()){
                    holder.sc.setSelected(false);
                    //TODO 取消喜欢
                }else{
                    holder.sc.setSelected(true);
                }
            }
        });
        holder.fx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.fx.isSelected()){
                    holder.fx.setSelected(false);
                    //TODO 取消喜欢
                }else{
                    holder.fx.setSelected(true);
                }
            }
        });
        holder.pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.pl.isSelected()){
                    holder.pl.setSelected(false);
                    //TODO 取消喜欢
                }else{
                    holder.pl.setSelected(true);
                }
            }
        });

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
        return list.size();
        // return list.size()>0?list.size():0;
    }
    public  void onDetach(){
        play.stopPlay();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView time;
        TextView title;
        ImageView open;
        RelativeLayout p1;
        RelativeLayout p2;
        RelativeLayout p3;
        CircleImageView host_ic;
        ImageView xh;
        ImageView sc;
        ImageView fx;
        ImageView pl;
        TextView xh1;
        TextView sc1;
        TextView fx1;
        TextView pl1;

        public MyHolder(View itemView) {
            super(itemView);
            open=itemView.findViewById(R.id.host_open);
            name=itemView.findViewById(R.id.host_name);
            title=itemView.findViewById(R.id.host_title);
            time=itemView.findViewById(R.id.host_time);
            host_ic=itemView.findViewById(R.id.host_ic);
            p1=itemView.findViewById(R.id.photo1);
            p2=itemView.findViewById(R.id.photo2);
            p3=itemView.findViewById(R.id.photo3);
            xh=itemView.findViewById(R.id.attention_iv);
            xh1=itemView.findViewById(R.id.attention_tv);

            sc=itemView.findViewById(R.id.collect_iv);
            sc1=itemView.findViewById(R.id.collect_tv);

            fx=itemView.findViewById(R.id.collect_iv);
            fx1=itemView.findViewById(R.id.collect_tv);

            pl=itemView.findViewById(R.id.share_iv);
            pl1=itemView.findViewById(R.id.share_tv);

//            Log.i("xxx",getPosition()+"");
//            itemView.setTag(list.get(getPosition()));

        }
    }
}
