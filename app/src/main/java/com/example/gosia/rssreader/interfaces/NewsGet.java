package com.example.gosia.rssreader.interfaces;

import com.example.gosia.rssreader.model.NewsModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gosia on 25.03.2017.
 */

public interface NewsGet {

    //https://newsapi.org/v1/articles?&source=bbc-news&apiKey=a5ca94964c0e4a90a59c4a4cd8a6b11e

    //https://newsapi.org/#documentation

    @GET("v1/articles?")
    Call<NewsModel> getCityNewsFromSource(
            @Query("source") Map<String, String> source,
            //@Query("sortBy") String sortBy,
            @Query("apiKey") String token
    );
}
