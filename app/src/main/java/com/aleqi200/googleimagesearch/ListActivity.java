package com.aleqi200.googleimagesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aleqi200.googleimagesearch.model.ImageResult;
import com.aleqi200.googleimagesearch.model.ImageRetriever;
import com.aleqi200.googleimagesearch.model.ImageSearchSettings;
import com.aleqi200.googleimagesearch.recycleview.EndlessRecyclerOnScrollListener;
import com.aleqi200.googleimagesearch.recycleview.RecyclerViewAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements Constants {

    private RecyclerViewAdapter rcAdapter;
    private GridLayoutManager lLayout;
    private TextView txtSearch;
    private String queryValue = "android";
    private EndlessRecyclerOnScrollListener listener;
    private RecyclerView rView;
    private ImageSearchSettings settingsObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        txtSearch = (TextView) findViewById(R.id.txtQuery);

        ArrayList<ImageResult> images = new ArrayList<>();

        lLayout = new GridLayoutManager(ListActivity.this, 3);

        rView = (RecyclerView) findViewById(R.id.rv);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rcAdapter = new RecyclerViewAdapter(ListActivity.this, images);
        rView.setAdapter(rcAdapter);

    }

    private void getAllItemListUsingQuery() {
        if (queryValue != null && !queryValue.trim().isEmpty()) {
            if (listener != null) {
                rView.removeOnScrollListener(listener);
            }
            listener = new EndlessRecyclerOnScrollListener(lLayout, queryValue, rcAdapter, settingsObject);
            rView.addOnScrollListener(listener);

            rcAdapter.clear();
            ImageRetriever.search(queryValue, rcAdapter, settingsObject);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ListActivity.this, SettingsActivity.class);
            startActivityForResult(intent, SETTINGS_REQUEST);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchValue(View view) {
        if (view instanceof Button && R.id.btnSearch == view.getId()){
            queryValue = txtSearch.getText().toString();
            getAllItemListUsingQuery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST && resultCode == RESULT_OK) {
            this.settingsObject = data.getParcelableExtra(SETTINGS_OBJECT);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
