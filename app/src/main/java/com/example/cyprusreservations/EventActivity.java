package com.example.cyprusreservations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar_events);

        //BACK ARROW
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override   //BACK ARROW
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}