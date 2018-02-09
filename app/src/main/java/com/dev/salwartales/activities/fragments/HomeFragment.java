package com.dev.salwartales.activities.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.salwartales.R;
import com.dev.salwartales.activities.ProductPageActivity;
import com.dev.salwartales.activities.SearchActivity;
import com.dev.salwartales.activities.adapters.CustomPageAdapter;
import com.dev.salwartales.activities.adapters.HorizontalAdapterBestSeller;
import com.dev.salwartales.activities.model.DataBest;
import com.dev.salwartales.activities.model.DataObject;
import com.dev.salwartales.activities.model.Dataa;
import com.dev.salwartales.activities.adapters.HorizontalAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView horizontal_recycler_view, rvbestseller;
    private HorizontalAdapter horizontalAdapter;
    private HorizontalAdapterBestSeller horizontalAdapterBestSeller;
    private ArrayList<Dataa> dataaArrayList;
    private ArrayList<DataBest> dataBestArrayList;

    private ViewFlipper viewFlipper;
    //  int[] flipimages = {R.drawable.mobilebanner1, R.drawable.mobilebanner2, R.drawable.mobilebanner1};
    Button btnfeaturedview, btnbestsellerview;
    private ProgressDialog pd;

    private String Imagerc, Name, Value;
    private String Id;

    private String FeaName, FeaImage, FeaPrice, ProdId;
    private String Qty, FavStatus;
    private String BSName, BSFeaImage, BSFeaPrice;
    private TextView tvsearch;
   private  ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 300;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.
    private LinearLayout llbestoffr,llnewarr,llgown,lllahnga,llmore;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        llbestoffr=v.findViewById(R.id.llbestoffr);
        llbestoffr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new BestOfferFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });


        llgown=v.findViewById(R.id.llgown);
        llgown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new GownFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });

        lllahnga=v.findViewById(R.id.lllahnga);
        lllahnga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new LahngaFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });


        llnewarr=v.findViewById(R.id.llnewarr);
        llnewarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new NewArrivelFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });




        llmore=v.findViewById(R.id.llmore);
        llmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new MoreFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });



        List<DataObject> getData = dataSource();
        viewPager = (ViewPager)v.findViewById(R.id.viewpager);
        CustomPageAdapter mCustomPagerAdapter = new CustomPageAdapter(getContext(), getData);
        viewPager.setAdapter(mCustomPagerAdapter);


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 4-1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        Animation in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        viewPager.setAnimation(in);




        horizontal_recycler_view = (RecyclerView) v.findViewById(R.id.horizontal_recycler_view);
        rvbestseller = (RecyclerView) v.findViewById(R.id.rv_bestseller);
        tvsearch = v.findViewById(R.id.tv_search);
        tvsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        btnfeaturedview = v.findViewById(R.id.btn_featuredview);
        btnfeaturedview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductPageActivity.class);
                intent.putExtra("F","FEA");
                startActivity(intent);
            }
        });

        btnbestsellerview = v.findViewById(R.id.btn_bestsellerview);
        btnbestsellerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductPageActivity.class);
                intent.putExtra("Best","BEST");
                startActivity(intent);
            }
        });


       /* //for image sliding

        viewFlipper = v.findViewById(R.id.flipper);
        // loop for creating ImageView's

        // create the object of ImageView
        ImageView imageView = new ImageView(getContext());

        // Declare in and out animations and load them using AnimationUtils class

        Animation in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        // set the animation type's to ViewFlipper
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(5000);
        // set auto start for flipping between views
        viewFlipper.setAutoStart(true);*/







        horizontal_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        horizontal_recycler_view.setHasFixedSize(true);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        dataaArrayList = new ArrayList<>();
        horizontalAdapter = new HorizontalAdapter(dataaArrayList, getContext(), getActivity());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);


        rvbestseller.setLayoutManager(new LinearLayoutManager(getContext()));
        rvbestseller.setHasFixedSize(true);
        rvbestseller.setAdapter(horizontalAdapterBestSeller);
        dataBestArrayList = new ArrayList<>();
        horizontalAdapterBestSeller = new HorizontalAdapterBestSeller(dataBestArrayList, getContext(), getActivity());
        LinearLayoutManager horizontalLayoutManagerr = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvbestseller.setLayoutManager(horizontalLayoutManagerr);
