package com.dev.salwartales.activities.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.dev.salwartales.R;
import com.dev.salwartales.activities.ProductDetailsActivity;
import com.dev.salwartales.activities.adapters.CustomProductAdaptor;
import com.dev.salwartales.activities.model.CustomProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewArrivelFragment extends Fragment {


    private RecyclerView rcview;
    private RecyclerView rcviewlist;
    private CustomProductAdaptor customProductAdaptor;
    private ArrayList<CustomProductModel> customProductModelArrayList;
    private ProgressDialog pd;
    private String Name, Image, Price,Qty,FavStatus,ProId;
    LinearLayout lllistgrid;

    private String url="https://salwartales.com/rests2/api_3.php?category_id=59";



   /* public NewArrivelFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_new_arrivel, container, false);




        rcview=v.findViewById(R.id.rv_featurednewarrgrid);
        rcviewlist=v.findViewById(R.id.rv_featurednewarrlist);

        lllistgrid=v.findViewById(R.id.ll_listgridnewarr);


        rcview.setVisibility(View.VISIBLE);
        rcview.setLayoutManager(new LinearLayoutManager(getContext()));
        rcview.setHasFixedSize(true);
        rcview.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        int numberOfColumns = 2;
        rcview.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        rcview.setAdapter(customProductAdaptor);
        customProductModelArrayList= new ArrayList<>();
        customProductAdaptor = new CustomProductAdaptor(customProductModelArrayList, getContext(), getActivity());




        rcviewlist.setLayoutManager(new LinearLayoutManager(getContext()));
        rcviewlist.setHasFixedSize(true);
        rcviewlist.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        rcviewlist.setAdapter(customProductAdaptor);
        customProductModelArrayList= new ArrayList<>();
        customProductAdaptor = new CustomProductAdaptor(customProductModelArrayList, getContext(), getActivity());



        lllistgrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rcview.getVisibility()==View.VISIBLE){
                    rcview.setVisibility(View.GONE);
                    rcviewlist.setVisibility(View.VISIBLE);
                }else if (rcviewlist.getVisibility() == View.VISIBLE){
                    rcviewlist.setVisibility(View.GONE);
                    rcview.setVisibility(View.VISIBLE);
                }
            }
        });


        GetDataNew();





  return v;
    }

    private void GetDataNew() {
        pd = new ProgressDialog(getContext());
        pd.setMessage("loading");
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://salwartales.com/rests2/api_3.php?category_id=59",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();
                        //hiding the progressbar after completion
                        Log.d("Response2", response.toString());
                        //   Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();


                        try {
                            //getting the whole json object from the response
                            JSONObject jsono = new JSONObject(response);
                            JSONArray jarray = jsono.getJSONArray("data");

                            for (int i = 0; i < jarray.length(); i++) {
                                JSONObject object = jarray.getJSONObject(i);

                                jarray = jsono.getJSONArray("data");
                                JSONArray jarray1 = object.getJSONArray("product_description");

                                for (int j = 0; j < jarray1.length(); j++) {
                                    JSONObject object1 = jarray1.getJSONObject(j);

                                    Name = object1.getString("product_name");
                                    Image = object1.getString("product_image");
                                    Price = object1.getString("rate");
                                    Qty = object1.getString("quantity_left");
                                    FavStatus = object1.getString("fav_status");
                                    ProId = object1.getString("product_id");
                                    CustomProductModel customProductModel = new CustomProductModel();
                                    customProductModel.setImage(Image);
                                    customProductModel.setName(Name);
                                    customProductModel.setValue(Price);
                                    customProductModel.setQtyLeft(Qty);
                                    customProductModel.setProId(ProId);

                                    if (FavStatus.equals("1")) {
                                        customProductModel.setFavimage(R.drawable.hartcolor);
                                    } else if (FavStatus.equals(0)) {
                                        customProductModel.setFavimage(R.drawable.whislist);
                                    }

                                    customProductModelArrayList.add(customProductModel);
                                }
                            }

                            rcview.setAdapter(customProductAdaptor);
                            rcviewlist.setAdapter(customProductAdaptor);
                            customProductAdaptor.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }


}

