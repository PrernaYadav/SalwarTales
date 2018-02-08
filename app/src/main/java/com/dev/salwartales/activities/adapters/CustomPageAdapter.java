package com.dev.salwartales.activities.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.salwartales.R;
import com.dev.salwartales.activities.model.DataObject;

import java.util.List;

/**
 * Created by amit on 2/6/2018.
 */

public class CustomPageAdapter extends PagerAdapter {

    private Context context;
    private List<DataObject> dataObjectList;
    private LayoutInflater layoutInflater;

    public CustomPageAdapter(Context context, List<DataObject> dataObjectList) {
        this.context = context;
        this.dataObjectList = dataObjectList;
        this.layoutInflater = (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return dataObjectList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = this.layoutInflater.inflate(R.layout.pager_row, container, false);
        ImageView displayImage = (ImageView)view.findViewById(R.id.banner);
        displayImage.setImageResource(this.dataObjectList.get(position).getImageBanner());


        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
