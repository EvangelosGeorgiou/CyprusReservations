package com.example.cyprusreservations;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Timer timer = new Timer();
    private static int count;
    EventActivity events = new EventActivity();
    private static final int i = 10;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public int onStartCommand(Intent in,int flag,int startId)
    {
        count = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                count++;
                int t = count % i;
                System.out.println("Counter =" + count);
                if (t  == 0){
                    events.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"If you are intrested dont forget to make a reservation",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        },0,1000);

        return super.onStartCommand(in, flag, startId);
    }

    public int getCounter()

    {
        return count;
    }

    public void onDestroy(){
        if(timer != null)
        {
            timer.cancel();
        }
        super.onDestroy();
    }
}