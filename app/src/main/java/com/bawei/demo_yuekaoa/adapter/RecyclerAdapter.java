package com.bawei.demo_yuekaoa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.demo_yuekaoa.R;
import com.bawei.demo_yuekaoa.bean.RowsBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 作    者：云凯文
 * 时    间：2017/4/4
 * 描    述：
 * 修改时间：
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<RowsBean> rowsList = new ArrayList<>();

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addrest_down(List<RowsBean> rowsList) {
        this.rowsList.clear();//先清空集合
        this.rowsList.addAll(rowsList);//给集合添加新的数据
        this.notifyDataSetChanged();//刷新界面
    }

    public void addrest_up(List<RowsBean> rowsList) {
        this.rowsList.addAll(rowsList);//给集合添加新的数据
        this.notifyDataSetChanged();//刷新界面
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(rowsList.get(position).getInfo().getDefault_image()).into(holder.iv_iamge);
        holder.tv_loupan_name.setText(rowsList.get(position).getInfo().getLoupan_name());
        holder.tv_region_title.setText(rowsList.get(position).getInfo().getRegion_title() + " - " + rowsList.get(position).getInfo().getSub_region_title());
        holder.tv_tvprice_value.setText(rowsList.get(position).getInfo().getNew_price_value() + rowsList.get(position).getInfo().getNew_price_back());
        holder.tv_tags.setText(rowsList.get(position).getInfo().getTags());
        holder.tv_sale_title.setText(rowsList.get(position).getInfo().getSale_title());
    }

    @Override
    public int getItemCount() {
        return rowsList == null ? 0 : rowsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_iamge;
        private TextView tv_loupan_name;
        private TextView tv_region_title;
        private TextView tv_tvprice_value;
        private TextView tv_tags;
        private TextView tv_sale_title;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_iamge = (ImageView) itemView.findViewById(R.id.iv_iamge);
            tv_loupan_name = (TextView) itemView.findViewById(R.id.tv_loupan_name);
            tv_region_title = (TextView) itemView.findViewById(R.id.tv_region_title);
            tv_tvprice_value = (TextView) itemView.findViewById(R.id.tv_tvprice_value);
            tv_tags = (TextView) itemView.findViewById(R.id.tv_tags);
            tv_sale_title = (TextView) itemView.findViewById(R.id.tv_sale_title);
        }
    }
}
