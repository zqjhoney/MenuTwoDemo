package com.zhzao.menutwodemo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.entity.TalkBean;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.TalkHolder> {
    private Context context;
    private ArrayList<TalkBean.DataBean> list;

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
    public void onBindViewHolder(TalkHolder holder, int position) {
        holder.name.setText(list.get(position).getUser().getNickname());
        holder.time.setText(list.get(position).getCreateTime());
        holder.content.setText(list.get(position).getContent());

        Glide.with(context).load(list.get(position).getUser().getIcon()).into(holder.ic);
        String imgUrls = list.get(position).getImgUrls();
//        holder.open.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!flag){
//                    holder.p1.setVisibility(View.VISIBLE);
//                    holder.p2.setVisibility(View.VISIBLE);
//                    holder.p3.setVisibility(View.VISIBLE);
//                    holder.open.animate().rotation(360).setDuration(200).start();
//                    holder.open.setImageResource(R.drawable.icon_packup);
//                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat( holder.p1, "translationX", 0,-80);
//                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat( holder.p2, "translationX", 0,-170);
//                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat( holder.p3, "translationX", 0,-260);
//
//                    AnimatorSet animatorSet = new AnimatorSet();
//                    animatorSet.setDuration(200);
//                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
//                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
//                    animatorSet.start();
//                    flag=true;
//                }else{
//                    flag=false;
//                    holder.open.animate().rotation(0).setDuration(200).start();
//                    holder.open.setImageResource(R.drawable.icon_open);
//                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.p1, "translationX",-80,0);
//                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.p2, "translationX", -170,0);
//                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat(holder.p3, "translationX",-260,0);
//                    AnimatorSet animatorSet = new AnimatorSet();
//                    animatorSet.setDuration(200);
//                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
//                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
//                    animatorSet.start();
//                    animatorSet.addListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            super.onAnimationEnd(animation);
//                            holder.p1.setVisibility(View.GONE);
//                            holder.p2.setVisibility(View.GONE);
//                            holder.p3.setVisibility(View.GONE);
//                        }
//                    });
//
//                }
//            }
//        });
        if(imgUrls==null){
        }else{
            String[] arr=imgUrls.split("\\|");
            if(arr.length==4){
                holder.gv_recyclerview.setLayoutManager(new GridLayoutManager(context,2));
            }else if(arr.length==2){
                holder.gv_recyclerview.setLayoutManager(new GridLayoutManager(context,2));
            }else {
                holder.gv_recyclerview.setLayoutManager(new GridLayoutManager(context,3));
            }
            holder.gv_recyclerview.setAdapter(new Talk_subAdapter(context,arr));

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TalkHolder extends RecyclerView.ViewHolder{

        TextView content;
        TextView name;
        TextView time;
        CircleImageView ic;
        ImageView show;
        RecyclerView gv_recyclerview;
        public TalkHolder(View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.talk_content);
            name=itemView.findViewById(R.id.talk_name);
            time=itemView.findViewById(R.id.talk_time);
            ic=itemView.findViewById(R.id.talk_ic);
            show=itemView.findViewById(R.id.show_ic);
            gv_recyclerview=itemView.findViewById(R.id.gv_recyclerview);
        }
    }
}
