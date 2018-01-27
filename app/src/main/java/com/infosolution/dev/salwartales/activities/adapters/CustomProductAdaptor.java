package com.infosolution.dev.salwartales.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.ProductDetailsActivity;
import com.infosolution.dev.salwartales.activities.model.CustomProductModel;
import com.infosolution.dev.salwartales.activities.model.DataBest;

import java.util.ArrayList;

/**
 * Created by Shreyansh srivastava on 1/12/2018.
 */

public class CustomProductAdaptor extends RecyclerView.Adapter<CustomProductAdaptor.ProductHolder> {


    public CustomProductAdaptor(ArrayList<CustomProductModel> customProductModelArrayList, Context context, Activity activity) {
        this.customProductModelArrayList = customProductModelArrayList;
        this.context = context;
        this.activity = activity;
    }

    private ArrayList<CustomProductModel> customProductModelArrayList;
    Context context;
    private Activity activity;


    @Override
    public CustomProductAdaptor.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.product_grid_layout, parent, false);
        return new ProductHolder(itemView, context, customProductModelArrayList);
    }

    @Override
    public void onBindViewHolder(CustomProductAdaptor.ProductHolder holder, int position) {

        final CustomProductModel CustomProductModel= customProductModelArrayList.get(position);

        Glide.with(activity).load(CustomProductModel.getImage()).into(holder.ivProImage);
        holder.tvProName.setText(customProductModelArrayList.get(position).getName());
        holder.tvProValues.setText(customProductModelArrayList.get(position).getValue());
      holder.favv.setImageResource(customProductModelArrayList.get(position).getFavimage());
        holder.tvfavstatus.setText(customProductModelArrayList.get(position).getFavStatus());
        holder.tvqtyleft.setText(customProductModelArrayList.get(position).getQtyLeft());
        holder.tvproid.setText(customProductModelArrayList.get(position).getProId());



    }

    @Override
    public int getItemCount() {
        if (customProductModelArrayList == null)
            return 0;
        return customProductModelArrayList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {


        TextView tvProName,tvProValues,tvfavstatus,tvproid,tvqtyleft;
        ImageView ivProImage,favv;

        public ProductHolder(View itemView, Context context, final ArrayList<CustomProductModel> customProductModelArrayList) {
            super(itemView);

            tvProName = (TextView) itemView.findViewById(R.id.tv_proname);
            tvProValues = (TextView) itemView.findViewById(R.id.tv_provalue);
            ivProImage = (ImageView) itemView.findViewById(R.id.iv_proimage);
            tvfavstatus=(TextView) itemView.findViewById(R.id.favstatusss);
            tvproid=(TextView) itemView.findViewById(R.id.proidd);
            tvqtyleft=(TextView) itemView.findViewById(R.id.qtyyy);
            favv=(ImageView) itemView.findViewById(R.id.favv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Intent intent= new Intent(activity, ProductDetailsActivity.class);
                    intent.putExtra("Proid",customProductModelArrayList.get(position).getProId());
                    activity.startActivity(intent);
                }
            });

        }
    }
}

