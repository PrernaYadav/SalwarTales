package com.dev.salwartales.activities.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.salwartales.R;
import com.dev.salwartales.activities.adapters.CustomProductAdaptor;
import com.dev.salwartales.activities.model.CustomProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LahngaFragment extends Fragment {


    private RecyclerView rcview;
    private RecyclerView rcviewlist;
    private CustomProductAdaptor customProductAdaptor;
    private ArrayList<CustomProductModel> customProductModelArrayList;
    private ProgressDialog pd;
    private String Name, Image, Price,Qty,FavStatus,ProId;
    private LinearLayout lllistgrid;


   /* public LahngaFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_lahnga, container, false);

        rcview=v.findViewById(R.id.rv_featuredlahngagrid);
        rcviewlist=v.findViewById(R.id.rv_featuredlahngalist);

        lllistgrid=v.findViewById(R.id.ll_listgridlahnga);


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


        GetDatalahnga();


        return v;
    }

    private void GetDatalahnga() {


        String URL = "https://salwartales.com/rests2/api_3.php?category_id=65";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pd.dismiss();
                            System.out.println("Response is : " + response);
                            try {
                                JSONObject jsono = new JSONObject(response);
                                if (jsono.getString("status").equals("success")) {
                                    JSONArray jarray = jsono.getJSONArray("data");
                                    for (int i = 0; i < jarray.length(); i++) {
                                        JSONObject object = jarray.getJSONObject(i);
                                        jarray = jsono.getJSONArray("data");
                                        JSONArray jarray1 = object.getJSONArray("product_description");
                                        for (int j = 0; j < jarray1.length(); j++) {
                                            JSONObject object1 = jarray1.getJSONObject(j);
                                            String Name = object1.getString("product_name");
                                            String Image = object1.getString("product_image");
                                            String Price = object1.getString("rate");
                                            String Qty = object1.getString("quantity_left");
                                            String FavStatus = object1.getString("fav_status");
                                            String ProId = object1.getString("product_id");
                                            System.out.println("OOOLAAALAAALahnga : " + Name + Price + Qty + FavStatus + ProId+Image);

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


                                } else {
                                    Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception ex) {
                                System.out.println("EXCPTION IN SUCCESS OF LOGIN REQUEST : " + ex.toString());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            System.out.println("ERROR IN LOGIN STRING REQUEST : " + error.getMessage());
                        }

                    })

            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    80000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
            pd = new ProgressDialog(getContext());
            pd.setMessage("Loading...");
            pd.show();
        } catch (Exception ex) {
        }
    }
    }

