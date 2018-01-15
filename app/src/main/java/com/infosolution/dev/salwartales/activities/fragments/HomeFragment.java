package com.infosolution.dev.salwartales.activities.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.ProductPageActivity;
import com.infosolution.dev.salwartales.activities.model.Dataa;
import com.infosolution.dev.salwartales.activities.adapters.HorizontalAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.ImageView.ScaleType.FIT_XY;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    private List<Dataa> data;

    private ViewFlipper viewFlipper;
    int[] flipimages = {R.drawable.stat, R.drawable.ban, R.drawable.stat};
    Button btnfeaturedview;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        horizontal_recycler_view = (RecyclerView) v.findViewById(R.id.horizontal_recycler_view);

        btnfeaturedview=v.findViewById(R.id.btn_featuredview);
        btnfeaturedview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductPageActivity.class);
                startActivity(intent);
            }
        });


        //for image sliding

        viewFlipper=v.findViewById(R.id.flipper);
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
        data.add(new Dataa(R.drawable.abc,"Black color designer embroldered dress for women","abc"));
        data.add(new Dataa(R.drawable.abc,"Black color designer embroldered dress for women","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));
        data.add(new Dataa(R.drawable.abc,"abc","abc"));

//        data.add(new Dataa(R.drawable.ic_bell_icon, "Image 1"));



     return data;
    }
}