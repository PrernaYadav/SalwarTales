package com.infosolution.dev.salwartales.activities.bottomnavfragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.ProductDetailsActivity;
import com.infosolution.dev.salwartales.activities.adapters.CustomProductAdaptor;
import com.infosolution.dev.salwartales.activities.model.CustomProductModel;
import com.infosolution.dev.salwartales.activities.model.DataBest;
import com.infosolution.dev.salwartales.activities.model.Dataa;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeatureProductsFragment extends Fragment {

    private RecyclerView rcview;
    private RecyclerView rcviewlist;
    private CustomProductAdaptor customProductAdaptor;
    private ArrayList<CustomProductModel> customProductModelArrayList;
    private ProgressDialog pd;
    private String Name, Image, Price,Qty,FavStatus,ProId;
    LinearLayout lllistgrid;




  /*  public FeatureProductsFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_featured_products, container, false);
        rcview=v.findViewById(R.id.rv_featuredpro);
        rcviewlist=v.findViewById(R.id.rv_featuredprolist);

        lllistgrid=v.findViewById(R.id.ll_listgrid);











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




        new FetchFeatureProduct().execute();


        return v;
    }


    public class FetchFeatureProduct extends AsyncTask<Object, Object, Boolean> {

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(getContext());
            pd.setMessage("loading");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                String dataurl = "http://salwartales.com/rests2/api_11.php";
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




                            jarray = jsono.getJSONArray("data");
                            JSONArray jarray1 = object.getJSONArray("product_description");

                            for (int j = 0; j < jarray1.length(); j++) {
                                JSONObject object1 = jarray1.getJSONObject(j);

                                Name = object1.getString("product_name");
                                Image= object1.getString("product_image");
                                Price = object1.getString("rate");
                                Qty = object1.getString("quantity_left");
                                FavStatus = object1.getString("fav_status");
                                ProId = object1.getString("product_id");


                                CustomProductModel customProductModel= new CustomProductModel();
                                customProductModel.setImage(Image);
                                customProductModel.setName(Name);
                                customProductModel.setValue(Price);
                                customProductModel.setQtyLeft(Qty);
                                customProductModel.setProId(ProId);

                                if (FavStatus.equals("1")){
                                    customProductModel.setFavimage(R.drawable.favselectedicon);
                                }else if (FavStatus.equals(0)){
                                    customProductModel.setFavimage(R.drawable.whislist);
                                }

                                customProductModelArrayList.add(customProductModel);

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
            rcview.setAdapter(customProductAdaptor);
            rcviewlist.setAdapter(customProductAdaptor);
            customProductAdaptor.notifyDataSetChanged();


        }


    }


}
