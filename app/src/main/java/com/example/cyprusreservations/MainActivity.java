package com.example.cyprusreservations;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyprusreservations.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null ){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.nav_host_fragment,new HomeFragment());
            fragmentTransaction.commit();
        }

        Intent in = getIntent();
        String LOGIN_STATUS = in.getStringExtra(LoginActivity.LOGIN_STATUS);

        if(LOGIN_STATUS ==null){
            LOGIN_STATUS = "";
        } if (LOGIN_STATUS.equals("true")){
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_signin).setVisible(false);
            nav_menu.findItem(R.id.nav_signout).setVisible(true);

            MenuItem signout = nav_menu.findItem(R.id.nav_signout);
            signout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    logout();
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.nav_home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment,new HomeFragment());
            fragmentTransaction.commit();
        }else if(id == R.id.nav_events){
            Intent in = new Intent(this, EventActivity.class);
            startActivity(in);
        }else if(id == R.id.nav_myreservations){
            Intent in = new Intent(this,MyReservationsActivity.class);
            startActivity(in);
        }else if(id == R.id.nav_signin){
            Intent in = new Intent(this,LoginActivity.class);
            startActivity(in);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");

        //creating CANCEL button
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //creating YES button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NavigationView navigationView = findViewById(R.id.nav_view);
                Menu nav_menu = navigationView.getMenu();
                nav_menu.findItem(R.id.nav_signin).setVisible(true);
                nav_menu.findItem(R.id.nav_signout).setVisible(false);
                Toast.makeText(getApplicationContext(), "You are now logged out", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        //show dialog
        builder.show();
    }
}