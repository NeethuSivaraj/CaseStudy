package com.example.user.movieapp;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 1/4/2016.
 */
public class FavouritesActivity extends ListActivity {

    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private TextView textTxt;
    private String text;
    private SharedPreference sharedPreference;
    Activity context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourites);
        sharedPreference = new SharedPreference();

        findViewsById();

        text = sharedPreference.getValue(context);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        list.add(text);
        //textTxt.setText(text);
        //adapter.notifyDataSetChanged();
        setListAdapter(adapter);

    }

    private void findViewsById() {
        textTxt = (TextView) findViewById(R.id.textView1);
        textTxt.setMovementMethod(new ScrollingMovementMethod());
    }
}

