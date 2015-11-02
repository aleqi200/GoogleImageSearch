package com.aleqi200.googleimagesearch.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by acampelo on 11/1/15.
 */
public class ImageSearchSettings implements Parcelable {
    private String siteFilter;
    private String colorFilter;
    private String imageType;
    private String imageSize;

    public ImageSearchSettings(String siteFilter, String colorFilter, String imageType, String imageSize) {
        this.siteFilter = siteFilter;
        this.colorFilter = colorFilter;
        this.imageType = imageType;
        this.imageSize = imageSize;
    }

    public String getSiteFilter() {
        return siteFilter;
    }

    public String getColorFilter() {
        return colorFilter;
    }

    public String getImageType() {
        return imageType;
    }

    public String getImageSize() {
        return imageSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.siteFilter);
        dest.writeString(this.colorFilter);
        dest.writeString(this.imageType);
        dest.writeString(this.imageSize);
    }

    private ImageSearchSettings(Parcel in) {
        this.siteFilter = in.readString();
        this.colorFilter = in.readString();
        this.imageType = in.readString();
        this.imageSize = in.readString();
    }

    public static final Parcelable.Creator<ImageSearchSettings> CREATOR = new Parcelable.Creator<ImageSearchSettings>() {
        public ImageSearchSettings createFromParcel(Parcel source) {
            return new ImageSearchSettings(source);
        }

        public ImageSearchSettings[] newArray(int size) {
            return new ImageSearchSettings[size];
        }
    };
}
