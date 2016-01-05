package com.example.user.movieapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 1/4/2016.
 */
public class SharedPreference {
    public static final String PREFS_NAME = "MOVIE_APP";
    public static final String FAVORITES = "Movie_Favorite";

    public SharedPreference() {
        super();
    }

    public void saveFavorites(Context context, String favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putString(FAVORITES, favorites);

        editor.commit();
    }

    public String getValue(Context context) {
        SharedPreferences settings;
        String text;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(FAVORITES, null);
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(FAVORITES);
        editor.commit();
    }
}
