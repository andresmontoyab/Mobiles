package com.example.destebanalvarez.avanceproyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterWords extends AppCompatActivity {

    EditText animal;
    EditText lastname;
    EditText food;
    EditText frut;
    EditText name;
    EditText country;
    EditText city;
    EditText color;
    EditText proffesion;
    String animalStr;
    String lastnameStr;
    String foodStr;
    String frutStr;
    String nameStr;
    String countryStr;
    String cityStr;
    String colorStr;
    String proffesionStr;
    DBD manager;
    Tabla_Animal tablaAnimal;
    Tabla_Apellido tablaApellido;
    Tabla_Comida tablaComida;
    Tabla_Frutas tablaFrutas;
    Tabla_Nombre tablaNombre;
    Tabla_Pais tablaPais;
    Table_Ciudad tableCiudad;
    Table_Color tableColor;
    Table_Profesion tableProfesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_words);

        manager = new DBD(this);

        animal = (EditText) findViewById(R.id.animalR);
        lastname = (EditText) findViewById(R.id.apellido);
        food = (EditText) findViewById(R.id.comida);
        frut = (EditText) findViewById(R.id.frutas);
        name = (EditText) findViewById(R.id.nombre);
        country = (EditText) findViewById(R.id.pais);
        city = (EditText) findViewById(R.id.ciudad);
        color = (EditText) findViewById(R.id.color);
        proffesion = (EditText) findViewById(R.id.profesion);
    }

    //metodo para insertar
    public void onclick(View view) {
        animalStr = animal.getText().toString().toUpperCase();
        lastnameStr = lastname.getText().toString().toUpperCase();
        foodStr = food.getText().toString().toUpperCase();
        frutStr = frut.getText().toString().toUpperCase();
        nameStr = name.getText().toString().toUpperCase();
        countryStr = country.getText().toString().toUpperCase();
        cityStr = city.getText().toString().toUpperCase();
        colorStr = color.getText().toString().toUpperCase();
        proffesionStr = proffesion.getText().toString().toUpperCase();

        if(!animalStr.equals("")) {
            if(!manager.consultarAnimal(animalStr)) {
                tablaAnimal = new Tabla_Animal();
                tablaAnimal.animal = animalStr;
                manager.insertarAnimal(tablaAnimal);
                animal.setText("");
                Toast.makeText(RegisterWords.this, "ANIMAL REGISTRADO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "EL ANIMAL YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
        if(!lastnameStr.equals("")) {
            if(!manager.consultarApellido(lastnameStr)) {
                tablaApellido = new Tabla_Apellido();
                tablaApellido.apellido = lastnameStr;
                manager.insertarApellido(tablaApellido);
                lastname.setText("");
                Toast.makeText(RegisterWords.this, "APELLIDO REGISTRADO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "EL APELLIDO YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
        if(!foodStr.equals("")) {
            if(!manager.consultarComida(foodStr)) {
                tablaComida = new Tabla_Comida();
                tablaComida.comida = foodStr;
                manager.insertarComida(tablaComida);
                food.setText("");
                Toast.makeText(RegisterWords.this, "COMIDA REGISTRADA", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "LA COMIDA YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
        if(!frutStr.equals("")) {
            if(!manager.consultarFruta(frutStr)) {
                tablaFrutas = new Tabla_Frutas();
                tablaFrutas.frutas = frutStr;
                manager.insertarFrutas(tablaFrutas);
                frut.setText("");
                Toast.makeText(RegisterWords.this, "FRUTA REGISTRADA", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "LA FRUTA YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
        if(!nameStr.equals("")) {
            if(!manager.consultarNombre(nameStr)) {
                tablaNombre = new Tabla_Nombre();
                tablaNombre.nombre = nameStr;
                manager.insertarNombre(tablaNombre);
                name.setText("");
                Toast.makeText(RegisterWords.this, "NOMBRE REGISTRADO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "EL NOMBRE YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
        if(!countryStr.equals("")) {
            if(!manager.consultarPais(countryStr)) {
                tablaPais = new Tabla_Pais();
                tablaPais.pais = countryStr;
                manager.insertarPais(tablaPais);
                country.setText("");
                Toast.makeText(RegisterWords.this, "PAIS REGISTRADO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "EL PAIS YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
        if(!cityStr.equals("")) {
            if(!manager.consultarCiudad(cityStr)) {
                tableCiudad = new Table_Ciudad();
                tableCiudad.ciudad = cityStr;
                manager.insertarCiudad(tableCiudad);
                city.setText("");
                Toast.makeText(RegisterWords.this, "CIUDAD REGISTRADA", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "LA CIUDAD YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
        if(!colorStr.equals("")) {
            if(!manager.consultarColor(colorStr)) {
                tableColor = new Table_Color();
                tableColor.color = colorStr;
                manager.insertarColor(tableColor);
                color.setText("");
                Toast.makeText(RegisterWords.this, "COLOR REGISTRADO", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "EL COLOR YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
        if(!proffesionStr.equals("")) {
            if(!manager.consultarJob(proffesionStr)) {
                tableProfesion = new Table_Profesion();
                tableProfesion.job = proffesionStr;
                manager.insertarJob(tableProfesion);
                proffesion.setText("");
                Toast.makeText(RegisterWords.this, "PROFESIÓN REGISTRADA", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterWords.this, "LA PROFESIÓN YA EXISTE", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
