package com.infosolution.dev.salwartales.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.infosolution.dev.salwartales.R;
import com.infosolution.dev.salwartales.activities.adapters.SearchBarAdapter;
import com.infosolution.dev.salwartales.activities.model.SearchBarModel;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<SearchBarModel> dataModels;
    private ListView listView;
    private static SearchBarAdapter adapter;
    private EditText etsearch;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        view = findViewById(R.id.search_action);
        etsearch = findViewById(R.id.et_searchbar);




        listView = (ListView) findViewById(R.id.list);


        dataModels = new ArrayList<>();
        dataModels.add(new SearchBarModel("Apple Pie"));
        dataModels.add(new SearchBarModel("Banana Bread"));
        dataModels.add(new SearchBarModel("Cupcake"));
        dataModels.add(new SearchBarModel("Donut"));
        dataModels.add(new SearchBarModel("Eclair"));
        dataModels.add(new SearchBarModel("Froyo"));
        dataModels.add(new SearchBarModel("Gingerbread"));
        dataModels.add(new SearchBarModel("Honeycomb"));
        dataModels.add(new SearchBarModel("Ice Cream Sandwich"));
        dataModels.add(new SearchBarModel("Jelly Bean"));
        dataModels.add(new SearchBarModel("Kitkat"));
        dataModels.add(new SearchBarModel("Lollipop"));
        dataModels.add(new SearchBarModel("Marshmallow"));

        adapter = new SearchBarAdapter(getApplicationContext(), dataModels);

        listView.setAdapter(adapter);
        listView.setVisibility(View.GONE);
        listView.setTextFilterEnabled(true);


        etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                listView.setVisibility(View.VISIBLE);

                // When user changed the Text
                String text = etsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }







}