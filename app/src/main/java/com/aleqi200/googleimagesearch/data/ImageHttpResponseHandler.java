package com.aleqi200.googleimagesearch.data;

import com.aleqi200.googleimagesearch.model.ImageResult;
import com.aleqi200.googleimagesearch.recycleview.RecyclerViewAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by acampelo on 10/28/15.
 */
public class ImageHttpResponseHandler extends com.loopj.android.http.JsonHttpResponseHandler {

    private RecyclerViewAdapter adapter;

    public ImageHttpResponseHandler(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }


    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

        if (statusCode == 200) {
            if (response.has("responseData")) {
                List<ImageResult> images = ImageResult.fromJsonArray(response);

                adapter.addAll(images);
                adapter.notifyDataSetChanged();
            }
        }

    }

}
