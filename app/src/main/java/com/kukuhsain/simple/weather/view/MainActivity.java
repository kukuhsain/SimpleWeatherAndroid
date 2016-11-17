package com.kukuhsain.simple.weather.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kukuhsain.simple.weather.R;
import com.kukuhsain.simple.weather.model.remote.SimpleApi;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.schedulers.Schedulers;
import timber.log.Timber;

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
        View view = View.inflate(this, R.layout.dialog_insert_city,null);
        EditText etCity = (EditText) view.findViewById(R.id.et_city);
        new AlertDialog.Builder(this)
                .setTitle("Insert City")
                .setMessage("Please insert your current location")
                .setView(view)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    requestWeatherForecast(etCity.getText().toString());
                    dialogInterface.dismiss();
                })
                .show();
    }

    @OnClick(R.id.btn_current_location)
    public void useCurrentLocation() {
        startActivity(new Intent(this, WeatherDetailActivity.class));
    }

    private void requestWeatherForecast(String city) {
        showLoading();
        SimpleApi.getInstance()
                .getWeatherByCity(city)
                .subscribeOn(Schedulers.io())
                .subscribe(weather -> {
                    Timber.d("success...");
                    Timber.d(weather.getName());
                    Timber.d(weather.getCountryId());
                    runOnUiThread(() -> {
                        dismissLoading();
                        Toast.makeText(this, "success...", Toast.LENGTH_SHORT).show();
                    });
                    startActivity(new Intent(this, WeatherDetailActivity.class));
                }, throwable -> {
                    Timber.d("error...");
                    throwable.printStackTrace();
                    runOnUiThread(() -> {
                        dismissLoading();
                        Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();
                    });
                });
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
