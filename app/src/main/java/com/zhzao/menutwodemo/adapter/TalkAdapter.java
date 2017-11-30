package com.zhzao.menutwodemo.adapter;

import android.content.Context;
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
        View view=View.inflate(context, R.layout.item_two,null);
        return new TalkHolder(view);
    }

    @Override
    public void onBindViewHolder(TalkHolder holder, int position) {
        holder.name.setText(list.get(position).getUser().getNickname());
        holder.time.setText(list.get(position).getCreateTime());
        holder.content.setText(list.get(position).getContent());
        Glide.with(context).load(list.get(position).getUser().getIcon()).into(holder.ic);

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
        public TalkHolder(View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.talk_content);
            name=itemView.findViewById(R.id.talk_name);
            time=itemView.findViewById(R.id.talk_time);
            ic=itemView.findViewById(R.id.talk_ic);
        }
    }
}
