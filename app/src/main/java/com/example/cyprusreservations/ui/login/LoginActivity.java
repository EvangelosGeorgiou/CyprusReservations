package com.example.cyprusreservations.ui.login;

import android.app.Activity;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyprusreservations.HomeFragment;
import com.example.cyprusreservations.MainActivity;
import com.example.cyprusreservations.R;
import com.example.cyprusreservations.RegisterActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    private String file = "CustomerRegistration.txt";
    private String customer_session = "CustomerSession.txt";
    private LoginViewModel loginViewModel;
    public static final String LOGIN_STATUS = "";

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);



        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                //loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    //passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        //BACK ARROW
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void updateUiWithUser(LoggedInUserView model) {
        //GETTING THE DATA OUT OF THE FILE
        try {
            FileInputStream fin = openFileInput(file);
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br  = new BufferedReader(isr);

            int i = 0;
            String lines[] = new String[5];
            String strLine;
            while((strLine = br.readLine()) != null){
                lines[i] = strLine;
                i++;
            }

            EditText usernameEditText = findViewById(R.id.username);
            EditText passwordEditText = findViewById(R.id.password);
            String email = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if(email.equals(lines[2]) && password.equals(lines[3])){
                Intent in = new Intent(this, MainActivity.class);
                Toast.makeText(getApplicationContext(), "Welcome "+lines[0] , Toast.LENGTH_SHORT).show();
                customer_session();
                startActivity(in);
            }else{
                Toast.makeText(getApplicationContext(), "Email or Password is incorrect! Please try again", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,LoginActivity.class));
            }

            fin.close();

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error ---> "+ex);
        }

    }

    public void addCredentials(View v){
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        CheckBox cb = findViewById(R.id.cbCredentials);
        if(cb.isChecked()){
            try {
                FileInputStream fin = openFileInput(file);
                DataInputStream din = new DataInputStream(fin);
                InputStreamReader isr = new InputStreamReader(din);
                BufferedReader br  = new BufferedReader(isr);

                int i = 0;
                String lines[] = new String[5];
                String strLine;
                while((strLine = br.readLine()) != null){
                    lines[i] = strLine;
                    i++;
                }

                usernameEditText.setText(lines[2]);
                passwordEditText.setText(lines[3]);

                fin.close();

            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Error ---> "+ex);
            }
        }else{
            usernameEditText.setText("");
            passwordEditText.setText("");
        }
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public void register(View v) {
        Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(in);
    }

    @Override   //BACK ARROW
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void customer_session(){
        try {
            FileOutputStream fout = openFileOutput(customer_session, 0);
            fout.write("true".getBytes());
            fout.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
            System.out.println("Error code:--> " + ex);
        }
    }
}