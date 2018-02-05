package com.dev.salwartales.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev.salwartales.R;
import com.dev.salwartales.activities.model.SearchBarModel;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by amit on 2/3/2018.
 */

public class SearchBarAdapter extends BaseAdapter {
    ArrayList<SearchBarModel> myList = new ArrayList<SearchBarModel>();
    LayoutInflater inflater;
    Context context;
    private ArrayList<SearchBarModel> privatearray;
    public SearchBarAdapter(Context context,ArrayList<SearchBarModel> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        privatearray=new ArrayList<SearchBarModel>();
        privatearray.addAll(myList);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        //LayoutInflater inflater = getLayoutInflater();

        View row;
        row = inflater.inflate(R.layout.search_row, arg2, false);
        TextView title;

        title = (TextView) row.findViewById(R.id.tv_itemnamesearch);


        // Log.d("list", String.valueOf(myList.size())+" "+String.valueOf(data.getDescription()));

        title.setText(myList.get(position).getItemNameSearch());

        return (row);
    }




    // Filter Class
    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        myList.clear();
        if(charText.length()==0){
            myList.addAll(privatearray);
        }
        else{
            for (SearchBarModel c : privatearray) {
                if (c.getItemNameSearch().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    myList.add(c);
                }
            }
        }
        notifyDataSetChanged();



    }}









    /* private ArrayList<SearchBarModel> dataSet;
    Context mContext;
    List<SearchBarModel> sampleData=null;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        ImageView info;
    }



    public SearchBarAdapter(ArrayList<SearchBarModel> data, Context context) {
        super(context, R.layout.search_row, data);
        this.dataSet = data;
        this.mContext=context;

    }







    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SearchBarModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.search_row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.tv_itemnamesearch);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        lastPosition = position;


        viewHolder.txtName.setText(dataModel.getItemNameSearch());
        // Return the completed view to render on screen
        return convertView;
    }*/


