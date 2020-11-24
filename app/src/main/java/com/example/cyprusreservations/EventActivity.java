package com.example.cyprusreservations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cyprusreservations.ui.main.SectionsPagerAdapter;

public class EventActivity extends AppCompatActivity {
    StoreInfo storeInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar_events);

        //BACK ARROW
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // CREATING TABLE FOR FOOTBALL EVENTS
        String[] eventsFootball = new String[5];

        eventsFootball[0] = "OMONOIA vs APOEL";
        eventsFootball[1] = "CHELSE VS LIVERPOOL";
        eventsFootball[2] = "BARCELONA VS JUVENTUS";
        eventsFootball[3] = "APOLLON VS AEL";
        eventsFootball[4] = "AEK VS DOKSA";

        // CREATING TABLE FOR MUSIC EVENTS
        String[] eventsMusic = new String[3];

        eventsMusic[0] = "LIVE";
        eventsMusic[1] = "KARAOKE";
        eventsMusic[2] = "DJ";

        //CREATING TABLE FOR DATE
        String[] date =new String[5];

        date[0] = "5/12/20";
        date[1] = "7/12/20";
        date[2] = "15/12/20";
        date[3] = "20/12/20";
        date[4] = "27/12/20";

        //CREATING TIME TABLE
        String[] time = new String[3];

        time[0] = "20:00";
        time[1] = "21:00";
        time[2] = "22:00";

    }

    @Override   //BACK ARROW
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}