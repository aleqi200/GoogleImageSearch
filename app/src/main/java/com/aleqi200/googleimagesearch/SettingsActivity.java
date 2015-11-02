package com.aleqi200.googleimagesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.aleqi200.googleimagesearch.model.ImageSearchSettings;

public class SettingsActivity extends AppCompatActivity implements Constants {

    private Spinner spinnerImgSize;
    private Spinner spinnerImgColor;
    private Spinner spinnerImgType;
    private EditText txtSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        spinnerImgColor = (Spinner) findViewById(R.id.spin_color_filter);
        setValues(spinnerImgColor, R.array.imgcolor);
        spinnerImgSize = (Spinner) findViewById(R.id.spin_img_size);
        setValues(spinnerImgSize, R.array.imgsz);
        spinnerImgType = (Spinner) findViewById(R.id.spin_img_type);
        setValues(spinnerImgType, R.array.imgtype);
        txtSite = (EditText) findViewById(R.id.txt_site_filter);
    }

    private void setValues(Spinner spinner, int arrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayId, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void saveSettings(View view) {
        if (view instanceof Button && view.getId() == R.id.btn_save_settings) {
            String imgColorSetting = spinnerImgColor.getSelectedItem().toString().trim();
            String imgTypeSetting = spinnerImgType.getSelectedItem().toString().trim();
            String imgSizeSetting = spinnerImgSize.getSelectedItem().toString().trim();
            String site = txtSite.getText().toString().trim();
            ImageSearchSettings searchSettings = new ImageSearchSettings(site, imgColorSetting, imgTypeSetting, imgSizeSetting);
            Intent data = new Intent();
            data.putExtra(SETTINGS_OBJECT, searchSettings);
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
