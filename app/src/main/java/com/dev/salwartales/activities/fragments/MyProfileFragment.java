package com.dev.salwartales.activities.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.salwartales.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    private ImageView ivprofile;
    private TextView tvname,tvcontact;


    JSONObject response, profile_pic_data, profile_pic_url;
    private String Data;
    private String Fname,Lname;


    /*public MyProfileFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my_profile, container, false);
        ivprofile=v.findViewById(R.id.profile_image);
        tvname=v.findViewById(R.id.proname);
        tvcontact=v.findViewById(R.id.procon);

        final SharedPreferences prefsfb = getActivity().getSharedPreferences("detfromfb", MODE_PRIVATE);
        Data = prefsfb.getString("det", null);


       /* if (TextUtils.isEmpty(Data)){
            final SharedPreferences prefslogin = getActivity().getSharedPreferences("detfromlogin", MODE_PRIVATE);
            Fname = prefslogin.getString("first", null);
            Lname = prefslogin.getString("last", null);

            tvname.setText(Fname+" "+Lname);
            ivprofile.setImageResource(R.drawable.profilepic);

        }*/












        try {
            response = new JSONObject(Data);
            tvcontact.setText(response.get("email").toString());
            tvname.setText(response.get("name").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Picasso.with(getContext()).load(profile_pic_url.getString("url"))
                    .into(ivprofile);

        } catch(Exception e){
            e.printStackTrace();
        }


    return v;
    }

}
