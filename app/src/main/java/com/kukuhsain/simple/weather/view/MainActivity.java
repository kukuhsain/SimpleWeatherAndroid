package com.kukuhsain.simple.weather.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.kukuhsain.simple.weather.R;
import com.kukuhsain.simple.weather.model.remote.SimpleApi;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kukuh on 16/11/16.
 */

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private final int RC_LOCATION = 1;
    private ProgressDialog progressDialog;
    private String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private GoogleApiClient googleApiClient;
    private Location currentLocation;
    private LocationRequest locationRequest;

    private long UPDATE_INTERVAL = 10000;
    private long FASTEST_INTERVAL = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EasyPermissions.requestPermissions(this, "Request Permission for User Location", RC_LOCATION, perms);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @OnClick(R.id.btn_insert_city)
    public void insertCity() {
        View view = View.inflate(this, R.layout.dialog_insert_city, null);
        EditText etCity = (EditText) view.findViewById(R.id.et_city);
        new AlertDialog.Builder(this)
                .setTitle("Insert City")
                .setMessage("Please insert your current location")
                .setView(view)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    requestWeatherForecastByCity(etCity.getText().toString());
                    dialogInterface.dismiss();
                })
                .show();
    }

    @OnClick(R.id.btn_current_location)
    public void useCurrentLocation() {
        if (currentLocation != null) {
            requestWeatherByLatLon(currentLocation.getLatitude(), currentLocation.getLongitude());
        } else {
            Toast.makeText(this, "Please enable accessing location from your setting", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestWeatherForecastByCity(String city) {
        showLoading();
        SimpleApi.getInstance()
                .getWeatherByCity(city)
                .subscribeOn(Schedulers.io())
                .subscribe(weather -> {
                    Intent intent = new Intent(this, WeatherDetailActivity.class);
                    intent.putExtra("weather", (new Gson()).toJson(weather));
                    startActivity(intent);
                    runOnUiThread(() -> {
                        dismissLoading();
                        Toast.makeText(this, "success...", Toast.LENGTH_SHORT).show();
                    });
                }, throwable -> {
                    Timber.d("error...");
                    throwable.printStackTrace();
                    runOnUiThread(() -> {
                        dismissLoading();
                        Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();
                    });
                });
    }

    private void requestWeatherByLatLon(double latitude, double longitude) {
        showLoading();
        SimpleApi.getInstance()
                .getWeatherByLatLon(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .subscribe(weather -> {
                    Intent intent = new Intent(this, WeatherDetailActivity.class);
                    intent.putExtra("weather", (new Gson()).toJson(weather));
                    startActivity(intent);
                    runOnUiThread(() -> {
                        dismissLoading();
                        Toast.makeText(this, "success...", Toast.LENGTH_SHORT).show();
                    });
                }, throwable -> {
                    Timber.d("error...");
                    throwable.printStackTrace();
                    runOnUiThread(() -> {
                        dismissLoading();
                        Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();
                    });
                });
    }

    @AfterPermissionGranted(RC_LOCATION)
    private void getUserLocation() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            if (currentLocation != null) {
                updateLocation();
            }
        } else {
            EasyPermissions.requestPermissions(this, "Request Permission for User Location", RC_LOCATION, perms);
        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (currentLocation == null) {
            updateLocation();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void updateLocation() {
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
    }
}
