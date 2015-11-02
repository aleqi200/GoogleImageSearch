package com.aleqi200.googleimagesearch;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.aleqi200.googleimagesearch.model.ImageResult;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageResult result = getIntent().getParcelableExtra("imageResult");
        ImageView imageView = (ImageView) findViewById(R.id.img_full_image);
        setTitle(Html.fromHtml(result.getText()));
        Picasso.with(this).load(result.getFullUrl()).into(imageView);
    }
}
