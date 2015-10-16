package com.iandrobot.example.photos.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.iandrobot.example.photos.data.Photo;
import com.iandrobot.example.photos.data.PhotoList;
import com.iandrobot.example.photos.data.Photos;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by surajbhattarai on 10/15/15.
 */
public class NetworkTask {

    protected static final String ENDPOINT_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=%s&text=%s&format=json&nojsoncallback=1";
    protected static final String API_KEY = "314a1329bfd63991b0d001f93b39d542";

    private static class DownloadPhotoListTask extends AsyncTask<String, Void, PhotoList> {

        protected PhotoList doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                String url = String.format(ENDPOINT_URL, API_KEY, params[0]);
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();

                //Log.v("SURAJ", "ERROR here: " + response.body().string());

                Gson gson = new Gson();
                String body = response.body().string();

                JsonReader reader = new JsonReader(new StringReader(body));
                reader.setLenient(true);
                return gson.fromJson(reader, PhotoList.class);


            } catch (IOException e) {
                e.printStackTrace();
                Log.v("SURAJ", "ERROR here: " + e.getMessage());
            } catch (Exception e) {
                Log.v("SURAJ", "Error here: " + e.getMessage());
            }


            Log.v("SURAJ", "Error here: " + "Reached here");
            return null;
        }

        protected void onProgressUpdate() {}

        protected void onPostExecute(PhotoList result) {
            for (Photo photo:result.getPhotos().getPhoto()) {
                Log.v("SURAJ", photo.getTitle());
            }
        }
    }

    public void downloadPhotoList(String searchTerm) {
        new DownloadPhotoListTask().execute(searchTerm);
    }
}