package com.dev.salwartales.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.salwartales.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText etuser, etpass;
    private CheckBox checkBox;
    private Button btnsignin;
    private ImageView ivfb, ivtwitter;
    private TextView tvforgotpass, tvsignup;
    private ProgressDialog pdLoading;
    private  String email,password;
    CallbackManager callbackManager;
    private static final String email_pattern =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        loginButton.setReadPermissions("email");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);
                Log.d("FacebookFragment",loginResult.toString() );



            }

            @Override
            public void onCancel() {
                // App code
                Log.d("FacebookFragment", "Canceled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("FacebookFragment", String.format("Error: %s", exception.toString()));

            }
        });



        etuser = findViewById(R.id.et_user);
        etpass = findViewById(R.id.et_password);
        checkBox = findViewById(R.id.cb);
        btnsignin = findViewById(R.id.btn_login);
        ivfb = findViewById(R.id.iv_fb);
        ivtwitter = findViewById(R.id.iv_gmail);

      btnsignin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              email=etuser.getText().toString();
              password=etpass.getText().toString();

              Matcher matcherObj = Pattern.compile(email_pattern).matcher(email);

              if (!matcherObj.matches()) {
                  etuser.setError("Invalid Email");
              } else if (etpass.getText().toString().trim().length() < 0) {
                  etpass.setError("Password Length is short");
              } else {
                  Login();
              }


          }
      });


    }

    private void Login() {



        pdLoading = new ProgressDialog(LoginActivity.this);
        //this method will be running on UI thread
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();

        String URL="https://salwartales.com/rests2/api_5.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("pppppppppp", response);
                        pdLoading.dismiss();
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                        try {


                            JSONObject result = new JSONObject(response);
                            String status = result.getString("status");
                            Log.i("status",""+status);


                            if (status.equals("success")){
                                JSONArray routearray = result.getJSONArray("data");
                                for (int i=0;i<routearray.length();i++){

                                    JSONObject object = routearray.getJSONObject(i);

                                    Log.i("type",""+object.getString("product_type"));

                                    JSONArray routearray1 = object.getJSONArray("product_description");
                                    for (int j=0; j<routearray1.length();j++){

                                        JSONObject object1 = routearray1.getJSONObject(j);

                                    String   Userid = object1.getString("user_id");
                                        String   Country= object1.getString("country");
                                        String  First = object1.getString("firstname");
                                        String   Last= object1.getString("lastname");
                                        Log.i("DAta",""+Userid + Country + First + Last);


                                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("detfromlogin", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("userid", Userid);
                                        editor.putString("country", Country);
                                        editor.putString("first", First);
                                        editor.putString("last", Last);
                                        editor.commit();

                                        Intent intent=new Intent(LoginActivity.this,Navigation.class);
                                        startActivity(intent);


                                    }

                                }
                            }else {
                                Toast.makeText(LoginActivity.this, "Please Check Your Login Credantial", Toast.LENGTH_SHORT).show();
                            }


                        }catch (Exception e){

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  Toast.makeText(LoginActivity.this, ""+error.toString(), Toast.LENGTH_LONG).show();
                        pdLoading.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<String, String>();

                params.put("email",email );
                params.put("password",password );

                Log.i("params",""+params);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        Intent intent = new Intent(LoginActivity.this, Navigation.class);
                        startActivity(intent);
                        Log.i("userProfile", json_object.toString());


                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("detfromfb", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("det", json_object.toString());
                        editor.commit();



                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(240).height(240)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

}
