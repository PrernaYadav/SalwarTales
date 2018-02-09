package com.dev.salwartales.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.dev.salwartales.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView tvproname,tvprice,tvavail,tvprodetail,tvcolor,tvworkdetail,tvoccasion,tvfabric,tvshape,tvwashcare;
    private ImageView ivproimage,ivwish,ivshare;
    private Button btnaddtocart,btnbuynow;
    private String ProId,Proid;
    private ProgressDialog pd;
    private  String ProName,Price,Available,Prodetail,Color,Workdetail,Occasion,Fabric,shape,WashCare,ProImage,FavStatus,Qty;
 private   View view;
 private String url2;

    private String url="https://salwartales.com/rests2/api_2.php?product_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        view = findViewById(R.id.actionbarr);
        ImageView ivback = findViewById(R.id.iv_back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(CartActivity.this,"back clicked",Toast.LENGTH_SHORT).show();
                finish();
            }
        });



        /*final SharedPreferences prefs = getSharedPreferences("CustumproductPage", MODE_PRIVATE);
        ProId = prefs.getString("pro", null);*/

        Intent intent=getIntent();
        Proid=intent.getStringExtra("HoriProid");

        if (TextUtils.isEmpty(Proid)){
            Proid=intent.getStringExtra("bsProid");
        }





        Intent intent2=getIntent();
        ProId=intent2.getStringExtra("Proid");

        if (TextUtils.isEmpty(Proid)) {

            url2=url+ProId;

        }else  if (TextUtils.isEmpty(ProId)) {

            url2=url+Proid;

        }


        Log.i("Values",""+Proid);



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
        ivshare=findViewById(R.id.iv_share);
        ivwish=findViewById(R.id.iv_wish);
        btnaddtocart=findViewById(R.id.btn_addtocartdet);
        btnbuynow=findViewById(R.id.btn_buynowdet);


    //    new Dataa().execute();
        Loaddata();



        ivshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }

    private void Loaddata() {

        pd = new ProgressDialog(ProductDetailsActivity.this);
        pd.setMessage("loading");
        pd.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        //hiding the progressbar after completion
                        Log.d("Response2", response.toString());
                     //   Toast.makeText(ProductDetailsActivity.this, response.toString(), Toast.LENGTH_SHORT).show();


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

                                    ProName = object1.getString("product_name");
                                    Price = object1.getString("rate");
                                    ProImage = object1.getString("product_image");
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
                                    if (FavStatus.equals("1")) {
                                        ivwish.setImageResource(R.drawable.ico);
                                    } else {
                                        ivwish.setImageResource(R.drawable.whislist);
                                    }

                                    if (Available.equals("1")) {
                                        tvavail.setText("In Stock");
                                    } else {
                                        tvavail.setText("Out Of Stock");
                                    }

                                    Glide.with(ProductDetailsActivity.this).load(ProImage).into(ivproimage);
                                }}




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(ProductDetailsActivity.this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);


    }



}
