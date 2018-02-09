package com.dev.salwartales.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class ProFromCatListActivity extends AppCompatActivity {

    private RecyclerView rcview;
    private RecyclerView rcviewlist;
    private CustomProductAdaptor customProductAdaptor;
    private ArrayList<CustomProductModel> customProductModelArrayList;
    private ProgressDialog pd;
    private String Name, Image, Price,Qty,FavStatus,ProId;
    private LinearLayout lllistgrid;
    private  String Id;

    String URL = "https://salwartales.com/rests2/api_3.php?category_id=";
    private String URL2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_from_cat_list);

        Intent intent= getIntent();
        Id=intent.getStringExtra("ID");

        URL2=URL+Id;



        rcview=findViewById(R.id.rv_featuredprocatgrid);
        rcviewlist=findViewById(R.id.rv_featuredprocatlist);

        lllistgrid=findViewById(R.id.ll_listgridprocat);


        rcview.setVisibility(View.VISIBLE);
        rcview.setLayoutManager(new LinearLayoutManager(ProFromCatListActivity.this));
        rcview.setHasFixedSize(true);
        rcview.addItemDecoration(new DividerItemDecoration(ProFromCatListActivity.this,
                DividerItemDecoration.VERTICAL));
        int numberOfColumns = 2;
        rcview.setLayoutManager(new GridLayoutManager(ProFromCatListActivity.this, numberOfColumns));
        rcview.setAdapter(customProductAdaptor);
        customProductModelArrayList= new ArrayList<>();
        customProductAdaptor = new CustomProductAdaptor(customProductModelArrayList, ProFromCatListActivity.this, this);




        rcviewlist.setLayoutManager(new LinearLayoutManager(ProFromCatListActivity.this));
        rcviewlist.setHasFixedSize(true);
        rcviewlist.addItemDecoration(new DividerItemDecoration(ProFromCatListActivity.this,
                DividerItemDecoration.VERTICAL));
        rcviewlist.setAdapter(customProductAdaptor);
        customProductModelArrayList= new ArrayList<>();
        customProductAdaptor = new CustomProductAdaptor(customProductModelArrayList, ProFromCatListActivity.this, this);



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


        GetDataa();
    }

    private void GetDataa() {


        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL2,
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
                                            System.out.println("OOOLAAALAAABlouse : " + Name + Price + Qty + FavStatus + ProId+Image);

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
                                    Toast.makeText(ProFromCatListActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(ProFromCatListActivity.this);
            requestQueue.add(stringRequest);
            pd = new ProgressDialog(ProFromCatListActivity.this);
            pd.setMessage("Loading...");
            pd.show();
        } catch (Exception ex) {
        }



    }
}
