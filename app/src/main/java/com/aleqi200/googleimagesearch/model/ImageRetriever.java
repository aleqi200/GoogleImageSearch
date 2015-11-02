package com.aleqi200.googleimagesearch.model;


import android.util.Log;

import com.aleqi200.googleimagesearch.data.ImageHttpResponseHandler;
import com.aleqi200.googleimagesearch.recycleview.RecyclerViewAdapter;

import com.loopj.android.http.AsyncHttpClient;

import java.util.List;

/**
 * Created by acampelo on 10/28/15.
 */
public class ImageRetriever {
    private static final String URL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=8&q=";

    public static void search(String query, final RecyclerViewAdapter adapter, ImageSearchSettings settingsObject) {

        AsyncHttpClient client = new AsyncHttpClient();

        String url = URL + query;
        url = addSettings(settingsObject, url);
        Log.d(ImageRetriever.class.getName(), url);
        client.get(url, new ImageHttpResponseHandler(adapter));
    }

    private static String addSettings(ImageSearchSettings settingsObject, String url) {
        if (settingsObject != null) {
            if (settingsObject.getSiteFilter() != null && !settingsObject.getSiteFilter().isEmpty()) {
                url = url + "&as_sitesearch=" + settingsObject.getSiteFilter();
            }
            if (settingsObject.getImageSize() != null && !settingsObject.getImageSize().isEmpty()) {
                url = url + "&imgsz=" + settingsObject.getImageSize();
            }
            if (settingsObject.getColorFilter() != null && !settingsObject.getColorFilter().isEmpty()) {
                url = url + "&imgcolor=" + settingsObject.getColorFilter();
            }
            if (settingsObject.getImageType() != null && !settingsObject.getImageType().isEmpty()) {
                url = url + "&imgtype=" + settingsObject.getImageType();
            }
        }
        return url;
    }

    public static void search(String query, final RecyclerViewAdapter adapter, ImageSearchSettings settingsObject, int page) {

        AsyncHttpClient client = new AsyncHttpClient();
        Log.d(ImageRetriever.class.getName(), "loading page: " + page);
        String url = URL + query + "&start=" + ((page -1) * 8);
        url = addSettings(settingsObject, url);
        Log.d(ImageRetriever.class.getName(), url);
        client.get(url, new ImageHttpResponseHandler(adapter));
    }
}
