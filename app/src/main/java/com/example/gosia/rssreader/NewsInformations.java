package com.example.gosia.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gosia.rssreader.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gosia on 25.03.2017.
 */

public class NewsInformations extends AppCompatActivity {

    public final String TAG = "NewsInformations";

    public String stuffToShow = null;

    private ListView mListview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.news_informations);
        mListview = (ListView) findViewById(R.id.list);
        Intent intent = getIntent();
        stuffToShow = intent.getStringExtra("EXTRA_MESSAGE");
        if(stuffToShow != null && !stuffToShow.isEmpty()) {
            loadNews();
        }
    }



    private void loadNews(){
        Log.d(TAG,"loadNews");
        //Map<String, String> newsPortal = (Map<String, String>) PreferenceManager.getDefaultSharedPreferences(this).getAll();


        NewsDownloader newsDownloader = new NewsDownloader();
        //Map<String,String> map =  newsDownloader.downloadNews(newsPortal);
        List<Article> aqwzsx =  newsDownloader.downloadNews(stuffToShow);

        List<String> list = new ArrayList<String>();
        if(aqwzsx != null && aqwzsx.size()>0) {
            for(Article article : aqwzsx) {
                list.add(article.getTitle());
                Log.d(TAG,article.toString());
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    list);
            mListview.setAdapter(adapter);
        } else {
            Toast.makeText(this,"No news for this compagny",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
