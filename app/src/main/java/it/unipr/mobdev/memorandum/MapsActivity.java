package it.unipr.mobdev.memorandum;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.unipr.mobdev.memorandum.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private MemoList list;
    ExecutorService mThreadPool = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);


        // check sulle permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

            // il listener di locatio Manager con il provider
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,5,this::onLocationChanged);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        list = MemoList.getInstance();

        // ottengo il geocoder
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault())  ;
        
        for (int i = 0; i < list.size(); ++i) {
            Memo m = list.getMemoAtIndex(i);
            String name = null;
            String description = null;
            String title = null;

            // mostrati sulla mappa solo se sono attivi
            if (m.isActive()) {
                name = list.getMemoAtIndex(i).getPlace();
                description = list.getMemoAtIndex(i).getDescription();
                title = list.getMemoAtIndex(i).getTitle();
            }

            try {
                Iterator<Address> addresses = geocoder.getFromLocationName(name, 1).iterator();
                if (addresses != null) {
                    while (addresses.hasNext()) {
                        Address loc = addresses.next();
                        double lat = loc.getLatitude();
                        double lng = loc.getLongitude();
                        LatLng pos = new LatLng(lat, lng);
                        addMarker(pos, title, description);
                        addCircle(pos, 1000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addMarker(LatLng latLng, String title, String description) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title).snippet(description);
        mMap.addMarker(markerOptions);
    }

    private void addCircle(LatLng latLng, float radius) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255, 255, 0,0));
        circleOptions.fillColor(Color.argb(64, 255, 0,0));
        circleOptions.strokeWidth(4);
        mMap.addCircle(circleOptions);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
