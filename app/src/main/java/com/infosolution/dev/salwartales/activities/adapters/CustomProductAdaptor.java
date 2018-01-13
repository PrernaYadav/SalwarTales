package com.infosolution.dev.salwartales.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infosolution.dev.salwartales.R;

/**
 * Created by amit on 1/12/2018.
 */

public class CustomProductAdaptor extends BaseAdapter {

    private Context mContext;
     String[] name;
     String[] value;
     int[] proimage;

    public CustomProductAdaptor(Context mContext, String[] name, String[] value, int[] proimage) {
        this.mContext = mContext;
        this.name = name;
        this.value = value;
        this.proimage = proimage;
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.product_grid_layout, null);
            TextView tvname = (TextView) grid.findViewById(R.id.tv_proname);
            TextView tvvalue = (TextView) grid.findViewById(R.id.tv_provalue);
            ImageView imageView = (ImageView)grid.findViewById(R.id.iv_proimage);
            tvname.setText(name[position]);
            imageView.setImageResource(proimage[position]);
        } else {
            grid = (View) view;
        }

        return grid;
    }
}
