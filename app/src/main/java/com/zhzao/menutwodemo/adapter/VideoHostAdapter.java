package com.zhzao.menutwodemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.onetime.platform.R;
import com.zhzao.menutwodemo.Video_Host_DesActivity;
import com.zhzao.menutwodemo.entity.VideoHost;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 张乔君 on 2017/12/10.
 */

public class VideoHostAdapter extends RecyclerView.Adapter<VideoHostAdapter.MyHostHolder> {
    private ArrayList<VideoHost.DataBean> list;
    private Context context;
    private int hight;
    private int i;


    //生成300-700的随机值
    public int getRandomHeight(){
        hight= (int)(300+Math.random()*400);
        return hight;
    }

    public VideoHostAdapter(ArrayList<VideoHost.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.f3_sub_item,null);
        return new MyHostHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHostHolder holder, final int position) {
        //随机值
        ViewGroup.LayoutParams params=holder.iv.getLayoutParams();
        params.height=getRandomHeight();
        holder.itemView.setLayoutParams(params);

       // holder.player.setUp(list.get(position).getVideoUrl()," ");
        Glide.with(context).load(list.get(position).getCover()).into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  i= holder.getLayoutPosition();
//                onItemClickListener.onItemCllick(v,i);
                Intent in=new Intent(context, Video_Host_DesActivity.class);
                VideoHost.DataBean bean = list.get(position);
                in.putExtra("videoUrl",bean.getVideoUrl());
                in.putExtra("img",list.get(position).getCover());
                context.startActivity(in);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size()>0?list.size():0;
    }

    class MyHostHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public MyHostHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.f3_iv);
        }
    }

//    private OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public  interface OnItemClickListener{
//        void onItemCllick(View view,int pos);
// }
}
