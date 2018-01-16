package com.infosolution.dev.salwartales.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.infosolution.dev.salwartales.R;

/**
 * Created by Shreyansh Srivastava on 1/16/2018.
 */

public class Checkout_Login extends AppCompatActivity {

    private Button btnproceed;
    private ImageView ivfb,ivgoogle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_login);
    }
}
