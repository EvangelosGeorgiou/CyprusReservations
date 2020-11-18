package com.example.cyprusreservations;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.example.cyprusreservations.ui.main.PlaceholderFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyprusreservations.ui.main.SectionsPagerAdapter;


import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ReservationActivity extends AppCompatActivity {

    StoreInfo storeInfo;

    long selectedDate;
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

//    // To save the title in an invinsible text
//        TextView title_event =findViewById(R.id.idTitle);
//        title_event.setText(title);

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

        // CREATING TABLE FOR FOOTBALL EVENTS
        String[] eventsFootball = new String[5];

        eventsFootball[0] = "OMONOIA vs APOEL";
        eventsFootball[1] = "CHELSE VS LIVERPOOL";
        eventsFootball[2] = "BARCELONA VS REAL JUVENTUS";
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

        //Button for viewing the events
//        Button submit = findViewById(R.id.submit);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView d1 = findViewById(R.id.idD1);
//                TextView d2 = findViewById(R.id.idD2);
//                TextView d3 = findViewById(R.id.idD3);
//
//                TextView t1 = findViewById(R.id.idT1);
//                TextView t2 = findViewById(R.id.idT2);
//                TextView t3 = findViewById(R.id.idT3);
//
//                TextView ev1 = findViewById(R.id.idEv1);
//                TextView ev2 = findViewById(R.id.idEv2);
//                TextView ev3 = findViewById(R.id.idEv3);
//                if (title.equals("Pier One"))
//                {
//                    d1.setText(date[0]);
//                    t1.setText(time[0]);
//                    ev1.setText(eventsMusic[1]);
//                } else if (title.equals("Αγράμπελη")) {
//
//                } else if (title.equals("Η Γωνιά")) {
//
//                } else if (title.equals("Finders")) {
//
//                } else if (title.equals("Baraki Live")) {
//
//                } else if (title.equals("Confuzio Cafe")) {
//
//                }
//
//
//            }
//        });
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
    public void bookNow(View v)
    {

        Bundle info = new Bundle();


        Intent in = getIntent();
        storeInfo = (StoreInfo) in.getSerializableExtra("storeInfo");
        int logo = storeInfo.getLogo();
        String title = storeInfo.getTitle();
        String description = storeInfo.getDescription();

        info.putInt("logo", logo);
        info.putString("title",title);
        info.putString("description", description);




        EditText etDate = findViewById(R.id.etDate);
        EditText guests = findViewById(R.id.etGuests);
        RadioGroup group = findViewById(R.id.rbGroup);

        String date = etDate.getText().toString();

        info.putString("selectedDate",date);


        //SimpleDateFormat sdf = new SimpleDateFormat("MMM d");
        //String currentdate = sdf.format(new Date(cv.getDate()));

        int selection = group.getCheckedRadioButtonId();

        // long selectedDate = calendarView.getDate();
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


        info.putString("selectedTime", selectedTime);

        info.putString("selectedGuests", selectedGuests);


        Toast.makeText(getApplicationContext(), "Your reservation is pending. Please check again for confirmation ",Toast.LENGTH_LONG).show();

        Intent in1 = new Intent(this, MyReservationsActivity.class);
        in1.putExtras(info);
        startActivity(in1);
    }


}