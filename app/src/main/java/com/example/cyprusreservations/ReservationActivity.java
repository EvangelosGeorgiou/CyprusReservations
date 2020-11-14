package com.example.cyprusreservations;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyprusreservations.ui.main.SectionsPagerAdapter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public class ReservationActivity extends AppCompatActivity {

    StoreInfo storeInfo;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
               Toast.makeText(getApplicationContext(), "Please fill all the fields and then press book now", Toast.LENGTH_LONG).show();
            }
        });

        Intent in = getIntent();
        storeInfo = (StoreInfo) in.getSerializableExtra("storeInfo");
        String title = storeInfo.getTitle();
        String description = storeInfo.getDescription();
        float rate = storeInfo.getRating();
        int logo = storeInfo.getLogo();
        String address = storeInfo.getAddress();
        String openHour = storeInfo.getOpenHour();
        String closeHour = storeInfo.getCloseHour();


        TextView tvTitle =findViewById(R.id.tvTitle);
        TextView desc =findViewById(R.id.tvDescription);
        RatingBar rating = findViewById(R.id.rbRating);
        ImageView l = findViewById(R.id.ivLogo);
        Button button = findViewById(R.id.locationButton);
        Button button1 = findViewById(R.id.button4);


        l.setImageResource(logo);
        rating.setRating(rate);
        tvTitle.setText(title);
        desc.setText(description);
        button.setText(address);



        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());

        int beforeTime = -1;
        int afterTime = 1;
        int equalTime = 0;
        try{

            Date time1 = sdf.parse(currentTime);
            Date time2 = sdf.parse(closeHour);
            Date time3 = sdf.parse(openHour);

            // Outputs -1 as date1 is before date2
            //date1.compareTo(date2)
            // Outputs 1 as date1 is after date1
            //date2.compareTo(date1);
            // Outputs 0 as the dates are now equal
            //date1.compareTo(date2);

            if ( beforeTime == time1.compareTo(time2) && afterTime == time1.compareTo(time3) )
            {
                button1.setText("We are Open Now");
            }
            else
            {
                button1.setText("We are Close Now");
            }


        } catch (ParseException e){
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }

        //BACK ARROW
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override   //BACK ARROW
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToLocation(View v)
    {
        Bundle info = new Bundle();

        Intent in = getIntent();
        storeInfo = (StoreInfo) in.getSerializableExtra("storeInfo");
        String address = storeInfo.getAddress();
        String title = storeInfo.getTitle();
        String openHour = storeInfo.getOpenHour();
        String closeHour = storeInfo.getCloseHour();
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

        info.putString("openHour",openHour);
        info.putString("closeHour",closeHour);


        Intent in1 = new Intent(this, LocationActivity.class);
        in1.putExtras(info);
        startActivity(in1);

    }

}