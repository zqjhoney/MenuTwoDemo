package com.zhzao.menutwodemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 张乔君 on 2017/11/30.
 */

public class FlterAdapter extends RecyclerView.Adapter<FlterAdapter.FilterHolder> {
    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FilterHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class FilterHolder extends RecyclerView.ViewHolder {
        public FilterHolder(View itemView) {
            super(itemView);
        }
    }
}
