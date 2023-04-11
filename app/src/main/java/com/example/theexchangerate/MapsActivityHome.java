package com.example.theexchangerate;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivityHome extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // creating a variable
    // for search view.
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_home);

        // initializing our search view.
        searchView = findViewById(R.id.idSearchView);

        // Obtain the SupportMapFragment and get notified
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // adding on query listener for our search view.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String location = searchView.getQuery().toString();

                // below line is to create a list of address
                // where we will store the list of all address.
                List<Address> addressList = null;

                // checking if the entered location is null or not.
                if (location != null || location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(MapsActivityHome.this);
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    Address address = addressList.get(0);

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    // on below line we are adding marker to that position.
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                    // below line is to animate camera to that position.
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        // at last we calling our map fragment to update.
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng capital_indo = new LatLng( -6.200000, 106.816666);
        LatLng capital_jpn = new LatLng( 36.2048, 138.2529);
        LatLng capital_sokor = new LatLng( 37.532600, 127.024612);
        LatLng capital_thai = new LatLng( 13.7563309, 100.5017651);
        LatLng capital_uk = new LatLng( 51.509865, -0.118092);
        LatLng capital_us = new LatLng( 47.751076, -120.740135);

        mMap.addMarker(new MarkerOptions().position(capital_indo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title("Indonesia Capital City: Jakarta"));
        mMap.addMarker(new MarkerOptions().position(capital_us).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("US Capital City: Washington D.C"));
        mMap.addMarker(new MarkerOptions().position(capital_uk).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("UK Capital City: London"));
        mMap.addMarker(new MarkerOptions().position(capital_sokor).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title("South Korean Capital City: Seoul"));
        mMap.addMarker(new MarkerOptions().position(capital_thai).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).title("Thailand Capital City: Bangkok"));
        mMap.addMarker(new MarkerOptions().position(capital_jpn).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Japan Capital City: Tokyo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(capital_indo));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(capital_us));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(capital_uk));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(capital_sokor));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(capital_thai));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(capital_jpn));
        enableMyLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            case R.id.satelite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Mengecek permisi
    private void enableMyLocation(){
        //Kalau uda set locasi di map
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }
        //Kalau belum request permission
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Menyesuasikan request code di fungsi enableMyLocation
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED){
                    enableMyLocation();
                    break;
                }
        }
    }
}