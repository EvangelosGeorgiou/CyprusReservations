package com.example.cyprusreservations;

import android.app.ActionBar;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.cyprusreservations.ui.main.SectionsPagerAdapter;
import org.w3c.dom.Text;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReservationActivity extends AppCompatActivity {

    StoreInfo storeInfo;
    private String file = "reservations.txt";


    private static final String TAG ="ReservationActivity";

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

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView desc = findViewById(R.id.tvDescription);
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
        try {

            Date time1 = sdf.parse(currentTime);
            Date time2 = sdf.parse(closeHour);
            Date time3 = sdf.parse(openHour);

            // Outputs -1 as date1 is before date2
            //date1.compareTo(date2)
            // Outputs 1 as date1 is after date1
            //date2.compareTo(date1);
            // Outputs 0 as the dates are now equal
            //date1.compareTo(date2);

            if (beforeTime == time1.compareTo(time2) && afterTime == time1.compareTo(time3)) {
                button1.setText("We are Open Now");
            } else {
                button1.setText("We are Close Now");
            }


        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    public void seeEvents(View v){
        
        Intent in = getIntent();
        storeInfo = (StoreInfo) in.getSerializableExtra("storeInfo");
        String title = storeInfo.getTitle();

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
        
        //To view the titles of the table 
        TextView days = findViewById(R.id.idDate);
        days.setVisibility(View.VISIBLE);
        TextView hour = findViewById(R.id.idTime);
        hour.setVisibility(View.VISIBLE);
        TextView event = findViewById(R.id.idEvent);
        event.setVisibility(View.VISIBLE);

        //Find the cells of the table
        TextView d1 = findViewById(R.id.idD1);
        TextView d2 = findViewById(R.id.idD2);
        TextView d3 = findViewById(R.id.idD3);
        TextView d4 = findViewById(R.id.idD4);
        TextView d5 = findViewById(R.id.idD5);

        TextView t1 = findViewById(R.id.idT1);
        TextView t2 = findViewById(R.id.idT2);
        TextView t3 = findViewById(R.id.idT3);
        TextView t4 = findViewById(R.id.idT4);
        TextView t5 = findViewById(R.id.idT5);

        TextView ev1 = findViewById(R.id.idEv1);
        TextView ev2 = findViewById(R.id.idEv2);
        TextView ev3 = findViewById(R.id.idEv3);
        TextView ev4 = findViewById(R.id.idEv4);
        TextView ev5 = findViewById(R.id.idEv5);

        //Changing the values in the table
        if (title.equals("Pier One"))
        {
            //EVENT 1
            d1.setText(date[0]);
            t1.setText(time[0]);
            ev1.setText(eventsMusic[1]);

            //EVENT2
            d2.setText(date[1]);
            t2.setText(time[0]);
            ev2.setText(eventsMusic[0]);

            //EVENT 3
            d3.setText(date[2]);
            t3.setText(time[1]);
            ev3.setText(eventsMusic[2]);

            //EVENT 4
            d4.setText(date[3]);
            t4.setText(time[0]);
            ev4.setText(eventsMusic[1]);

            //EVENT5 5
            d5.setText(date[4]);
            t5.setText(time[1]);
            ev5.setText(eventsMusic[2]);
        }
        else if (title.equals("Αγράμπελη")) {
            //EVENT 1
            d1.setText(date[0]);
            t1.setText(time[0]);
            ev1.setText(eventsMusic[1]);

            //EVENT2
            d2.setText(date[1]);
            t2.setText(time[0]);
            ev2.setText(eventsMusic[0]);

            //EVENT 3
            d3.setText(date[2]);
            t3.setText(time[1]);
            ev3.setText(eventsMusic[2]);
        }
        else if (title.equals("Η Γωνιά")) {
            //EVENT 1
            d1.setText(date[3]);
            t1.setText(time[0]);
            ev1.setText(eventsMusic[1]);

            //EVENT2
            d2.setText(date[4]);
            t2.setText(time[1]);
            ev2.setText(eventsMusic[2]);
        }
        else if (title.equals("Finders")) {
            //EVENT 1
            d1.setText(date[0]);
            t1.setText(time[0]);
            ev1.setText(eventsFootball[4]);

            //EVENT2
            d2.setText(date[0]);
            t2.setText(time[2]);
            ev2.setText(eventsMusic[1]);

            //EVENT 3
            d3.setText(date[2]);
            t3.setText(time[0]);
            ev3.setText(eventsFootball[3]);

            //EVENT 4
            d4.setText(date[4]);
            t4.setText(time[0]);
            ev4.setText(eventsFootball[0]);
        }
        else if (title.equals("Baraki Live")) {
            //EVENT 1
            d1.setText(date[0]);
            t1.setText(time[0]);
            ev1.setText(eventsFootball[4]);

            //EVENT2
            d2.setText(date[1]);
            t2.setText(time[1]);
            ev2.setText(eventsMusic[0]);

            //EVENT 3
            d3.setText(date[2]);
            t3.setText(time[0]);
            ev3.setText(eventsFootball[3]);

            //EVENT 4
            d4.setText(date[4]);
            t4.setText(time[1]);
            ev4.setText(eventsMusic[0]);
        }
        else if (title.equals("Confuzio Cafe")) {
            //EVENT 1
            d1.setText(date[0]);
            t1.setText(time[0]);
            ev1.setText(eventsFootball[4]);

            //EVENT2
            d2.setText(date[1]);
            t2.setText(time[2]);
            ev2.setText(eventsFootball[1]);

            //EVENT 3
            d3.setText(date[2]);
            t3.setText(time[0]);
            ev3.setText(eventsFootball[3]);

            //EVENT 4
            d4.setText(date[4]);
            t4.setText(time[0]);
            ev4.setText(eventsFootball[0]);

            //EVENT 5
            d5.setText(date[4]);
            t5.setText(time[2]);
            ev5.setText(eventsFootball[2]);
        }
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
        info.putString("title",title);

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
    public void bookNow(View v)
    {



        Intent in = getIntent();
        storeInfo = (StoreInfo) in.getSerializableExtra("storeInfo");
        int logo = storeInfo.getLogo();
        String title = storeInfo.getTitle();
        String description = storeInfo.getDescription();



        EditText etDate = findViewById(R.id.etDate);
        EditText guests = findViewById(R.id.etGuests);
        RadioGroup group = findViewById(R.id.rbGroup);
        String date = etDate.getText().toString();


        int selection = group.getCheckedRadioButtonId();


        String selectedGuests = guests.getText().toString();
        String selectedTime = "";

        if(selection == R.id.radioButton13)
        {
            selectedTime = "09:00";
        }
        else if(selection == R.id.radioButton12)
        {
            selectedTime = "09:30";
        }
        else if(selection == R.id.radioButton11)
        {
            selectedTime = "10:00";
        }
        else if(selection == R.id.radioButton10)
        {
            selectedTime = "10:30";
        }
        else if(selection == R.id.radioButton3)
        {
            selectedTime = "11:00";
        }
        else if(selection == R.id.radioButton2)
        {
            selectedTime = "11:30";
        }
        else if(selection == R.id.radioButton4)
        {
            selectedTime = "12:00";
        }
        else if(selection == R.id.radioButton5)
        {
            selectedTime = "12:30";
        }
        else if(selection == R.id.radioButton6)
        {
            selectedTime = "13:00";
        }
        else if(selection == R.id.radioButton7)
        {
            selectedTime = "13:30";
        }
        else if(selection == R.id.radioButton8)
        {
            selectedTime = "14:00";
        }
        else if(selection == R.id.radioButton9)
        {
            selectedTime = "14:30";
        }
        else if(selection == R.id.radioButton)
        {
            selectedTime = "15:00";
        }
        else if(selection == R.id.radioButton16)
        {
            selectedTime = "15:30";
        }
        else if(selection == R.id.radioButton17)
        {
            selectedTime = "16:00";
        }
        else if(selection == R.id.radioButton18)
        {
            selectedTime = "16:30";
        }
        else if(selection == R.id.radioButton19)
        {
            selectedTime = "17:00";
        }
        else if(selection == R.id.radioButton20)
        {
            selectedTime = "17:30";
        }
        else if(selection == R.id.radioButton21)
        {
            selectedTime = "18:00";
        }
        else if(selection == R.id.radioButton22)
        {
            selectedTime = "18:30";
        }
        else if(selection == R.id.radioButton27)
        {
            selectedTime = "19:00";
        }
        else if(selection == R.id.radioButton28)
        {
            selectedTime = "19:30";
        }
        else if(selection == R.id.radioButton29)
        {
            selectedTime = "20:00";
        }
        else if(selection == R.id.radioButton30)
        {
            selectedTime = "20:30";
        }
        else if(selection == R.id.radioButton31)
        {
            selectedTime = "21:00";
        }
        else if(selection == R.id.radioButton32)
        {
            selectedTime = "21:30";
        }
        else if(selection == R.id.radioButton33)
        {
            selectedTime = "22:00";
        }
        else if(selection == R.id.radioButton34)
        {
            selectedTime = "22:30";
        }
        else if(selection == R.id.radioButton35)
        {
            selectedTime = "23:00";
        }

        String title1 = title + "\n";
        String desc = description + "\n";
        String date1 = date + "\n";
        String time = selectedTime + "\n";
        String guests1 = selectedGuests + "\n";
        String logo1 = String.valueOf(logo);

        boolean b_guests = true, b_hour= true , b_date=true;


        if(selectedGuests.equals("")){
            guests.setError("This field can't be empty");
            b_guests = false;
        }
        if(date.equals("Name"))
        {
            Toast.makeText(this, "Please select your date for the reservation", Toast.LENGTH_SHORT).show();
            b_date=false;
        }

        if (group.getCheckedRadioButtonId() == -1)
        {
            b_hour = false;
            Toast.makeText(this, "Please check your time for the reservation", Toast.LENGTH_SHORT).show();
        }

        if (b_guests && b_hour && b_date) {
            try {
                FileOutputStream fout = openFileOutput(file, 0);


                fout.write(title1.getBytes());
                fout.write(desc.getBytes());
                fout.write(date1.getBytes());
                fout.write(time.getBytes());
                fout.write(guests1.getBytes());
                fout.write(logo1.getBytes());

                fout.close();
                Toast.makeText(getApplicationContext(), "Your reservation is pending. Please check the progress from MyReservations page!",Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        Intent in1 = new Intent(this, MainActivity.class);
        startActivity(in1);
        }
    }


}