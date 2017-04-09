package com.example.gosia.rssreader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

//https://newsapi.org/sources

public class MainActivity extends AppCompatActivity {

    private String selectedPortal;
    private SharedPreferences sharedPreferences;
    private final String USER_PORTALS = "USER_PORTALS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
    }

    public void goToNewsInformation(ArrayList<String> list){
        if(!list.isEmpty()) {
            Intent intent = new Intent(this, NewsInformations.class);
            intent.putExtra("EXTRA_MESSAGE", list);
            startActivity(intent);
        }
    }

    public void goToNewsInformations(String pString){
        if(!pString.isEmpty()) {
            Intent intent = new Intent(this, NewsInformations.class);
            intent.putExtra("EXTRA_MESSAGE", pString);
            startActivity(intent);
        }
    }

    public void selectSource(View view) {
        TextView textView = (TextView) view;
        selectedPortal = textView.getText().toString();
        selectedPortal = selectedPortal.replaceAll("\\s", "_").toLowerCase();

        sharedPreferences = getApplicationContext().getSharedPreferences(USER_PORTALS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.contains(selectedPortal)) {
            editor.remove(selectedPortal);
            editor.commit();
        } else {
            //editor.putString(selectedPortal, selectedPortal);
            editor.putString(selectedPortal, selectedPortal);
            editor.commit();
        }

        goToNewsInformations(selectedPortal);
    }
}