package com.example.destebanalvarez.myapplication;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Toast;

/**
 * Created by juan.ospina3 on 14/04/16.
 */
public class MyService extends Service {


    private long seconds = 1500;
    private boolean vibration;
    private boolean debug;
    public static final String COUNTDOWN_BR = "myCounterdown";
    private Intent bi = new Intent(COUNTDOWN_BR);
    private Thread hilo;
    private MediaPlayer reproductor;
    private boolean flag;
    private int lim;
    private String vol;
    private float volumen;

    private boolean v1;
    private boolean v2;
    public MyService() {
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        seconds = intent.getLongExtra("myCounter", 0);
        vibration = intent.getBooleanExtra("vibration", false);
        debug = intent.getBooleanExtra("debug", false);
        flag = intent.getBooleanExtra("flag", false);
        v1 = intent.getBooleanExtra("v1", true);
        v2 = intent.getBooleanExtra("v2", false);
        //vol="BAJO";
        Log.d("iji",v1+" "+v2);
        if((v1==true)&&(v2==true)) {
            vol = "ALTO";
        }
        if((v1==false)&&(v2==false)) {
            vol = "MUDO";
        }
        if((v1==true)&&(v2==false)) {
            vol = "MEDIO";
        }
       // vol = intent.getStringExtra("volumen",);
        if(!flag) {
            lim = 10;
        } else {
            lim = 2;
        }
        if(vol.equals("ALTO")) {
            volumen = 1f;
        } else if(vol.equals("MEDIO")) {
            volumen = 0.2f;
        } else {
            volumen = 0.0f;
        }
        if(debug) {
            seconds /= 60;
        }
        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("", seconds + "");

                synchronized (this) {
                    try {
                        do {

                            if(seconds == lim) {
                                if(!flag) {
                                    startLoop(volumen);
                                    if(vibration) {
                                        star_vibration(3000);
                                    }
                                } else {
                                    startMario(volumen);
                                    if(vibration) {
                                        star_vibration(500);
                                    }
                                }
                            }

                            Thread.sleep(1000);
                            seconds = seconds - 1;

                            Log.d("oeeeeeeee", seconds + "");
                            bi.putExtra("countdown", seconds);
                            sendBroadcast(bi);

                        } while (seconds > 1);

                        Thread.sleep(1000);
                        seconds = seconds - 1;

                        bi.putExtra("countdown", seconds);
                        sendBroadcast(bi);


                    } catch (Exception e) {
                    }

                }

            }

        });
        hilo.start();
        return START_STICKY;
    }

    private void startLoop(float volu) {
        reproductor = MediaPlayer.create(this, R.raw.star_wars);
        reproductor.start();
        reproductor.setVolume(volu, volu);

    }

    private void startMario(float volu) {
        reproductor = MediaPlayer.create(this, R.raw.mario_warning);
        reproductor.start();
        reproductor.setVolume(volu, volu);
    }

    private void stop_loop(){
        if(reproductor != null) {
            reproductor.stop();
        }
    }

    private void star_vibration(int length){
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(length);



    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        hilo.interrupt();
        stop_loop();
        super.onDestroy();
    }
}
