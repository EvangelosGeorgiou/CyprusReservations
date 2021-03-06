package com.example.cyprusreservations;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyprusreservations.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
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
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private View headerView;
    private String file = "CustomerRegistration.txt";
    private String notificationFile = "NotificationFile.txt";
    private String customer_session = "CustomerSession.txt";
    private boolean session = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Page");

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

        if (customerSession()){
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

                headerView = navigationView.getHeaderView(0);
                TextView tv = headerView.findViewById(R.id.tvCustomerName);
                tv.setText("Hello "+lines[0]+" !");
                fin.close();

            }catch (Exception ex){
                ex.printStackTrace();
                //Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                System.out.println("Error ---> "+ex);
            }
        }

        Menu menu = navigationView.getMenu();
        MenuItem notifications = menu.findItem(R.id.nav_notification);
        Switch notSwitch = MenuItemCompat.getActionView(menu.findItem(R.id.nav_notification)).findViewById(R.id.notificationSwitch);
        notSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(notSwitch.isChecked()){
                    boolean notification = true;
                    createNotificationFile(notification);
                }else{
                    boolean notification = false;
                    createNotificationFile(notification);

                }
            }
        });

        if(booleanNotification()){
            notSwitch.setChecked(true);
        }else{
            notSwitch.setChecked(false);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && booleanNotification()) {
            notification();
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
            Intent in = new Intent(MainActivity.this,MyReservationsActivity.class);
            startActivity(in);
        }else if(id == R.id.nav_signin){
            Intent in = new Intent(this,LoginActivity.class);
            startActivity(in);
        }else if(id == R.id.nav_rate){
            Intent in = new Intent(this,RateUsActivity.class);
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
                //setting the navigation drawer menu items
                NavigationView navigationView = findViewById(R.id.nav_view);
                Menu nav_menu = navigationView.getMenu();
                nav_menu.findItem(R.id.nav_signin).setVisible(true);
                nav_menu.findItem(R.id.nav_signout).setVisible(false);

                //empty the tvCustomerName in the nav_header
                headerView = navigationView.getHeaderView(0);
                TextView tv = headerView.findViewById(R.id.tvCustomerName);
                tv.setText("");

                //logout msg
                Toast.makeText(getApplicationContext(), "You are now logged out", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        //show dialog
        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notification(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 60);
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.setAction("android.intent.action.DISPLAY_NOTIFICATION");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
    }

    public void createNotificationFile(boolean notification){       //creating file to save switch value
        try {
            FileOutputStream fout = openFileOutput(notificationFile, 0);
            if(notification){
                fout.write("true".getBytes());
                fout.close();
            }else{
                fout.write("false".getBytes());
                fout.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("createNotification file error -->  ");
        }
    }

    public boolean booleanNotification(){
        try {
            FileInputStream fin = openFileInput(notificationFile);
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br  = new BufferedReader(isr);

            int i = 0;
            String lines[] = new String[1];
            String strLine;
            while((strLine = br.readLine()) != null){
                lines[i] = strLine;
                System.out.println("session = "+lines[i]);
                i++;
            }
            fin.close();

            if(lines[0].equals("true")){
                return true;
            }else{
                return false;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error ---> "+ex);
        }
        return true;
    }

    public boolean customerSession(){
        try {
            FileInputStream fin = openFileInput(customer_session);
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br  = new BufferedReader(isr);

            int i = 0;
            String lines[] = new String[1];
            String strLine;
            while((strLine = br.readLine()) != null){
                lines[i] = strLine;
                i++;
            }

            if(lines[0].equals("true")){
                fin.close();
                return session = true;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error ---> "+ex);
        }
        return false;
    }
}