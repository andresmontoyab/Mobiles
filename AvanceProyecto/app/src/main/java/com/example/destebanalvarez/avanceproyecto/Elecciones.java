package com.example.destebanalvarez.avanceproyecto;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Elecciones extends AppCompatActivity {

    Button button;

    String[] items;
    String mode;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elecciones2);
        a=5;
        items=new String[5];
        Intent i = getIntent();
        mode = i.getStringExtra("mode");



    }

    public void goPunt(View v){

       if(a==0) {
           Intent i;
           i = new Intent(this, puntaje.class);
           i.putExtra("items", items);
           int punt=0;
           int total=0;
           i.putExtra("punt",punt);
           i.putExtra("total",total);
           i.putExtra("mode", mode);
           a=5;
           button = (Button) findViewById(R.id.apellido);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           button = (Button) findViewById(R.id.animal);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           button = (Button) findViewById(R.id.frutas);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           button = (Button) findViewById(R.id.nombre);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           button = (Button) findViewById(R.id.pais);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           button = (Button) findViewById(R.id.ciudad);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           button = (Button) findViewById(R.id.color);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           button = (Button) findViewById(R.id.profesion);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           button = (Button) findViewById(R.id.comida);
           button.setEnabled(true);
           button.setBackgroundColor(Color.parseColor("#795548"));
           //En este Punto debo enviar Las opciones que el usuario a decidio jugar.
           startActivity(i);
       }
        else{
           Toast.makeText(Elecciones.this, "Aun le faltan "+a+" por decidir", Toast.LENGTH_SHORT).show();

       }

    }

    public void color(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.color);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "color";

        }
    }

    public void ciudad(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.ciudad);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "ciudad";
        }
    }

    public void job(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.profesion);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "job";
        }
    }

    public void nombre(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.nombre);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "nombre";
        }
    }

    public void apellido(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.apellido);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "apellido";
        }
    }

    public void animal(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.animal);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "animal";
        }
    }

    public void pais(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.pais);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "pais";
        }
    }

    public void frutas(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.frutas);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "frutas";
        }
    }

    public void comida(View v){
        if(a>0) {
            button = (Button) findViewById(R.id.comida);
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#D7CCC8"));
            a = a - 1;
            items[a] = "comida";
        }
    }
}
