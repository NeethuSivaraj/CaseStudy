package com.example.user.movieapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    private Button button1, button2;

    private static String url = "https://movieaddict-codehard.appspot.com/_ah/api/newsFeedEndpoint/v1/newsfeeddtocollection/10/%3C?dateValue=2015-07-16T08:42:20.670z";

    private static final String TAG_ITEMS = "items";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_IMAGEURL = "imageURL";
    private static final String TAG_SHAREURL = "shareURL";
    private static final String TAG_CREATEDAT = "createdAt";
    private static final String TAG_SCALETYPE = "scaleType";
    private static final String TAG_KIND = "kind";
    private ListView lv;
    JSONArray updates = null;
    String description;
    ArrayList<HashMap<String, String>> movieList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<HashMap<String, String>>();

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        lv = getListView();

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String createdAt = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String name = ((TextView) view.findViewById(R.id.createdat))
                        .getText().toString();
                String description = ((TextView) view.findViewById(R.id.content))
                       .getText().toString();

                Intent in = new Intent(getApplicationContext(),
                        SecondActivity.class);
                in.putExtra(TAG_TITLE, name);
                in.putExtra(TAG_CREATEDAT, createdAt);
                in.putExtra(TAG_DESCRIPTION, description);
                startActivity(in);

            }
        });
        new GetUpdates().execute();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FavouritesActivity.class);
                startActivity(i);
            }
        });
    }

    private class GetUpdates extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            ParseMovieUpdate sh = new ParseMovieUpdate();
            String jsonStr = sh.makeServiceCall(url, ParseMovieUpdate.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    updates = jsonObj.getJSONArray(TAG_ITEMS);
                    for (int i = 0; i < updates.length(); i++) {
                        JSONObject c = updates.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_TITLE);
                        description = c.getString(TAG_DESCRIPTION);
                        String imageurl = c.getString(TAG_IMAGEURL);
                        String shareurl = c.getString(TAG_SHAREURL);
                        String createdat = c.getString(TAG_CREATEDAT);
                        String scaletype = c.getString(TAG_SCALETYPE);
                        String kind = c.getString(TAG_KIND);

                        HashMap<String, String> update = new HashMap<String, String>();

                        update.put(TAG_ID, id);
                        update.put(TAG_TITLE, name);
                        update.put(TAG_DESCRIPTION, description);
                        update.put(TAG_CREATEDAT, createdat);

                        movieList.add(update);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, movieList,
                    R.layout.list_item, new String[]{TAG_ID, TAG_TITLE,
                    TAG_CREATEDAT, TAG_DESCRIPTION}, new int[]{R.id.name,
                    R.id.createdat, R.id.desc, R.id.content});

            setListAdapter(adapter);
        }

    }
}