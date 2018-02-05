package com.dev.salwartales.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.salwartales.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etuser,etpass;
    private CheckBox checkBox;
    private Button btnsignin;
    private ImageView ivfb,ivtwitter;
    private TextView tvforgotpass,tvsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);


        etuser = findViewById(R.id.et_user);
        etpass = findViewById(R.id.et_password);
        checkBox=findViewById(R.id.cb);
        btnsignin=findViewById(R.id.btn_login);
        ivfb=findViewById(R.id.iv_fb);
        ivtwitter=findViewById(R.id.iv_twitter);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,Navigation.class);
                startActivity(intent);
            }
        });

ivtwitter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});
    }
}
