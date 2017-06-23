package com.cs4540.fy.newsapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

/**
 * Created by fy on 6/23/17.
 */

public final class NetworkUtils {
    public static final String BASE_URL = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=c40e5186d5654ef7ba3043a392673e80";
    public static final String PARAM_QUERY = "q";
    public static final String PARAM_SORT = "sort";

    public static URL buildURL(String searchQuery, String sortBY) {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, searchQuery)
                .appendQueryParameter(PARAM_SORT, sortBY).build();

        URL url = null;
        try {
            String urlString = uri.toString();
            Log.d(TAG, "url:" + urlString);
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {

            InputStream newinput = urlConnection.getInputStream();
            Scanner input = new Scanner(newinput);


            input.useDelimiter("\\A");
            boolean hasInput = input.hasNext();
            if (hasInput) {
                return input.next();
            } else {
                return null;
            }

        }finally {
            urlConnection.disconnect();
        }
    }
}
