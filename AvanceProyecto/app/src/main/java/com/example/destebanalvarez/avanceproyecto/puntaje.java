package com.example.destebanalvarez.avanceproyecto;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class puntaje extends AppCompatActivity {

    EditText leter;
   // EditText total_score;
    Bundle bundle;
    int total;
    int punta;
    String [] items;
    TextView punt;
    TextView ptotal;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);
        leter = (EditText) findViewById(R.id.letra);
        ptotal = (TextView) findViewById(R.id.punt_tot);
        punt = (TextView) findViewById(R.id.punt);

        Bundle bundle = getIntent().getExtras();
        total = bundle.getInt("total");
        punta=bundle.getInt("punt");
        mode = bundle.getString("mode");
        //total=total+punta;

        punt.setText(punta+"");
        ptotal.setText(total+"");
       // total_score.setText(total + "");
    }

    public void goGame(View v){
        Log.d("prueba2",leter.getText().toString());
        Intent intent;
        if(mode.trim().equals("alone")) {
            intent = new Intent(this, Game.class);
        } else {
            intent = new Intent(this, Servidor.class);
        }
        Bundle datos = this.getIntent().getExtras();
        items=new String[5];
        items = datos.getStringArray("items");
  //      total = datos.getInt("total");
        punta=datos.getInt("punt");
       // total=total+punta;

        intent.putExtra("items", items);
        intent.putExtra("total", total);
        intent.putExtra("mode",mode);
        //intent.putExtra("letra",leter.getText().toString().toUpperCase().trim());

        String newString = leter.getText().toString().trim().toUpperCase();


        String len=newString.length()+"";
        if(!newString.equals("") && len.equals("1")) {
            intent.putExtra("letra", newString);
            startActivity(intent);
        } else {
            Toast.makeText(puntaje.this, "Ingrese solo una letra", Toast.LENGTH_SHORT).show();
        }
        //En este punto debo enviar el puntaje total del usuario
        //La Letra Seleccionada




    }
}
