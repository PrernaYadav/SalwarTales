package com.infosolution.dev.salwartales.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.model.Cart;
import com.infosolution.dev.salwartales.activities.model.Dataa;

import java.util.Collections;
import java.util.List;

/**
 * Created by Shreyansh Srivastava on 1/16/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.proimage.setImageResource(cartList.get(position).getProimage());
        holder.proname.setText(cartList.get(position).getProname());
        holder.proprice.setText(cartList.get(position).getProprice());
    }

    @Override
    public int getItemCount() {
        return cartList.size();    }

    List<Cart> cartList= Collections.emptyList();
    Context context;

    public CartAdapter(List<Cart> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView proimage;
        TextView proname, proprice,proqty,tvminus,tvplus;
        ImageView ivremove;
       int count=1;
        int pri,price;

        public MyViewHolder(View view) {
            super(view);
            proname = (TextView) view.findViewById(R.id.cart_proname);
            proqty = (TextView) view.findViewById(R.id.tv_proqty);
            proprice = (TextView) view.findViewById(R.id.cart_proprice);
            tvminus = (TextView) view.findViewById(R.id.tv_minus);
            tvplus = (TextView) view.findViewById(R.id.tv_plus);
            proimage = (ImageView) view.findViewById(R.id.cart_proimage);

            //pri = Integer.parseInt(proprice.getText().toString());


            tvminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count--;

                    if(count<0){
                        count=0;
                    }



                    proqty.setText(String.valueOf(count));



                }
            });
            tvplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;



                    proqty.setText(String.valueOf(count));

                }
            });

            ivremove = (ImageView) view.findViewById(R.id.iv_remove);
          ivremove.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  removeAt(getAdapterPosition());
              }
          });

        }

    }

    public void removeAt(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartList.size());
    }


}

