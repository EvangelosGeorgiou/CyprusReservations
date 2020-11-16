package com.example.cyprusreservations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyReservationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_myreservations_info);


        Intent in  = getIntent();
        Bundle info = in.getExtras();



        String reservationMessage = info.getString("selectedDate") + ", " + info.getString("selectedTime") +
                " for " + info.getString("selectedGuests") + " Persons";

        ImageView logo = findViewById(R.id.ivLogo);
        TextView title = findViewById(R.id.tvTitle);
        TextView desc =findViewById(R.id.tvDescription);
        TextView reservation = findViewById(R.id.tvMyReservations);

        logo.setImageResource(info.getInt("logo"));
        title.setText(info.getString("title"));
        desc.setText(info.getString("description"));
        reservation.setText(reservationMessage);

    }
}