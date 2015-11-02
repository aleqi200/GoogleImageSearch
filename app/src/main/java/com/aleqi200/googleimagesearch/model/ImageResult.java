package com.aleqi200.googleimagesearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acampelo on 10/31/15.
 */
public class ImageResult implements Parcelable {
    private String fullUrl;
    private String thumbnailUrl;
    private String text;

    public ImageResult(String fullUrl, String text, String thumbnailUrl) {
        this.fullUrl = fullUrl;
        this.text = text;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getText() {
        return text;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public static List<ImageResult> fromJsonArray(JSONObject response) {
        List<ImageResult> images = new ArrayList<>();
        try {
            JSONArray items = response.getJSONObject("responseData").getJSONArray("results");
            for (int i = 0; i < items.length(); i++) {
                JSONObject object = (JSONObject) items.get(i);
                images.add(ImageResult.fromJsonObject(object));
            }
        } catch (JSONException e) {
            return images;
        }
        return images;
    }

    private static ImageResult fromJsonObject(JSONObject object) throws JSONException {
        String caption = object.getString("title");
        String url = object.getString("url");
        String tbUrl = object.getString("tbUrl");
        return new ImageResult(url, caption, tbUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullUrl);
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.text);
    }

    private ImageResult(Parcel in) {
        this.fullUrl = in.readString();
        this.thumbnailUrl = in.readString();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<ImageResult> CREATOR = new Parcelable.Creator<ImageResult>() {
        public ImageResult createFromParcel(Parcel source) {
            return new ImageResult(source);
        }

        public ImageResult[] newArray(int size) {
            return new ImageResult[size];
        }
    };
}
