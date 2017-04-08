package com.example.gosia.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.gosia.rssreader.model.Article;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.description;

/**
 * Created by Gosia on 25.03.2017.
 */

public class NewsInformations extends AppCompatActivity {

    public final String TAG = "NewsInformations";
    public String stuffToShow = null;
    private ListView listView;
    private List<Article> articleList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.news_informations);
        listView = (ListView) findViewById(R.id.list);
        Intent intent = getIntent();
        stuffToShow = intent.getStringExtra("EXTRA_MESSAGE");
        if(stuffToShow != null && !stuffToShow.isEmpty()) {
            loadNews();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetails(position);
            }
        });
    }

    private void loadNews(){
        Log.d(TAG,"loadNews");
        //Map<String, String> newsPortal = (Map<String, String>) PreferenceManager.getDefaultSharedPreferences(this).getAll();

        NewsDownloader newsDownloader = new NewsDownloader();
        //Map<String,String> map =  newsDownloader.downloadNews(newsPortal);
        articleList =  newsDownloader.downloadNews(stuffToShow);

        List<String> list = new ArrayList<String>();
        if(articleList != null && articleList.size()>0) {
            for(Article article : articleList) {
                list.add(article.getTitle());
                Log.d(TAG,article.toString());
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    list);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this,"No news for this source",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void goToDetails(int position){
        Intent intent = new Intent(this, NewsDetails.class);

        String title = articleList.get(position).getTitle();
        intent.putExtra("title",title);

        String description = articleList.get(position).getDescription();
        intent.putExtra("description", description);

        String urlPicture = articleList.get(position).getUrlToImage();
        intent.putExtra("urlPicture", urlPicture);

        String author = articleList.get(position).getAuthor();
        intent.putExtra("author", author);

        String date = articleList.get(position).getPublishedAt();
        intent.putExtra("date", date);

        String articleUrl = articleList.get(position).getUrl();
        intent.putExtra("articleUrl", articleUrl);

        startActivity(intent);
    }
}
