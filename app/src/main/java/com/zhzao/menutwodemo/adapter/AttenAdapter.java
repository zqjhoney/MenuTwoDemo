package com.zhzao.menutwodemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onetime.platform.R;
import com.zhzao.menutwodemo.entity.AttenLie;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 张乔君 on 2017/12/17.
 */

public class AttenAdapter extends BaseAdapter {
    private ArrayList<AttenLie.DataBean> list;
    private Context context;

    public AttenAdapter( ArrayList<AttenLie.DataBean> list, Context context) {
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
        View view=View.inflate(context, R.layout.attenitem,null);
        CircleImageView ic=view.findViewById(R.id.atten_ic);
        TextView name=view.findViewById(R.id.atten_name);
        TextView content=view.findViewById(R.id.atten_content);
        TextView time=view.findViewById(R.id.atten_time);

        Glide.with(context).load(list.get(position).getIcon()).into(ic);
        name.setText(list.get(position).getNickname());
        content.setText(list.get(position).getAppsecret());
        time.setText(list.get(position).getCreatetime());

        return view;
    }
}
