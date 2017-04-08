package com.example.gosia.rssreader;

import android.os.AsyncTask;

import com.example.gosia.rssreader.interfaces.NewsGet;
import com.example.gosia.rssreader.model.NewsModel;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gosia on 25.03.2017.
 */

public class NewsDownloader {

    private static final String API_KEY = "a5ca94964c0e4a90a59c4a4cd8a6b11e";
    private List<NewsModel.Articles>aricles;

    public Map<String, String> downloadNews(Map<String, String> newsPortal){
        NewsDownloaderAsyncTask aqd = new NewsDownloaderAsyncTask();
        Map<String,String> map = null;
        try {
            map = aqd.execute(newsPortal).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return map;
    }


    private class NewsDownloaderAsyncTask extends AsyncTask<Map<String, String>, Void, Map<String,String>> {

        @Override
        protected Map<String,String> doInBackground(Map<String, String>...params) {
            NewsModel newsModel;
            Response<NewsModel> newsModelResponse;
            Map<String,String> map  = new HashMap();

            Retrofit retrofit = getRetrofit();

            NewsGet service = retrofit.create(NewsGet.class);
            Call<NewsModel> call = service.getCityNewsFromSource(params[0], API_KEY);

            try {
                newsModelResponse = call.execute();
                newsModel = newsModelResponse.body();

                if(newsModel != null) {

                }
                else{
                    Logger.e("Something is null here!");
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
            return map;
        }


        @Override
        protected void onPostExecute(Map<String, String> stringStringMap) {
            super.onPostExecute(stringStringMap);
        }
        private Retrofit getRetrofit() {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            return new Retrofit.Builder()
                    .baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
    }
}
