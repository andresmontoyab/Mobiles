package com.example.destebanalvarez.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Spinner longBreak;
    Spinner shortBreak;
    String lonBr;
    String shorBr;
    String Volumen;
    CheckBox debugMode;
    CheckBox vibration;
    int shortB;
    int longB;
    boolean v1;
    boolean v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        debugMode = (CheckBox) findViewById(R.id.checkBox2);
        vibration = (CheckBox) findViewById(R.id.checkBox);

        Spinner shortBreak = (Spinner) findViewById(R.id.shortbreak);
        String[] valores = {"5 minutos","4 minutos","3 minutos"};
        shortBreak.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        shortBreak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
               shorBr=(String) adapterView.getItemAtPosition(position);
                shortB = Integer.parseInt(shorBr.split(" ")[0]);

                //Toast.makeText(adapterView.getContext(), , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });

        Spinner longBreak = (Spinner) findViewById(R.id.longbreak);
        String[] valoresL = {"10 minutos","15 minutos","20 minutos"};
        longBreak.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresL));
        longBreak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //
                lonBr = (String) adapterView.getItemAtPosition(position);
                longB = Integer.parseInt(lonBr.split(" ")[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });




        Spinner spinner_vol = (Spinner) findViewById(R.id.spinner_vol);
        String[] valoresVol = {"MUDO","MEDIO","ALTO"};
        spinner_vol.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresVol));
        spinner_vol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //
                Volumen=(String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });


    }

    public void configuracion(View v){


        if(Volumen.equals("MUDO")){
            v1=false;
            v2=false;
        }
        if(Volumen.equals("MEDIO")){
            v1=true;
            v2=false;
        }
        if(Volumen.equals("ALTO")){
            v1=true;
            v2=true;
        }

        Log.d("iji","Desde el setting "+Volumen+"  "+v1+" "+v2);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("vibration", vibration.isChecked());
        intent.putExtra("debug", debugMode.isChecked());
        intent.putExtra("short", shortB);
        intent.putExtra("long", longB);
        intent.putExtra("v1", v1);
        intent.putExtra("v2", v2);
        startActivity(intent);

    }
}
