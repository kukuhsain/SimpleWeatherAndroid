package com.kukuhsain.simple.weather.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.kukuhsain.simple.weather.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kukuh on 16/11/16.
 */

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_insert_city)
    public void insertCity() {
        new AlertDialog.Builder(this)
                .setTitle("Insert City")
                .setMessage("Please insert your current location")
                .setView(R.layout.dialog_insert_city)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();
    }

    @OnClick(R.id.btn_current_location)
    public void useCurrentLocation() {
        startActivity(new Intent(this, WeatherListActivity.class));
    }

    private void showLoading() {
        if (progressDialog != null) {
            progressDialog.show();
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }
    }

    private void dismissLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
