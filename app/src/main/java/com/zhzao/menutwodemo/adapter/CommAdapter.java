package com.zhzao.menutwodemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.onetime.platform.R;
import com.zhzao.menutwodemo.entity.VideoBean;

import java.util.ArrayList;

/**
 * Created by 张乔君 on 2017/12/19.
 */

public class CommAdapter extends BaseAdapter {
    private ArrayList<VideoBean.DataBean.CommentsBean> list;
    private Context context;

    public CommAdapter(ArrayList<VideoBean.DataBean.CommentsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context, R.layout.comment_item,null);
        TextView name=v.findViewById(R.id.comm_name);
        TextView content=v.findViewById(R.id.comm_content);
        name.setText(list.get(position).getNickname());
        content.setText(list.get(position).getContent());
        return v;
    }
}
