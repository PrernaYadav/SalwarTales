package com.dev.salwartales.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.salwartales.R;
import com.dev.salwartales.activities.ProductDetailsActivity;
import com.dev.salwartales.activities.model.DataBest;

import java.util.ArrayList;

/**
 * Created by amit on 1/25/2018.
 */

public class HorizontalAdapterBestSeller extends RecyclerView.Adapter<HorizontalAdapterBestSeller.MyViewHolderr> {
    public HorizontalAdapterBestSeller(ArrayList<DataBest> dataBestArrayList, Context context, Activity activity) {
        this.dataBestArrayList = dataBestArrayList;
        this.context = context;
        this.activity = activity;
    }

    private ArrayList<DataBest> dataBestArrayList;
    Context context;
    private Activity activity;

    @Override
    public HorizontalAdapterBestSeller.MyViewHolderr onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.featuredproduct_row, parent, false);

        return new MyViewHolderr(itemView, context, dataBestArrayList);
    }

    @Override
    public void onBindViewHolder(HorizontalAdapterBestSeller.MyViewHolderr holder, int position) {


        final DataBest dataBest= dataBestArrayList.get(position);

        Glide.with(activity).load(dataBest.getImage()).into(holder.imageView);
        holder.tvname.setText(dataBestArrayList.get(position).name);
        holder.tvvalue.setText(dataBestArrayList.get(position).value);
        holder.tvfavstatus.setText(dataBestArrayList.get(position).getFavStatus());
        holder.tvqtyleft.setText(dataBestArrayList.get(position).getQtyLeft());
        holder.tvproidbs.setText(dataBestArrayList.get(position).getProid());
        holder.ivfav.setImageResource(dataBestArrayList.get(position).getFavimage());

    }

    @Override
    public int getItemCount() {
        if (dataBestArrayList == null)
            return 0;
        return dataBestArrayList.size();
    }

    public class MyViewHolderr extends RecyclerView.ViewHolder {

        ImageView imageView,ivfav;
        TextView tvname,tvvalue,tvfavstatus,tvproid,tvqtyleft,tvproidbs;

        public MyViewHolderr(View view, Context context, final ArrayList<DataBest> dataBestArrayList) {
            super(view);


            imageView=(ImageView) view.findViewById(R.id.iv_itemimage);
            ivfav=(ImageView) view.findViewById(R.id.ivfav);
            tvname=(TextView) view.findViewById(R.id.tv_itemname);
            tvvalue=(TextView) view.findViewById(R.id.tv_itemvalue);
            tvfavstatus=(TextView) view.findViewById(R.id.favstatus);
            tvproid=(TextView) view.findViewById(R.id.proid);
            tvqtyleft=(TextView) view.findViewById(R.id.qtyleft);
            tvproidbs=(TextView) view.findViewById(R.id.horiproid);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Intent intent= new Intent(activity, ProductDetailsActivity.class);
                    intent.putExtra("bsProid",dataBestArrayList.get(position).getProid());
                    activity.startActivity(intent);
                }
            });
        }
    }
}
