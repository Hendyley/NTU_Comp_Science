package com.example.mdpandroidcontroller.helper;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gwyn Bong Xiao Min on 1/2/2021.
 */
class SharedPref {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    @SuppressLint("CommitPrefEdits")
    public SharedPref (Context context){
        this.context = context;
        preferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
}
