package com.infosolution.dev.salwartales.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.adapters.BottomNavigationViewHelper;
import com.infosolution.dev.salwartales.activities.bottomnavfragments.FeatureProductsFragment;
import com.infosolution.dev.salwartales.activities.fragments.HomeFragment;

public class ProductPageActivity extends AppCompatActivity  {

    View view;
    private TextView tvbudge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        view=findViewById(R.id.actionbar);
     ImageView ivback = findViewById(R.id.iv_back);
     ivback.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
//             Toast.makeText(ProductPageActivity.this,"back clicked",Toast.LENGTH_SHORT).show();
         finish();
         }
     });




        ImageView ivcart = findViewById(R.id.iv_back);
        ivcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProductPageActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

        tvbudge=findViewById(R.id.badge_notification_1);
        tvbudge.setText("0");






        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);


       /* NavigationMenuView navigationMenuView= (NavigationMenuView) bottomNavigationView.getChildAt(0);
        navigationMenuView.addItemDecoration(new DividerItemDecoration(ProductPageActivity.this,DividerItemDecoration.VERTICAL));*/
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.home:
                     view.setVisibility(View.GONE);


                                setTitle("");
                                HomeFragment fragment =new HomeFragment();
                                FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.framee,fragment," ");
                                fragmentTransaction.commit();

                                break;
                            case R.id.categories:
                              //  selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.deals:
                             //   selectedFragment = ItemThreeFragment.newInstance();
                                break;
                            case R.id.wishlist:
                               // selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.account:
                              //  selectedFragment = ItemThreeFragment.newInstance();
                                break;
                        }
                       /* FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame, selectedFragment);
                        transaction.commit();*/
                        return true;
                    }
                });

        setTitle("");
        FeatureProductsFragment fragment =new FeatureProductsFragment();
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framee,fragment," ");
        fragmentTransaction.commit();


       /* //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
        transaction.commit();*/

        //Used to select an item programmatically
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        /*Toast.makeText(ProductPageActivity.this,"Back pressed",Toast.LENGTH_LONG).show();
        Log.i("back","backpressed");*/



    }


}
