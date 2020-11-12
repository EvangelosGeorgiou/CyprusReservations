package com.example.cyprusreservations;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cyprusreservations.ui.main.SectionsPagerAdapter;

public class ReservationActivity extends AppCompatActivity {

    StoreInfo storeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent in = getIntent();
        storeInfo = (StoreInfo) in.getSerializableExtra("storeInfo");
        String title = storeInfo.getTitle();
        String description = storeInfo.getDescription();
        float rate = storeInfo.getRating();
        int logo = storeInfo.getLogo();
        String address = storeInfo.getAddress();


        TextView tvTitle =findViewById(R.id.tvTitle);
        TextView desc =findViewById(R.id.tvDescription);
        RatingBar rating = findViewById(R.id.rbRating);
        ImageView l = findViewById(R.id.ivLogo);
        Button button = findViewById(R.id.locationButton);


        l.setImageResource(logo);
        rating.setRating(rate);
        tvTitle.setText(title);
        desc.setText(description);
        button.setText(address);




    }
    public void goToLocation(View v)
    {
        Bundle info = new Bundle();

        Intent in = getIntent();
        storeInfo = (StoreInfo) in.getSerializableExtra("storeInfo");
        String address = storeInfo.getAddress();
        String title = storeInfo.getTitle();
        LatLng coords;
        info.putString("address" , address);

        if (title.equals("Pier One"))
        {
            coords = new LatLng(34.670962,33.043532);
            info.putString("marker", "Pier One");
            info.putParcelable("coordinates", coords);
        }
        else if(title.equals("Αγράμπελη"))
        {
            coords = new LatLng(34.676100,33.043359);
            info.putString("marker", "Αγράμπελη");
            info.putParcelable("coordinates", coords);
        }
        else if(title.equals("Η Γωνιά"))
        {
            coords = new LatLng(34.676124,33.043521);
            info.putString("marker", "Η Γωνιά");
            info.putParcelable("coordinates", coords);
        }
        else if(title.equals("Finders"))
        {
            coords = new LatLng(35.162131,33.317455);
            info.putString("marker", "Finders");
            info.putParcelable("coordinates", coords);
        }
        else if(title.equals("Baraki Live"))
        {
            coords = new LatLng(35.163115,33.358232);
            info.putString("marker", "Baraki Live");
            info.putParcelable("coordinates", coords);
        }
        else if(title.equals("Confuzio Cafe"))
        {
            coords = new LatLng(34.686193,33.034457);
            info.putString("marker", "Confuzio Cafe");
            info.putParcelable("coordinates", coords);
        }




        Intent in1 = new Intent(this, LocationActivity.class);
        in1.putExtras(info);
        startActivity(in1);

    }

}