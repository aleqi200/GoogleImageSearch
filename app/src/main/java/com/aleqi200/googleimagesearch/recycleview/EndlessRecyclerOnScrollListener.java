package com.aleqi200.googleimagesearch.recycleview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aleqi200.googleimagesearch.model.ImageRetriever;
import com.aleqi200.googleimagesearch.model.ImageSearchSettings;

public class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    private String queryValue;
    private RecyclerViewAdapter rcAdapter;
    private ImageSearchSettings settingsObject;
    public static int MAX_PAGES = 8;

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private GridLayoutManager layoutManager;

    public EndlessRecyclerOnScrollListener(GridLayoutManager linearLayoutManager, String queryValue,
                                           RecyclerViewAdapter rcAdapter, ImageSearchSettings settingsObject) {
        this.layoutManager = linearLayoutManager;
        this.queryValue = queryValue;
        this.rcAdapter = rcAdapter;
        this.settingsObject = settingsObject;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (current_page >= MAX_PAGES) {
            return;
        }
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            current_page++;

            onLoadMore(current_page);

            loading = true;
        }
    }

    public void onLoadMore(int current_page) {
        ImageRetriever.search(queryValue, rcAdapter, settingsObject, current_page);
    };
}