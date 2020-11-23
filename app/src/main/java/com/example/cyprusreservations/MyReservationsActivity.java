package com.example.cyprusreservations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyReservationsActivity extends AppCompatActivity {

    private String file = "reservations.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_myreservations_info);


        ImageView logo = findViewById(R.id.ivLogo);
        TextView title = findViewById(R.id.tvTitle);
        TextView desc = findViewById(R.id.tvDescription);
        TextView reservation = findViewById(R.id.tvMyReservations);

        try {
            FileInputStream fin = openFileInput(file);
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);


            int i=0;
            String Lines[] = new String[6];
            String strLine;


            while((strLine = br.readLine()) != null)
            {
                Lines[i] = strLine;
                i++;
            }

            int value = Integer.parseInt(Lines[5]);
            logo.setImageResource(value);
            title.setText(Lines[0]);
            desc.setText(Lines[1]);
            reservation.setText(Lines[2] + ", " + Lines[3] +
                    " for " + Lines[4] + " Persons");

            Toast.makeText(this, "Please wait the reservation confirm from the administrator", Toast.LENGTH_LONG).show();
            fin.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            setContentView(R.layout.activity_my_reservations);
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

}