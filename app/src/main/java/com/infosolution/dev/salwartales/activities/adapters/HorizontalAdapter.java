package com.infosolution.dev.salwartales.activities.adapters;

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
import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.ProductDetailsActivity;
import com.infosolution.dev.salwartales.activities.model.Dataa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Shreyansh srivastava on 1/11/2018.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    public HorizontalAdapter(ArrayList<Dataa> dataaArrayList, Context context, Activity activity) {
        this.dataaArrayList = dataaArrayList;
        this.context = context;
        this.activity = activity;
    }

    private ArrayList<Dataa> dataaArrayList;
    Context context;
    private Activity activity;





    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,ivfav;
        TextView tvname,tvvalue,tvfavstatus,tvproid,tvqtyleft;
        public MyViewHolder(View view, Context context, ArrayList<Dataa> dataaArrayList) {
            super(view);
            imageView=(ImageView) view.findViewById(R.id.iv_itemimage);
            ivfav=(ImageView) view.findViewById(R.id.ivfav);
            tvname=(TextView) view.findViewById(R.id.tv_itemname);
            tvvalue=(TextView) view.findViewById(R.id.tv_itemvalue);
            tvfavstatus=(TextView) view.findViewById(R.id.favstatus);
            tvproid=(TextView) view.findViewById(R.id.proid);
            tvqtyleft=(TextView) view.findViewById(R.id.qtyleft);
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.featuredproduct_row, parent, false);

        return new MyViewHolder(itemView, context, dataaArrayList);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Dataa dataa= dataaArrayList.get(position);

        Glide.with(activity).load(dataa.getImage()).into(holder.imageView);
        holder.tvname.setText(dataaArrayList.get(position).name);
        holder.tvvalue.setText(dataaArrayList.get(position).value);
        holder.tvfavstatus.setText(dataaArrayList.get(position).getFavStatus());
        holder.tvqtyleft.setText(dataaArrayList.get(position).getQtyLeft());
        holder.ivfav.setImageResource(dataaArrayList.get(position).getFavimage());




    }


    @Override
    public int getItemCount() {

        if (dataaArrayList == null)
            return 0;
        return dataaArrayList.size();
    }
}
