package com.example.cyprusreservations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent in = getIntent();
        Bundle info = in.getExtras();
        TextView text = findViewById(R.id.textAddress);
        text.setText(info.getString("address"));

        String closeHour = info.getString("closeHour");
        if(closeHour.equals("26:00"))
        {
            closeHour = "02:00";
        }
        else if(closeHour.equals("26:30"))
        {
            closeHour ="02:30";
        }
        TextView text1 = findViewById(R.id.tvMonday);
        text1.setText(info.getString("openHour") +" - "+ closeHour);
        TextView text2 = findViewById(R.id.tvTuesday);
        text2.setText(info.getString("openHour") +" - "+ closeHour );
        TextView text3 = findViewById(R.id.tvWednesday);
        text3.setText(info.getString("openHour") +" - "+ closeHour );
        TextView text4 = findViewById(R.id.tvThursday);
        text4.setText(info.getString("openHour") +" - "+ closeHour );
        TextView text5 = findViewById(R.id.tvFriday);
        text5.setText(info.getString("openHour") +" - "+ closeHour );
        TextView text6 = findViewById(R.id.tvSaturday);
        text6.setText(info.getString("openHour") +" - "+ closeHour );
        TextView text7 = findViewById(R.id.tvSunday);
        text7.setText(info.getString("openHour") +" - "+ closeHour);


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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent in = getIntent();
        Bundle info = in.getExtras();

        String location =info.getString("marker");
        LatLng coords = info.getParcelable("coordinates");

        if(location.equals("Pier One"))
        {
            mMap.addMarker(new MarkerOptions().position(coords).title("Pier One"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 15));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        }
        else if(location.equals("Αγράμπελη"))
        {
            mMap.addMarker(new MarkerOptions().position(coords).title("Αγράμπελη"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 15));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else if(location.equals("Η Γωνιά"))
        {
            mMap.addMarker(new MarkerOptions().position(coords).title("Η Γωνιά"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords,15));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else if(location.equals("Finders"))
        {
            mMap.addMarker(new MarkerOptions().position(coords).title("Finders"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords,15));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else if(location.equals("Baraki Live"))
        {
            mMap.addMarker(new MarkerOptions().position(coords).title("Baraki Live"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords,15));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else if(location.equals("Confuzio Cafe"))
        {
            mMap.addMarker(new MarkerOptions().position(coords).title("Confuzio Cafe"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords,15));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }


    }
}