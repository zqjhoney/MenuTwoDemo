package com.zhzao.menutwodemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onetime.platform.R;

/**
 * Created by 张乔君 on 2017/11/30.
 */

public class Talk_subAdapter extends RecyclerView.Adapter<Talk_subAdapter.SubHolder> {
    private String[] list;
    private Context context;

    public Talk_subAdapter(Context context,String[] list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public SubHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.talk_photo,null);
        return new SubHolder(view);
    }

    @Override
    public void onBindViewHolder(SubHolder holder, final int position) {
        RequestOptions option = new RequestOptions().placeholder(R.drawable.ic_empty).error(R.drawable.ic_error);
        Glide.with(context).load(list[position]).apply(option).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
            }
        });


    }
    @Override
    public int getItemCount() {
        return list.length;
    }

    class SubHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public SubHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.other_photo);
        }
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View v,int pos);
    }
}
