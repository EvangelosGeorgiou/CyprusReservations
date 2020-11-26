package com.example.cyprusreservations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

        //START SERVICE
        Intent in = new Intent(this,MyService.class);
        startService(in);

        //VIEW TABLE PIER ONE
        //VIEW DATE
        TextView d1 = findViewById(R.id.d1);
        TextView d2 = findViewById(R.id.d2);
        TextView d3 = findViewById(R.id.d3);
        TextView d4 = findViewById(R.id.d4);
        TextView d5 = findViewById(R.id.d5);
        // VIEW TIMES
        TextView t1 = findViewById(R.id.t1);
        TextView t2 = findViewById(R.id.t2);
        TextView t3 = findViewById(R.id.t3);
        TextView t4 = findViewById(R.id.t4);
        TextView t5 = findViewById(R.id.t5);
        //VIEW EVENTS
        TextView e1 = findViewById(R.id.e1);
        TextView e2 = findViewById(R.id.e2);
        TextView e3 = findViewById(R.id.e3);
        TextView e4 = findViewById(R.id.e4);
        TextView e5 = findViewById(R.id.e5);
        //-----------------------------------
        //VIEW TABLE AGRAMPELI
        //VIEW DATE
        TextView d6 = findViewById(R.id.d6);
        TextView d7 = findViewById(R.id.d7);
        TextView d8 = findViewById(R.id.d8);
        // VIEW TIMES
        TextView t6 = findViewById(R.id.t6);
        TextView t7 = findViewById(R.id.t7);
        TextView t8 = findViewById(R.id.t8);
        //VIEW EVENTS
        TextView e6 = findViewById(R.id.e6);
        TextView e7 = findViewById(R.id.e7);
        TextView e8 = findViewById(R.id.e8);
        //---------------------------------------
        //VIEW TABLE GONIA
        //VIEW DATE
        TextView d9 = findViewById(R.id.d9);
        TextView d10 = findViewById(R.id.d10);
        // VIEW TIMES
        TextView t9 = findViewById(R.id.t9);
        TextView t10 = findViewById(R.id.t10);
        //VIEW EVENTS
        TextView e9 = findViewById(R.id.e9);
        TextView e10 = findViewById(R.id.e10);
        //---------------------------------------
        //VIEW TABLE FINDERS
        //VIEW DATE
        TextView d11 = findViewById(R.id.d11);
        TextView d12 = findViewById(R.id.d12);
        TextView d13 = findViewById(R.id.d13);
        TextView d14 = findViewById(R.id.d14);
        // VIEW TIMES
        TextView t11 = findViewById(R.id.t11);
        TextView t12 = findViewById(R.id.t12);
        TextView t13 = findViewById(R.id.t13);
        TextView t14 = findViewById(R.id.t14);
        //VIEW EVENTS
        TextView e11 = findViewById(R.id.e11);
        TextView e12 = findViewById(R.id.e12);
        TextView e13 = findViewById(R.id.e13);
        TextView e14 = findViewById(R.id.e14);
        //---------------------------------------
        //VIEW TABLE BARAKI
        //VIEW DATE
        TextView d15 = findViewById(R.id.d15);
        TextView d16 = findViewById(R.id.d16);
        TextView d17 = findViewById(R.id.d17);
        TextView d18 = findViewById(R.id.d18);
        // VIEW TIMES
        TextView t15 = findViewById(R.id.t15);
        TextView t16 = findViewById(R.id.t16);
        TextView t17 = findViewById(R.id.t17);
        TextView t18 = findViewById(R.id.t18);
        //VIEW EVENTS
        TextView e15 = findViewById(R.id.e15);
        TextView e16 = findViewById(R.id.e16);
        TextView e17 = findViewById(R.id.e17);
        TextView e18 = findViewById(R.id.e18);
        //---------------------------------------
        //VIEW TABLE CONFUZIO
        //VIEW DATE
        TextView d19= findViewById(R.id.d19);
        TextView d20= findViewById(R.id.d20);
        TextView d21= findViewById(R.id.d21);
        TextView d22= findViewById(R.id.d22);
        TextView d23= findViewById(R.id.d23);
        // VIEW TIMES
        TextView t19 = findViewById(R.id.t19);
        TextView t20 = findViewById(R.id.t20);
        TextView t21 = findViewById(R.id.t21);
        TextView t22 = findViewById(R.id.t22);
        TextView t23 = findViewById(R.id.t23);
        //VIEW EVENTS
        TextView e19 = findViewById(R.id.e19);
        TextView e20 = findViewById(R.id.e20);
        TextView e21 = findViewById(R.id.e21);
        TextView e22 = findViewById(R.id.e22);
        TextView e23 = findViewById(R.id.e23);
        //---------------------------------------

        // CREATING TABLE FOR FOOTBALL EVENTS
        String[] eventsFootball = new String[5];

        eventsFootball[0] = "OMO vs APO";
        eventsFootball[1] = "CHE VS LIV";
        eventsFootball[2] = "BAR VS JUV";
        eventsFootball[3] = "APO VS AEL";
        eventsFootball[4] = "AEK VS DOK";

        // CREATING TABLE FOR MUSIC EVENTS
        String[] eventsMusic = new String[3];

        eventsMusic[0] = "LIVE";
        eventsMusic[1] = "KARAOKE";
        eventsMusic[2] = "DJ";

        //CREATING TABLE FOR DATE
        String[] date =new String[5];

        date[0] = "05/12/20";
        date[1] = "07/12/20";
        date[2] = "15/12/20";
        date[3] = "20/12/20";
        date[4] = "27/12/20";

        //CREATING TIME TABLE
        String[] time = new String[3];

        time[0] = "20:00";
        time[1] = "21:00";
        time[2] = "22:00";

        //EVENTS FOR PIER ONE
        //EVENT 1
        d1.setText(date[0]);
        t1.setText(time[0]);
        e1.setText(eventsMusic[1]);

        //EVENT2
        d2.setText(date[1]);
        t2.setText(time[0]);
        e2.setText(eventsMusic[0]);

        //EVENT 3
        d3.setText(date[2]);
        t3.setText(time[1]);
        e3.setText(eventsMusic[2]);

        //EVENT 4
        d4.setText(date[3]);
        t4.setText(time[0]);
        e4.setText(eventsMusic[1]);

        //EVENT5 5
        d5.setText(date[4]);
        t5.setText(time[1]);
        e5.setText(eventsMusic[2]);
        //-------------------------------
        //EVENTS FOR AGRAMPELI
        //EVENT 1
        d6.setText(date[0]);
        t6.setText(time[0]);
        e6.setText(eventsMusic[1]);

        //EVENT2
        d7.setText(date[1]);
        t7.setText(time[0]);
        e7.setText(eventsMusic[0]);

        //EVENT 3
        d8.setText(date[2]);
        t8.setText(time[1]);
        e8.setText(eventsMusic[2]);
        //-------------------------------
        //EVENTS FOR GONIA
        //EVENT 1
        d9.setText(date[3]);
        t9.setText(time[0]);
        e9.setText(eventsMusic[1]);

        //EVENT2
        d10.setText(date[4]);
        t10.setText(time[1]);
        e10.setText(eventsMusic[2]);
        //-------------------------------
        //EVENTS FOR FINDERS
        //EVENT 1
        d11.setText(date[0]);
        t11.setText(time[0]);
        e11.setText(eventsFootball[4]);

        //EVENT2
        d12.setText(date[0]);
        t12.setText(time[2]);
        e12.setText(eventsMusic[1]);

        //EVENT 3
        d13.setText(date[2]);
        t13.setText(time[0]);
        e13.setText(eventsFootball[3]);

        //EVENT 4
        d14.setText(date[4]);
        t14.setText(time[0]);
        e14.setText(eventsFootball[0]);
        //-------------------------------
        //EVENTS FOR BARAKI LIVE
        //EVENT 1
        d15.setText(date[0]);
        t15.setText(time[0]);
        e15.setText(eventsFootball[4]);

        //EVENT2
        d16.setText(date[1]);
        t16.setText(time[1]);
        e16.setText(eventsMusic[0]);

        //EVENT 3
        d17.setText(date[2]);
        t17.setText(time[0]);
        e17.setText(eventsFootball[3]);

        //EVENT 4
        d18.setText(date[4]);
        t18.setText(time[1]);
        e18.setText(eventsMusic[0]);
        //-------------------------------
        //EVENTS FOR CONFUZIO
        //EVENT 1
        d19.setText(date[0]);
        t19.setText(time[0]);
        e19.setText(eventsFootball[4]);

        //EVENT2
        d20.setText(date[1]);
        t20.setText(time[2]);
        e20.setText(eventsFootball[1]);

        //EVENT 3
        d21.setText(date[2]);
        t21.setText(time[0]);
        e21.setText(eventsFootball[3]);

        //EVENT 4
        d22.setText(date[4]);
        t22.setText(time[0]);
        e22.setText(eventsFootball[0]);

        //EVENT 5
        d23.setText(date[4]);
        t23.setText(time[2]);
        e23.setText(eventsFootball[2]);
        //-------------------------------
    }

    @Override   //BACK ARROW
    public boolean onSupportNavigateUp() {
        Intent in2 = new Intent(this,MyService.class);
        stopService(in2);

        MyService s = new MyService();
        Toast.makeText(getApplicationContext(),"You have spent "+s.getCounter()+ " seconds in the second activity",Toast.LENGTH_LONG).show();
        onBackPressed();
        return true;
    }

}