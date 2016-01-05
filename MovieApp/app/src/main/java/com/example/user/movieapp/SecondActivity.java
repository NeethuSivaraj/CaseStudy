package com.example.user.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SecondActivity  extends Activity
{
    private TextView textName, textDesc, textcreatedat;
    LinearLayout linaerlayout;
    SharedPreference sharedPreference;
    Activity context = this;
    private ToggleButton toggle;
    private static final String TAG_CREATEDAT = "createdat";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent in = getIntent();

        String name = in.getStringExtra(TAG_TITLE);
        String createdat = in.getStringExtra(TAG_CREATEDAT);
        String description = in.getStringExtra(TAG_DESCRIPTION);

        textcreatedat = (TextView) findViewById(R.id.name);
        textName = (TextView) findViewById(R.id.createdat);
        textDesc = (TextView) findViewById(R.id.desc);

        textName.setText(name);
        textcreatedat.setText(createdat);
        textDesc.setText(description);
        textDesc.setMovementMethod(new ScrollingMovementMethod());

        toggle = (ToggleButton) findViewById(R.id.toggleButton1);
        toggle.setMovementMethod(new ScrollingMovementMethod());
        toggle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (toggle.isChecked()) {
                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("toggleButton", toggle.isChecked());
                    editor.commit();
                    toggle.setTextColor(Color.BLUE);
                    String tit = ((TextView) findViewById(R.id.createdat)).getText().toString();
                    String cont = ((TextView) findViewById(R.id.desc)).getText().toString();
                    sharedPreference = new SharedPreference();
                    sharedPreference.saveFavorites(context, tit);

                    Toast.makeText(context,getResources().getString(R.string.saved), Toast.LENGTH_LONG).show();
                    Intent in = new Intent(getApplicationContext(), FavouritesActivity.class);
                    in.putExtra(TAG_TITLE, tit);
                    in.putExtra(TAG_DESCRIPTION, cont);
                    //startActivity(in);
                    toggle.setChecked(true);
                } else {
                    toggle.setTextColor(Color.WHITE);
                    //linaerlayout.removeAllViews(
                    Intent in = new Intent(getApplicationContext(), FavouritesActivity.class);
                    in.putExtra("FAVORITES","No Favorites Added");
                }
            }
        });

    }
}
