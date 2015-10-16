package com.iandrobot.example.photos.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.iandrobot.example.photos.data.Photo;
import com.iandrobot.example.photos.data.Photos;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by surajbhattarai on 10/15/15.
 */
public class NetworkTask {

    protected static final String ENDPOINT_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=%s&text=%s&format=json&nojsoncallback=1";
    protected static final String API_KEY = "314a1329bfd63991b0d001f93b39d542";

    private static class DownloadPhotoListTask extends AsyncTask<String, Void, Photos> {

        protected Photos doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                String url = String.format(ENDPOINT_URL, API_KEY, params[0]);
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();

                Gson gson = new Gson();

                Photos photos = gson.fromJson(response.body().string(), Photos.class);
                return photos;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onProgressUpdate() {}

        protected void onPostExecute(Photos result) {
            for (Photo photo:result.getPhotos()) {
                Log.v("SURAJ", photo.getTitle());
            }
        }
    }

    public void downloadPhotoList(String searchTerm) {
        new DownloadPhotoListTask().execute(searchTerm);
    }
}