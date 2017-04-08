package com.example.gosia.rssreader;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gosia.rssreader.interfaces.NewsGet;
import com.example.gosia.rssreader.model.Article;
import com.example.gosia.rssreader.model.NewsModel;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;
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
    private static final String TAG = "NewsDownloader";

    private List<Article> aricles;

    public List<Article> downloadNews(String newsPortal){
        NewsDownloaderAsyncTask newsDownloaderAsyncTask = new NewsDownloaderAsyncTask();
        List<Article> list = null;
        try {
            list = newsDownloaderAsyncTask.execute(newsPortal).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return list;
    }


    private class NewsDownloaderAsyncTask extends AsyncTask<String, Void, List<Article>> {

        @Override
        protected List<Article> doInBackground(String... params) {
            Log.d(TAG,"doInBackground: " + params[0]);
            NewsModel newsModel;
            Response<NewsModel> newsModelResponse;
            List<Article> result = null;

            Retrofit retrofit = getRetrofit();

            NewsGet service = retrofit.create(NewsGet.class);
            Call<NewsModel> call = service.getNewsFromOneSource(params[0], API_KEY);

            try {
                newsModelResponse = call.execute();
                newsModel = newsModelResponse.body();

                if(newsModel != null) {
                     result= newsModel.getArticles();
                } else{
                    Logger.e("Something is null here!");
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
            return result;
        }


        @Override
        protected void onPostExecute(List<Article> result) {
            super.onPostExecute(result);
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
