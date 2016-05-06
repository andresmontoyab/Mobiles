package com.example.destebanalvarez.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private TextView tv;
    private TextView title;
    private Intent service;
    private boolean vibration;
    private boolean debug;
    private Bundle bundle;
    private boolean flag;
    private int shortB;
    private int longB;
    private int i;
    private boolean v1;
    private boolean v2;
    private String volumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.counter);
        title = (TextView) findViewById(R.id.state);
        i=0;
        tv.setText("25:00");
        title.setText("TRABAJANDO");
        longB = 10;
        shortB = 5;
        debug = false;
        vibration = false;
        volumen = "ALTO";
        bundle = getIntent().getExtras();
        if(bundle != null) {
            vibration = bundle.getBoolean("vibration");
            debug = bundle.getBoolean("debug");
            shortB = bundle.getInt("short");
            longB = bundle.getInt("long");
            volumen = bundle.getString("volumen");
            v1 = bundle.getBoolean("v1");
            v2 = bundle.getBoolean("v2");
            if(debug) {
                tv.setText("25");
            }
        }
        service = new Intent(this, MyService.class);
        flag = false;
        Log.d("flag god", flag+"");
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent); // or whatever method used to update your GUI fields
        }
    };

    public void start(View view) {
        stopService(service);
        service.putExtra("vibration", vibration);
        service.putExtra("debug", debug);
        service.putExtra("v1", v1);
        service.putExtra("v2", v2);
        service.putExtra("myCounter", (long)1500);
        startService(service);
        Toast.makeText(MainActivity.this, "Empieza el pomodoro", Toast.LENGTH_SHORT).show();
        registerReceiver(br, new IntentFilter(MyService.COUNTDOWN_BR));
    }

    public void stop(View view) {
        stopService(service);
    }

    public void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long seconds = intent.getLongExtra("countdown", 0);
            int sec = (int)seconds % 60;
            int min = (int) seconds / 60;
            if(min == 0) {
                tv.setText(seconds + "");
            } else {
                tv.setText(min + ":" + sec);
            }
            String text = "";
            if(tv.getText().toString().equals("0")) {
                long num = 0;
                if(!flag) {
                    i++;
                    if(i < 4) {
                        num = shortB * 60;
                        text = "Es hora de un pequeño descanso";
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    } else {
                        num = longB * 60;
                        text = "Es hora de un descanso largo";
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                        i = 0;
                    }
                    title.setText("DESCANSANDO");
                    flag = true;
                } else {
                    num = 1500;
                    flag = false;
                    text = "Es momento de volver al trabajo";
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    title.setText("TRABAJANDO");
                }

                Notification notification = new NotificationCompat.Builder(this)
                        .setCategory(Notification.CATEGORY_PROMO)
                        .setContentTitle("POMODORO")
                        .setContentText(text)
                        .setSmallIcon(R.drawable.tomato)
                        .setAutoCancel(true)
                        .setVisibility(1).build();
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(10, notification);

                stopService(service);
                service.putExtra("vibration", vibration);
                service.putExtra("debug", debug);
                service.putExtra("myCounter", num);
                service.putExtra("flag", flag);
                service.putExtra("volumen", volumen);
                startService(service);
            }

        }
    }
}
