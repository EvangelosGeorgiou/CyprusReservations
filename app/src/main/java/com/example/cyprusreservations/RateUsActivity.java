package com.example.cyprusreservations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RateUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);

        //BACK ARROW
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override   //BACK ARROW
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void rateus(View v){
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        double rating = ratingBar.getRating();

        if(rating <= 2.5){

            ratingBar.setIsIndicator(true);

            TextView tv = findViewById(R.id.textView18);
            tv.setVisibility(View.VISIBLE);

            EditText et = findViewById(R.id.etBadComments);
            et.setVisibility(View.VISIBLE);

            Button button = findViewById(R.id.btnRateus);
            button.setVisibility(View.GONE);

            Button button1 = findViewById(R.id.btnSubmitEt);
            button1.setVisibility(View.VISIBLE);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Thank you for your feedback we will try to make our app better.", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(RateUsActivity.this, MainActivity.class);
                    startActivity(in);
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Thank you for your rating :) ", Toast.LENGTH_LONG).show();
            Intent in = new Intent(RateUsActivity.this, MainActivity.class);
            startActivity(in);
        }
    }
}