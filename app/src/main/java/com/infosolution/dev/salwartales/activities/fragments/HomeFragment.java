package com.infosolution.dev.salwartales.activities.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.ProductPageActivity;
import com.infosolution.dev.salwartales.activities.adapters.HorizontalAdapterBestSeller;
import com.infosolution.dev.salwartales.activities.model.DataBest;
import com.infosolution.dev.salwartales.activities.model.Dataa;
import com.infosolution.dev.salwartales.activities.adapters.HorizontalAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.widget.ImageView.ScaleType.FIT_XY;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    RecyclerView horizontal_recycler_view,rvbestseller;
    HorizontalAdapter horizontalAdapter;
    HorizontalAdapterBestSeller horizontalAdapterBestSeller;
    private ArrayList<Dataa> dataaArrayList;
    private ArrayList<DataBest> dataBestArrayList;

    private ViewFlipper viewFlipper;
    int[] flipimages = {R.drawable.stat, R.drawable.ban, R.drawable.stat};
    Button btnfeaturedview;
    private ProgressDialog pd;

    private String Imagerc, Name, Value;
    private String Id;

    private String FeaName, FeaImage, FeaPrice;
    private  String Qty,FavStatus;
    private  String BSName,BSFeaImage,BSFeaPrice;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);




        horizontal_recycler_view = (RecyclerView) v.findViewById(R.id.horizontal_recycler_view);
        rvbestseller = (RecyclerView) v.findViewById(R.id.rv_bestseller);

        btnfeaturedview = v.findViewById(R.id.btn_featuredview);
        btnfeaturedview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductPageActivity.class);
                startActivity(intent);
            }
        });


        //for image sliding

        viewFlipper = v.findViewById(R.id.flipper);
        // loop for creating ImageView's
        for (int i = 0; i < flipimages.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(FIT_XY);
            imageView.setImageResource(flipimages[i]); // set image in ImageView
            viewFlipper.addView(imageView); // add the created ImageView in ViewFlipper

        }
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
        viewFlipper.setAutoStart(true);


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

        new FetchData().execute();
        return v;
    }


    public class FetchData extends AsyncTask<Object, Object, Boolean> {

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(getContext());
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                String dataurl = "http://salwartales.com/rests2/api_1.php";
                HttpPost httppost = new HttpPost(dataurl);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("data");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Id = object.getString("id");

                        if (Id.equals("1")) {
                            jarray = jsono.getJSONArray("data");
                            JSONArray jarray1 = object.getJSONArray("product_description");

                            for (int j = 0; j < jarray1.length(); j++) {
                                JSONObject object1 = jarray1.getJSONObject(j);

                                FeaName = object1.getString("product_name");
                                FeaImage = object1.getString("product_image");
                                FeaPrice = object1.getString("rate");
                                Qty = object1.getString("quantity_left");
                                FavStatus = object1.getString("fav_status");


                                Dataa dataa= new Dataa();
                                dataa.setImage(FeaImage);
                                dataa.setName(FeaName);
                                dataa.setValue(FeaPrice);
                                dataa.setQtyLeft(Qty);
                                if (FavStatus.equals("1")){
                                    dataa.setFavimage(R.drawable.favselectedicon);
                                }else if (FavStatus.equals(0)){
                                    dataa.setFavimage(R.drawable.whislist);
                                }

                                dataaArrayList.add(dataa);

                            }


                        }else if (Id.equals("2")){

                            jarray = jsono.getJSONArray("data");
                            JSONArray jarray1 = object.getJSONArray("product_description");

                            for (int j = 0; j < jarray1.length(); j++) {
                                JSONObject object1 = jarray1.getJSONObject(j);

                                BSName = object1.getString("product_name");
                                BSFeaImage = object1.getString("product_image");
                                BSFeaPrice= object1.getString("rate");
                                FavStatus = object1.getString("fav_status");
                                Qty = object1.getString("quantity_left");

                                DataBest dataBest= new DataBest();
                                dataBest.setImage(FeaImage);
                                dataBest.setName(FeaName);
                                dataBest.setValue(FeaPrice);
                                dataBest.setValue(FeaPrice);
                                dataBest.setQtyLeft(Qty);

                                if (FavStatus.equals("1")){
                                    dataBest.setFavimage(R.drawable.favselectedicon);
                                }else if (FavStatus.equals(0)){
                                    dataBest.setFavimage(R.drawable.whislist);
                                }

                                dataBestArrayList.add(dataBest);
                            }

                        }


                    }


                    return true;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            pd.dismiss();
            horizontal_recycler_view.setAdapter(horizontalAdapter);
            horizontalAdapter.notifyDataSetChanged();

            rvbestseller.setAdapter(horizontalAdapterBestSeller);
            horizontalAdapterBestSeller.notifyDataSetChanged();
        }


    }
}