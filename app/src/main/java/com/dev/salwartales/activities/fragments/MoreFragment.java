package com.dev.salwartales.activities.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.salwartales.R;
import com.dev.salwartales.activities.adapters.CustomProductAdaptor;
import com.dev.salwartales.activities.adapters.MoreCatsAdapter;
import com.dev.salwartales.activities.model.CustomProductModel;
import com.dev.salwartales.activities.model.MoreCatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {
    private RecyclerView rcviewlist;
   private MoreCatsAdapter moreCatsAdapter;
    private ArrayList<MoreCatModel> moreCatArrayList;
    private ProgressDialog pd;


   /* public MoreFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_more, container, false);
        rcviewlist=v.findViewById(R.id.rv_morecat);

        rcviewlist.setLayoutManager(new LinearLayoutManager(getContext()));
        rcviewlist.setHasFixedSize(true);
        rcviewlist.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        rcviewlist.setAdapter(moreCatsAdapter);
        moreCatArrayList= new ArrayList<>();
        moreCatsAdapter = new MoreCatsAdapter(moreCatArrayList, getContext(), getActivity());
        GetCat();

 return v;
   }


    private void GetCat() {
        pd = new ProgressDialog(getContext());
        pd.setMessage("loading");
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://salwartales.com/rests2/api_4.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();
                        //hiding the progressbar after completion
                        Log.d("resCat", response.toString());
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

                                    String CatNAme=object1.getString("category_name");
                                    String CatId=object1.getString("category_id");

                                    MoreCatModel moreCatModel=new MoreCatModel();
                                    moreCatModel.setCatName(CatNAme);
                                    moreCatModel.setCatId(CatId);


                                    System.out.println("values"+CatNAme + CatId);
                                    moreCatArrayList.add(moreCatModel);


                                }


                            }
                            rcviewlist.setAdapter(moreCatsAdapter);
                            moreCatsAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);




}}
