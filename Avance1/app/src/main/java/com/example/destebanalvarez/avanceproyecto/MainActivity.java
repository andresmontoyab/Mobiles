package com.example.destebanalvarez.avanceproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText a;
    EditText b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a=(EditText)findViewById(R.id.algo);

    }

    public void goElecciones(View v){

        Intent intent =new Intent(this,Elecciones.class);
        startActivity(intent);

    }

    public void goRegistrar(View v){

        Intent intent =new Intent(this,RegisterWords.class);
        startActivity(intent);

    }
}
