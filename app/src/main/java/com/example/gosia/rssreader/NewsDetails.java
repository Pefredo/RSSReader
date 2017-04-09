package com.example.gosia.rssreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Gosia on 08.04.2017.
 */

public class NewsDetails extends AppCompatActivity{
    private TextView title;
    private TextView content;
    private SimpleDraweeView image;
    private TextView date;
    private TextView author;
    private TextView url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        init();
    }

    public void init(){
        title = (TextView) findViewById(R.id.titleDetails);
        content = (TextView) findViewById(R.id.contentDetails);
        image = (SimpleDraweeView) findViewById(R.id.imageDetails);
        date = (TextView) findViewById(R.id.dateDetails);
        author = (TextView) findViewById(R.id.authorDetail);
        url = (TextView) findViewById(R.id.url);

        setDetailsData();
    }

    public void setDetailsData(){

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        if(data != null)
        {
            String titleFromIntent = intent.getStringExtra("title");
            title.setText(titleFromIntent);

            String contentFromIntent = intent.getStringExtra("description");
            content.setText(contentFromIntent);

            String imageFromIntent = intent.getStringExtra("urlPicture");
            if(imageFromIntent != null) {
                Uri uri = Uri.parse(imageFromIntent);
                image.setImageURI(uri);
            }

            String authorFromIntent = intent.getStringExtra("author");
            author.setText(authorFromIntent);

            String dateFromIntent = intent.getStringExtra("date");
            if(dateFromIntent!=null) {
                dateFromIntent = dateFromIntent.replaceAll("T", " ").replaceAll("Z", "");
            }

            String urlFromIntent = intent.getStringExtra("articleUrl");
            url.setText(urlFromIntent);

            date.setText(dateFromIntent);
        }
    }
}
