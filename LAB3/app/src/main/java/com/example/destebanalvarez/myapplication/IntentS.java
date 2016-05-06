package com.example.destebanalvarez.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by andres.montoyab on 15/04/16.
 */
public class IntentS extends IntentService {

    int counter=10000;


    public IntentS() {
        super("IntetnS");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("", counter+"");

                    synchronized (this) {
                        try {
                            do{
                                Thread.sleep(1000);
                                counter=counter-1000;
                                Log.d("oeeeeeeee", counter+"");


                            }while(counter!=0);



                        } catch (Exception e) {
                        }

                    }

            }
        });


    }
}
