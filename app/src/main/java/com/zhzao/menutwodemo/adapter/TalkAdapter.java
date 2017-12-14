package com.zhzao.menutwodemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhzao.menutwodemo.Photo_shopActivity;
import com.zhzao.menutwodemo.R;
import com.zhzao.menutwodemo.entity.TalkBean;
import com.zhzao.menutwodemo.view.ShowView;

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
    public void onBindViewHolder(TalkHolder holder, final int position) {
        holder.name.setText(list.get(position).getUser().getNickname());
        holder.time.setText(list.get(position).getCreateTime());
        holder.content.setText(list.get(position).getContent());

        if(list.get(position).isShow()){
            holder.showview.shut();
        }
//        else{
//            holder.showview.open();
//        }
        holder.showview.setOnClickiv(new ShowView.OnClickiv() {
            @Override
            public void onitem1() {
                //评价
            }

            @Override
            public void onitem2() {
                //分享
            }

            @Override
            public void onitem3() {
                    //喜欢
            }

            @Override
            public boolean changeBoolean() {
                if(list.get(position).isShow()){
                   list.get(position).setShow(false);
                   return false;
                }else{
                    list.get(position).setShow(true);
                    return  true;
                }
            }
        });

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
        ShowView showview;
        RecyclerView gv_recyclerview;
        public TalkHolder(View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.talk_content);
            name=itemView.findViewById(R.id.talk_name);
            time=itemView.findViewById(R.id.talk_time);
            ic=itemView.findViewById(R.id.talk_ic);
            showview=itemView.findViewById(R.id.showview);
            gv_recyclerview=itemView.findViewById(R.id.gv_recyclerview);
        }
    }
}