//new FetchData().execute();
GetData();


        return v;
    }
    public void GetData(){

        pd = new ProgressDialog(getContext());
        pd.setMessage("loading");
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://salwartales.com/rests2/api_1.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        Log.d("Response2", response.toString());
                     //   Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();


                        try {
                            //getting the whole json object from the response
                            JSONObject jsono = new JSONObject(response);
                            JSONArray jarray = jsono.getJSONArray("data");
                            for (int i = 0; i < jarray.length(); i++) {

                                JSONObject object = jarray.getJSONObject(i);

                                String Product_type = object.getString("product_type");

                                if (Product_type.equals("Featured Products")) {

                                    JSONArray jarrayFeartured = object.getJSONArray("product_description");

                                    for (int j = 0; j < jarrayFeartured.length(); j++) {
                                        JSONObject object1 = jarrayFeartured.getJSONObject(j);

                                        String FeaName = object1.getString("product_name");
                                        String FeaImage = object1.getString("product_image");
                                        String FeaPrice = object1.getString("rate");
                                        String Qty = object1.getString("quantity_left");
                                        String FavStatus = object1.getString("fav_status");
                                        String ProdId = object1.getString("product_id");

                                        Log.i("Featured Products Name", ":" + FeaName);
                                        Log.i("Featured Products Image", ":" + FeaImage);
                                        Log.i("Featured Products Price", ":" + FeaPrice);
                                        Log.i("Featured Products Qty", ":" + Qty);
                                        Log.i("Featured Products fav", ":" + FavStatus);
                                        Log.i("Featured Products Id", ":" + ProdId);

                                        Dataa dataa = new Dataa();
                                        dataa.setImage(FeaImage);
                                        dataa.setName(FeaName);
                                        dataa.setValue(FeaPrice);
                                        dataa.setQtyLeft(Qty);
                                        dataa.setHoriPorId(ProdId);
                                        if (FavStatus.equals("1")) {
                                            dataa.setFavimage(R.drawable.ico);
                                        } else if (FavStatus.equals(0)) {
                                            dataa.setFavimage(R.drawable.whislist);
                                        }
                                        dataaArrayList.add(dataa);
                                    }

                                }

                                if (Product_type.equals("Best Seller Products")) {
                                    JSONArray jarrayBestSeller = object.getJSONArray("product_description");


                                    for (int j = 0; j < jarrayBestSeller.length(); j++) {
                                        JSONObject object2 = jarrayBestSeller.getJSONObject(j);

                                        String FeaName = object2.getString("product_name");
                                        String FeaImage = object2.getString("product_image");
                                        String FeaPrice = object2.getString("rate");
                                        String Qty = object2.getString("quantity_left");
                                        String FavStatus = object2.getString("fav_status");
                                        String ProdId = object2.getString("product_id");

                                        Log.i("Best Seller  Name", ":" + FeaName);
                                        Log.i("Best Seller  Image", ":" + FeaImage);
                                        Log.i("Best Seller  Price", ":" + FeaPrice);
                                        Log.i("Best Seller  Qty", ":" + Qty);
                                        Log.i("Best Seller  fav", ":" + FavStatus);
                                        Log.i("Best Seller  Id", ":" + ProdId);

                                        DataBest dataBest = new DataBest();
                                        dataBest.setImage(FeaImage);
                                        dataBest.setName(FeaName);
                                        dataBest.setValue(FeaPrice);
                                        dataBest.setValue(FeaPrice);
                                        dataBest.setQtyLeft(Qty);
                                        dataBest.setProid(ProdId);

                                        if (FavStatus.equals("1")) {
                                            dataBest.setFavimage(R.drawable.hartcolor);
                                        } else if (FavStatus.equals(0)) {
                                            dataBest.setFavimage(R.drawable.whislist);
                                        }

                                        dataBestArrayList.add(dataBest);


                                    }

                                }
                                if (Product_type.equals("Categories")) {
                                    JSONArray jarrayCategories = object.getJSONArray("product_description");
                                }
                            }

                            pd.dismiss();
                            horizontal_recycler_view.setAdapter(horizontalAdapter);
                            horizontalAdapter.notifyDataSetChanged();

                            rvbestseller.setAdapter(horizontalAdapterBestSeller);
                            horizontalAdapterBestSeller.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getContext(),""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }


    private List<DataObject> dataSource(){
        List<DataObject> data = new ArrayList<DataObject>();
        data.add(new DataObject(R.drawable.mobilebanner1));
        data.add(new DataObject(R.drawable.mobilebanner2));
        data.add(new DataObject(R.drawable.stat));
        return data;
    }
}