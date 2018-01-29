package com.infosolution.dev.salwartales.activities;

import android.content.Intent;
import android.net.http.RequestQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.model.DataBest;
import com.infosolution.dev.salwartales.activities.model.Dataa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView tvproname,tvprice,tvavail,tvprodetail,tvcolor,tvworkdetail,tvoccasion,tvfabric,tvshape,tvwashcare;
    private ImageView ivproimage;
    private Button btnaddtocart,btnbuynow;
    private String ProId;
    private  String ProName,Price,Available,Prodetail,Color,Workdetail,Occasion,Fabric,shape,WashCare,ProImage,FavStatus,Qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent=getIntent();
        ProId=intent.getStringExtra("Proid");


        tvproname=findViewById(R.id.tv_namedet);
        tvprice=findViewById(R.id.tv_pricedet);
        tvavail=findViewById(R.id.tv_availabledet);
        tvprodetail=findViewById(R.id.tv_detailsdet);
        tvcolor=findViewById(R.id.tv_color);
        tvworkdetail=findViewById(R.id.tv_workdetail);
        tvoccasion=findViewById(R.id.tv_occasion);
        tvfabric=findViewById(R.id.tv_fabric);
        tvshape=findViewById(R.id.tv_shape);
        tvwashcare=findViewById(R.id.tv_washcare);
        ivproimage=findViewById(R.id.iv_proimagedet);
        btnaddtocart=findViewById(R.id.btn_addtocartdet);
        btnbuynow=findViewById(R.id.btn_buynowdet);

        Loaddata();

    }

    private void Loaddata() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://salwartales.com/rests2/api_2.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ProductDetailsActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                        Log.i("resPro",response.toString());

                        try {
                            JSONObject jsono = new JSONObject(response);
                            JSONArray jarray = jsono.getJSONArray("data");

                            for (int i = 0; i < jarray.length(); i++) {
                                JSONObject object = jarray.getJSONObject(i);


                                jarray = jsono.getJSONArray("data");
                                    JSONArray jarray1 = object.getJSONArray("product_description");

                                    for (int j = 0; j < jarray1.length(); j++) {
                                        JSONObject object1 = jarray1.getJSONObject(j);

                                        ProName = object1.getString("product_name");
                                        Price = object1.getString("rate");
                                        ProImage= object1.getString("product_image");
                                        FavStatus = object1.getString("fav_status");
                                        Qty = object1.getString("quantity_left");
                                        Available = object1.getString("availability");
                                        Color = object1.getString("color");
                                        Workdetail = object1.getString("work_details");
                                        Occasion = object1.getString("occasion");
                                        Fabric = object1.getString("fabric");
                                        shape = object1.getString("shape");
                                        WashCare = object1.getString("wash_care");
                                        ProImage = object1.getString("product_image");

                                        tvproname.setText(ProName);
                                        tvprice.setText(Price);
                                        tvcolor.setText(Color);
                                        tvworkdetail.setText(Workdetail);
                                        tvoccasion.setText(Occasion);
                                        tvfabric.setText(Fabric);
                                        tvshape.setText(shape);
                                        tvwashcare.setText(WashCare);
                                        Glide.with(ProductDetailsActivity.this).load(ProImage).into(ivproimage);
                                    }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                     /*   Intent intent = new Intent(ProductDetailsActivity.this, LoginMailActivity.class);
                        startActivity(intent);*/



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(ProductDetailsActivity.this, error.toString(), Toast.LENGTH_LONG).show();

//                       pdLoading.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("product_id", ProId);

                Log.i("Detailsparam",""+params);



                return params;
            }

        };

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
