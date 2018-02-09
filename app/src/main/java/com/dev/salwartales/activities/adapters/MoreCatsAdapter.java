package com.dev.salwartales.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.salwartales.R;

import com.dev.salwartales.activities.ProFromCatListActivity;
import com.dev.salwartales.activities.model.MoreCatModel;

import java.util.ArrayList;

/**
 * Created by amit on 2/9/2018.
 */

public class MoreCatsAdapter extends RecyclerView.Adapter<MoreCatsAdapter.MoreCatHolder> {
    private ArrayList<MoreCatModel> moreCatModelArrayList;
    Context context;
    private Activity activityy;

    public MoreCatsAdapter(ArrayList<MoreCatModel> moreCatModelArrayList, Context context, Activity activityy) {
        this.moreCatModelArrayList = moreCatModelArrayList;
        this.context = context;
        this.activityy = activityy;
    }


    @Override
    public MoreCatsAdapter.MoreCatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.morecat_row, parent, false);
        return new MoreCatHolder(itemView,context,moreCatModelArrayList);
    }

    @Override
    public void onBindViewHolder(MoreCatsAdapter.MoreCatHolder holder, int position) {
        final MoreCatModel CustomProductModel= moreCatModelArrayList.get(position);
        holder.tvcat.setText(moreCatModelArrayList.get(position).getCatName());
        holder.tvcatid.setText(moreCatModelArrayList.get(position).getCatId());


    }

    @Override
    public int getItemCount() {
        if (moreCatModelArrayList == null)
            return 0;
        return moreCatModelArrayList.size();
    }
    public class MoreCatHolder extends RecyclerView.ViewHolder{

        TextView tvcat,tvcatid;

        public MoreCatHolder(View itemView, Context context, final ArrayList<MoreCatModel> moreCatModelArrayList) {
            super(itemView);

            tvcat=itemView.findViewById(R.id.tv_morecat);
            tvcatid=itemView.findViewById(R.id.tv_morecatId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int Pos=getAdapterPosition();
                    Intent intent = new Intent(activityy, ProFromCatListActivity.class);
                    intent.putExtra("ID",moreCatModelArrayList.get(Pos).getCatId());
                    activityy.startActivity(intent);
                }
            });

        }
    }
}
