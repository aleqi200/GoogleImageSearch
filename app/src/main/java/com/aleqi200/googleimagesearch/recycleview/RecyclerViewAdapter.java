package com.aleqi200.googleimagesearch.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleqi200.googleimagesearch.ListActivity;
import com.aleqi200.googleimagesearch.R;
import com.aleqi200.googleimagesearch.model.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acampelo on 10/31/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<ImageResult> itemList = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ListActivity listActivity, List<ImageResult> rowListItem) {
        this.context = listActivity;
        this.itemList = rowListItem;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.setImageResult(itemList.get(position));
        holder.setContext(context);
        holder.imageText.setText(Html.fromHtml(itemList.get(position).getText()));
        Picasso.with(context).load(itemList.get(position).getThumbnailUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        itemList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<ImageResult> list) {
        itemList.addAll(list);
        notifyDataSetChanged();
    }
}
