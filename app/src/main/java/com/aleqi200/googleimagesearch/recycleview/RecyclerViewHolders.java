package com.aleqi200.googleimagesearch.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aleqi200.googleimagesearch.ImageDisplayActivity;
import com.aleqi200.googleimagesearch.R;
import com.aleqi200.googleimagesearch.model.ImageResult;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageResult imageResult;
    public TextView imageText;
    public ImageView imageView;
    private Context context;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageText = (TextView)itemView.findViewById(R.id.img_text);
        imageView = (ImageView)itemView.findViewById(R.id.img_item);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, ImageDisplayActivity.class);
        intent.putExtra("imageResult", imageResult);
        context.startActivity(intent);
    }

    public void setImageResult(ImageResult imageResult) {
        this.imageResult = imageResult;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}