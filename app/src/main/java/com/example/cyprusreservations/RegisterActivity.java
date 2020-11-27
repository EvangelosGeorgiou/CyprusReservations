package com.example.cyprusreservations;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyprusreservations.ui.login.LoginActivity;
import com.example.cyprusreservations.ui.login.LoginViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText etname, etsurname, etemail, etpassword, etconfPassword, etphone;
    LoginViewModel lvm;
    private String file = "CustomerRegistration.txt";

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //TODO BULLET POINTS
        SpannableString msg1 = new SpannableString("Password must be between 8-15 characters");
        SpannableString msg2 = new SpannableString("Password must contain at least 1 Capital Letter");
        SpannableString msg3 = new SpannableString("Password must contain at least 1 Number");
        SpannableString msg4 = new SpannableString("Password must contain at least 1 Special Character");
        msg1.setSpan(new BulletSpan(10, Color.BLACK, 8), 1, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msg2.setSpan(new BulletSpan(10, Color.BLACK, 8), 1, 46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msg3.setSpan(new BulletSpan(10, Color.BLACK, 8), 1, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msg4.setSpan(new BulletSpan(10, Color.BLACK, 8), 1, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        String toastMsg = msg1 + "\n" + msg2 + "\n" + msg3 + "\n" + msg4;
        FloatingActionButton fab = findViewById(R.id.LoginFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
        });

        //BACK ARROW
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override   //BACK ARROW
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void registerbtn(View view) {
        etname = findViewById(R.id.etName);
        etsurname = findViewById(R.id.etSurname);
        etemail = findViewById(R.id.etEmail);
        etpassword = findViewById(R.id.etPass);
        etconfPassword = findViewById(R.id.etConfirmPass);
        etphone = findViewById(R.id.etPhone);

        String name = etname.getText().toString().trim() + "\n";
        String surname = etsurname.getText().toString().trim() + "\n";
        String email = etemail.getText().toString().trim() + "\n";
        String password = etpassword.getText().toString().trim() + "\n";
        String confPassword = etconfPassword.getText().toString().trim()+"\n";
        String phone = etphone.getText().toString().trim() + "\n";

        boolean b_email = true, b_pass = true, b_pass2 = true, b_name = true;

        if(name == null){
            etname.setError("This field can't be empty");
            b_name = false;
        }
        if (!email.contains("@gmail.com")) {
            etemail.setError("Email must be a gmail account.");
            b_email = false;
        }
        System.out.println("password 1 = " + password + "\npassword 2 = " + confPassword);
        if (!password.equals(confPassword)) {
            etconfPassword.setError("The two passwords do not match.");
            b_pass = false;
        }

        if (!isPasswordValid(password)) {
            etpassword.setError("Password type is incorrect. Please check the FAB button for more!");
            b_pass2 = false;

        }

        if (b_email && b_name && b_pass && b_pass2) {
            try {
                //deleteFile(file);
                FileOutputStream fout = openFileOutput(file, 0);
                fout.write(name.getBytes());
                fout.write(surname.getBytes());
                fout.write(email.getBytes());
                fout.write(password.getBytes());
                fout.write(phone.getBytes());
                fout.close();
                Toast.makeText(getApplicationContext(), "You have successfully register to the Cyprus Reservations! ", Toast.LENGTH_SHORT).show();


            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                System.out.println("Error code:--> " + ex);
            }
            Intent in = new Intent(this, LoginActivity.class);
            startActivity(in);
        }
    }

    public boolean isPasswordValid(String password) {

        if (password.trim().length() > 8 && password.trim().length() < 15 && hasNumber(password) && hasSpecialChar(password) && hasCapitalLetter(password) ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasNumber(String pass) {
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(pass.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasSpecialChar(String s) {
        //int counter =0;
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean hasCapitalLetter(String pass) {
        if (pass.equals(pass.toLowerCase())) {
            return false;
        } else {
            return true;
        }
    }
}