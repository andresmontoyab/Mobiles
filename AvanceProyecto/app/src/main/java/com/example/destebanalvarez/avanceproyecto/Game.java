package com.example.destebanalvarez.avanceproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Game extends AppCompatActivity {

    TextView[] itemsTxtView;
    public static EditText text1;
    public static EditText text2;
    public static EditText text3;
    public static EditText text4;
    public static EditText text5;
    TextView letra;
    TextView puntaje;
    String[] items;
    public static ArrayList<String> items2;
    public static String leta;
    int total;
    int punta;
    String[] vectAnim, vectApellido, vectComida, vectFrut, vectNombre, vectPais, vectCiudad, vectColor, vectProf;
    public static DBD manager;
    Tabla_Animal animal;
    Tabla_Apellido apellido;
    Tabla_Comida comida;
    Tabla_Frutas fruta;
    Tabla_Nombre nombre;
    Tabla_Pais pais;
    Table_Ciudad ciudad;
    Table_Color color;
    Table_Profesion profesion;
    Boolean[] flag;
    public static int mipuntaje;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle bundle = getIntent().getExtras();
        items = bundle.getStringArray("items");
        leta = bundle.getString("letra");
        Log.d("pruena",leta);
        total=bundle.getInt("total");
        mode = bundle.getString("mode");
        items2 = new ArrayList<String>(Arrays.asList(items));
        itemsTxtView = new TextView[5];
        itemsTxtView[0] = (TextView) findViewById(R.id.Item1);
        itemsTxtView[1] = (TextView) findViewById(R.id.Item2);
        itemsTxtView[2] = (TextView) findViewById(R.id.Item3);
        itemsTxtView[3] = (TextView) findViewById(R.id.Item4);
        itemsTxtView[4] = (TextView) findViewById(R.id.Item5);
        itemsTxtView[0].setText(items[4]);
        itemsTxtView[1].setText(items[3]);
        itemsTxtView[2].setText(items[2]);
        itemsTxtView[3].setText(items[1]);
        itemsTxtView[4].setText(items[0]);


        text1 = (EditText) findViewById(R.id.text1);
        text2 = (EditText) findViewById(R.id.text2);
        text3 = (EditText) findViewById(R.id.text3);
        text4 = (EditText) findViewById(R.id.text4);
        text5 = (EditText) findViewById(R.id.text5);
        text1.setHint(items[4]);
        text2.setHint(items[3]);
        text3.setHint(items[2]);
        text4.setHint(items[1]);
        text5.setHint(items[0]);

        letra=(TextView) findViewById(R.id.showletra);
        flag = new Boolean[9];
        letra.setText(leta);
        puntaje=(TextView) findViewById(R.id.showpunt);
        puntaje.setText(total+"");
        vectAnim = new String[]{"ARDILLA", "VACA", "CERDO", "TORTUGA", "RANA", "SAPO", "PERRO", "GATO", "TORO"};
        vectApellido = new String[]{"OPINA", "HERRERA", "ALVAREZ", "MUÑETON", "MONTOYA", "BUSTAMANTE", "GIRALDO", "MORENO", "SUAREZ"};
        vectComida = new String[]{"MONDONGO", "BOCACHICO", "PIZZA", "ARROZ", "HUEVO", "SAPOTE", "CHORIZO", "AREPA", "SACHILCHA"};
        vectFrut = new String[]{"MANZANA", "NARANJA", "TOMATE", "AGUACATE", "MARACUYA", "GUANABANA", "PERA", "GRANADILLA", "MANGO"};
        vectNombre = new String[]{"ANDRES", "JUAN", "FELIPE", "PABLO", "PAOLA", "ALEJANDRO", "WILLIAM", "CAMILO", "ANDREA"};
        vectPais = new String[]{"COLOMBIA", "ARGENTINA", "ESTADOS UNIDOS", "ALEMANIA", "BOLIVIA", "VENEZUELA", "ECUADOR", "CUBA", "INGLATERRA"};
        vectCiudad = new String[]{"MEDELLIN", "BOGOTA", "CARTAGENA", "AMALFI", "RIONEGRO", "ITAGUI", "BELLO", "BARBOSA", "CUCUTA"};
        vectColor = new String[]{"AZUL", "BLANCO", "ROJO", "AMARILLO", "VERDE", "NEGRO", "BLANCO", "ROSA", "NARANJA"};
        vectProf = new String[]{"INGENIERO", "MEDICO", "ARTISTA", "DEPORTISTA", "FISICO", "MATEMATICO", "ADMINISTRADOR", "ABOGADO", "ALBAÑIL"};
        manager = new DBD(this);
        animal = new Tabla_Animal();
        apellido = new Tabla_Apellido();
        comida = new Tabla_Comida();
        fruta = new Tabla_Frutas();
        nombre = new Tabla_Nombre();
        pais = new Tabla_Pais();
        ciudad = new Table_Ciudad();
        color = new Table_Color();
        profesion = new Table_Profesion();
        for(int i = 0; i < vectAnim.length; i++) {
            animal.animal = vectAnim[i];
            manager.insertarAnimal(animal);
            apellido.apellido = vectApellido[i];
            manager.insertarApellido(apellido);
            comida.comida = vectComida[i];
            manager.insertarComida(comida);
            fruta.frutas = vectFrut[i];
            manager.insertarFrutas(fruta);
            nombre.nombre = vectNombre[i];
            manager.insertarNombre(nombre);
            pais.pais = vectPais[i];
            manager.insertarPais(pais);
            ciudad.ciudad = vectCiudad[i];
            manager.insertarCiudad(ciudad);
            color.color = vectColor[i];
            manager.insertarColor(color);
            profesion.job = vectProf[i];
            manager.insertarJob(profesion);
        }
    }

    public void goPuntaje(View v) {

    /*
        // ACa emp0iezo a sumar en punta y al final punta mas total sera el nuevo total, debere retornr ambos datos
        punta=20;
        total=total+punta;
        String text1str = text1.getText().toString().trim().toUpperCase();
        String text2str = text2.getText().toString().trim().toUpperCase();
        String text3str = text3.getText().toString().trim().toUpperCase();
        String text4str = text4.getText().toString().trim().toUpperCase();
        String text5str = text5.getText().toString().trim().toUpperCase();


        if((!text1str.equals("")) && (!text2str.equals("")) && (!text3str.equals("")) &&(!text4str.equals(""))  && (!text5str.equals("")))  {
            if(text1str.substring(0,1).equals(leta)
                    &&(text2str.substring(0,1).equals(leta))
                    &&(text3str.substring(0,1).equals(leta))
                    &&(text4str.substring(0,1).equals(leta))
                    &&text5str.substring(0,1).equals(leta)) {

                Log.d("oeee....", text1str+":"+validateAnswer(text1str) + " " + text2str+":"+validateAnswer(text2str) + " " + text3str+":"+validateAnswer(text3str)
                        + " " + text4str+":"+validateAnswer(text4str) + " " + text5str+":"+validateAnswer(text5str));

                if(validateAnswer(text1str) && validateAnswer(text2str) && validateAnswer(text3str)
                        && validateAnswer(text4str) && validateAnswer(text5str)) {

                    Bundle bundle = getIntent().getExtras();
                    items = bundle.getStringArray("items");

                    Intent a = new Intent(this, puntaje.class);
                    a.putExtra("total", total);
                    a.putExtra("punt",punta);
                    a.putExtra("items",items);
                    startActivity(a);

                } else {
                    Toast.makeText(Game.this, "Palabra NO Existe", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(Game.this, "Palabra Erronea", Toast.LENGTH_SHORT).show();

            }



            //En esta parte debere primero validar que todos los textos ingresados inicien por la letra escogida anteriormente
            // Validar que las opciones seleccionadas esten en la base de respuestas validas
            //enviar el puntaje obtenido dada la conclucion de las preguntas + el puntaje que tubo anteriormente
            //se debe enviar tsodo por medio de bundle o un put



        } else {
            Toast.makeText(Game.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
        }


    */
/*
        Intent i=new Intent(Game.this, Cliente.class);
        i.putExtra("funcion","cerrar");
        startActivity(i);*/

        String text1str = text1.getText().toString().trim().toUpperCase();
        String text2str = text2.getText().toString().trim().toUpperCase();
        String text3str = text3.getText().toString().trim().toUpperCase();
        String text4str = text4.getText().toString().trim().toUpperCase();
        String text5str = text5.getText().toString().trim().toUpperCase();

        Log.d("oeee....", text1str+":"+validateAnswer(text1str) + " " + text2str+":"+validateAnswer(text2str) + " " + text3str+":"+validateAnswer(text3str)
                + " " + text4str+":"+validateAnswer(text4str) + " " + text5str+":"+validateAnswer(text5str));
        mipuntaje = 0;

        if(!text1str.equals("") && validateAnswer(text1str)) {
            mipuntaje += 20;
        }
        if(!text2str.equals("") && validateAnswer(text2str)) {
            mipuntaje += 20;
        }
        if(!text3str.equals("") && validateAnswer(text3str)) {
            mipuntaje += 20;
        }
        if(!text4str.equals("") && validateAnswer(text4str)) {
            mipuntaje += 20;
        }
        if(!text5str.equals("") && validateAnswer(text5str)) {
            mipuntaje += 20;
        }

        Cliente cliente=new Cliente();
        cliente.iniciarCliente(Cliente.IP,"cerrar");

        Intent i=new Intent(Game.this,puntaje.class);
        i.putExtra("items",items);
        i.putExtra("punt",mipuntaje);
        i.putExtra("mode", "alone");
        startActivity(i);



    }
    public static String[] obtener_resultados(){
        String values[]=new String[5];
        values[0] = text1.getText().toString().trim().toUpperCase();
        values[1] = text2.getText().toString().trim().toUpperCase();
        values[2] =  text3.getText().toString().trim().toUpperCase();
        values[3] = text4.getText().toString().trim().toUpperCase();
        values[4] = text5.getText().toString().trim().toUpperCase();

        return values;

    }

    public static int makeScoreThisGame(String[] valor){
        int puntaje=0;

        Log.d("prueba",leta);
        if((valor[0]!=null)&&(!valor[0].equals(""))&&(valor[0].substring(0,1).equals(leta))&&(validateAnswer(valor[0]))){
            Log.d("prueba","puntaje1 : "+makeScoreThisGame(obtener_resultados()));
            puntaje=puntaje+20;

        }
        if((valor[1]!=null)&&(!valor[1].equals(""))&&(valor[1].substring(0,1).equals(leta))&&(validateAnswer(valor[1]))){
            Log.d("prueba","puntaje2 : "+makeScoreThisGame(obtener_resultados()));
            puntaje=puntaje+20;

        }
        if((valor[2]!=null)&&(!valor[2].equals(""))&&(valor[2].substring(0,1).equals(leta))&&(validateAnswer(valor[2]))){
            Log.d("prueba","puntaje3 : "+makeScoreThisGame(obtener_resultados()));
            puntaje=puntaje+20;

        }
        if((valor[3]!=null)&&(!valor[3].equals(""))&&(valor[3].substring(0,1).equals(leta))&&(validateAnswer(valor[3]))){
            Log.d("prueba","puntaje4 : "+makeScoreThisGame(obtener_resultados()));
            puntaje=puntaje+20;

        }
        if((!valor[4].equals(""))&&(valor[4].substring(0,1).equals(leta))&&(validateAnswer(valor[4]))){
            Log.d("prueba","puntaje5 : "+makeScoreThisGame(obtener_resultados()));
            puntaje=puntaje+20;

        }


        return puntaje;
    }

    public static boolean validateAnswer(String text) {
        Log.d("prueba",manager.getDatabaseName());
        if(items2.contains("color")) {
            if(manager.consultarColor(text)) {
                return true;
            }
        }
        if(items2.contains("ciudad")) {
            if(manager.consultarCiudad(text)) {
                return true;
            }
        }
        if(items2.contains("job")) {
            if (manager.consultarJob(text)) {
                return true;
            }
        }
        if(items2.contains("nombre")) {
            if (manager.consultarNombre(text)) {
                return true;
            }
        }
        if(items2.contains("apellido")) {
            if (manager.consultarApellido(text)) {
                return true;
            }
        }
        if(items2.contains("animal")) {
            if (manager.consultarAnimal(text)) {
                return true;
            }
        }
        if(items2.contains("frutas")) {
            if (manager.consultarFruta(text)) {
                return true;
            }
        }
        if (items2.contains("comida")) {
            if (manager.consultarComida(text)) {
                return true;
            }
        }
        if (items2.contains("pais")) {
            if (manager.consultarPais(text)) {
                return true;
            }
        }
        return false;
    }

}
