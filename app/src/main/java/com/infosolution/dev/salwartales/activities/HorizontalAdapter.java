package com.infosolution.dev.salwartales.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infosolution.dev.salwartales.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by amit on 1/11/2018.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


    List<Dataa> horizontalList = Collections.emptyList();
    Context context;


    public HorizontalAdapter(List<Dataa> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvname,tvvalue;
        public MyViewHolder(View view) {
            super(view);
            imageView=(ImageView) view.findViewById(R.id.iv_itemimage);
            tvname=(TextView) view.findViewById(R.id.tv_itemname);
            tvvalue=(TextView) view.findViewById(R.id.tv_itemvalue);
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.featuredproduct_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.imageView.setImageResource(horizontalList.get(position).image);
        holder.tvname.setText(horizontalList.get(position).name);
        holder.tvvalue.setText(horizontalList.get(position).value);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String list = horizontalList.get(position).name.toString();
            }

        });

    }


    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}
