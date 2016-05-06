package com.example.destebanalvarez.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Inicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
    }

    public void goMenu(View v){
        Intent i=new Intent(this,Menu.class);
        i.putExtra("vibration", false);
        i.putExtra("debug", false);
        startActivity(i);


    }
}
