package com.example.cyprusreservations;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
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

}