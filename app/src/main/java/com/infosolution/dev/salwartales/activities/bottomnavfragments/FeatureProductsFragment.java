package com.infosolution.dev.salwartales.activities.bottomnavfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.adapters.CustomProductAdaptor;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeatureProductsFragment extends Fragment {

    GridView grid;
    ListView list;
    LinearLayout lllistgrid;

    private String[] name = new String[] { "Drishti dhami in pista color Gown", "Drishti dhami in pista color Gown", "Drishti dhami in pista color Gown", "Drishti dhami in pista color Gown", "WebOS","abcded" };
    public static int [] prodimages={R.drawable.gridimage,R.drawable.gridimage,R.drawable.gridimage,R.drawable.gridimage,R.drawable.gridimage,R.drawable.gridimage};
    private String[] values = new String[] { "1256", "5455", "65654", "656", "6666","1245" };


  /*  public FeatureProductsFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_featured_products, container, false);



        CustomProductAdaptor adapter = new CustomProductAdaptor(getContext(), name,values,prodimages);
        list=(ListView)v.findViewById(R.id.lv_product);

        grid=(GridView)v.findViewById(R.id.gv_product);
        grid.setVisibility(View.VISIBLE);
        grid.setAdapter(adapter);


        list.setAdapter(adapter);

        lllistgrid=v.findViewById(R.id.ll_listgrid);
        lllistgrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (grid.getVisibility()==View.VISIBLE){
                    grid.setVisibility(View.GONE);
                    list.setVisibility(View.VISIBLE);
                }else if (list.getVisibility() == View.VISIBLE){
                    list.setVisibility(View.GONE);
                    grid.setVisibility(View.VISIBLE);
                }
            }
        });


        return v;
    }

}
