package com.infosolution.dev.salwartales.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.Dataa;
import com.infosolution.dev.salwartales.activities.HorizontalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    private List<Dataa> data;


   /* public HomeFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        horizontal_recycler_view = (RecyclerView) v.findViewById(R.id.horizontal_recycler_view);

        data = fill_with_data();


        horizontalAdapter = new HorizontalAdapter(data, getContext());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

        return v;
    }

    public List<Dataa> fill_with_data() {

        List<Dataa> data = new ArrayList<>();

        data.add(new Dataa(R.drawable.abc,"Black color designer embroldered dress for women","1545"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));

//        data.add(new Dataa(R.drawable.ic_bell_icon, "Image 1"));



     return data;
    }
}