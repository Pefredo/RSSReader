package com.example.gosia.rssreader;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gosia on 25.03.2017.
 */

public class NewsInformations extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.news_informations);

        loadNews();
    }



    private void loadNews(){
        Map<String, String> newsPortal = (Map<String, String>) PreferenceManager.getDefaultSharedPreferences(this).getAll();

        NewsDownloader newsDownloader = new NewsDownloader();
        Map<String,String> map =  newsDownloader.downloadNews(newsPortal);
    }
}
