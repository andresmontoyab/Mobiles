package com.example.destebanalvarez.avanceproyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by andres.montoyab on 11/03/16.
 */
public class DBD extends SQLiteOpenHelper{

    private static final String DB_NAME = "stopic";
    private static final int SCHME_VERSION = 1;
    public static SQLiteDatabase db;



    public DBD(Context context) {
        super(context, DB_NAME, null, SCHME_VERSION);
        db = this.getWritableDatabase();
    }

    private ContentValues generarValorAnimal(Tabla_Animal a) {

        ContentValues valor = new ContentValues();
        valor.put(Tabla_Animal.ANIMAL, a.animal);

        return valor;
    }

    public void insertarAnimal(Tabla_Animal b) {
        db.insert(Tabla_Animal.TABLE_NAME, null, generarValorAnimal(b));

    }

    public boolean consultarAnimal(String animal){

        Cursor c = db.rawQuery(" SELECT * FROM animal WHERE animales='" + animal+"'", null);
        boolean flag = false;


        if (c.moveToFirst()) {
            flag = true;
        }
        return flag;
    }

    private ContentValues generarValorApellido(Tabla_Apellido a) {

        ContentValues valor = new ContentValues();
        valor.put(Tabla_Apellido.APELLIDO, a.apellido);

        return valor;
    }

    public void insertarApellido(Tabla_Apellido b) {
        db.insert(Tabla_Apellido.TABLE_NAME, null, generarValorApellido(b));

    }

    public boolean consultarApellido(String apellido){

        Cursor c = db.rawQuery(" SELECT * FROM apellido WHERE apellidos='" + apellido+"'", null);
        boolean flag = false;


        if (c.moveToFirst()) {
            flag = true;
        }
        return flag;
    }

    private ContentValues generarValorComida(Tabla_Comida a) {

        ContentValues valor = new ContentValues();
        valor.put(Tabla_Comida.COMIDA, a.comida);

        return valor;
    }

    public void insertarComida(Tabla_Comida b) {
        db.insert(Tabla_Comida.TABLE_NAME, null, generarValorComida(b));

    }

    public boolean consultarComida(String comida){

        Cursor c = db.rawQuery(" SELECT * FROM comida WHERE comidas='" + comida+"'", null);
        boolean flag = false;


        if (c.moveToFirst()) {
            flag = true;
        }
        return flag;
    }

    private ContentValues generarValorFrutas(Tabla_Frutas a) {

        ContentValues valor = new ContentValues();
        valor.put(Tabla_Frutas.FRUTA, a.frutas);

        return valor;
    }

    public void insertarFrutas(Tabla_Frutas b) {
        db.insert(Tabla_Frutas.TABLE_NAME, null, generarValorFrutas(b));

    }

    public boolean consultarFruta(String fruta){

        Cursor c = db.rawQuery(" SELECT * FROM fruta WHERE frutas='" + fruta+"'", null);
        boolean flag = false;


        if (c.moveToFirst()) {
            flag = true;
        }
        return flag;
    }

    private ContentValues generarValorNombre(Tabla_Nombre a) {

        ContentValues valor = new ContentValues();
        valor.put(Tabla_Nombre.NOMBRE, a.nombre);

        return valor;
    }

    public void insertarNombre(Tabla_Nombre b) {
        db.insert(Tabla_Nombre.TABLE_NAME, null, generarValorNombre(b));

    }

    public boolean consultarNombre(String nombre){

        Cursor c = db.rawQuery(" SELECT * FROM nombre WHERE nombres='" + nombre+"'", null);
        boolean flag = false;


        if (c.moveToFirst()) {
            flag = true;
        }
        return flag;
    }

    private ContentValues generarValorPais(Tabla_Pais a) {

        ContentValues valor = new ContentValues();
        valor.put(Tabla_Pais.PAIS, a.pais);

        return valor;
    }

    public void insertarPais(Tabla_Pais b) {
        db.insert(Tabla_Pais.TABLE_NAME, null, generarValorPais(b));

    }

    public boolean consultarPais(String pais){

        Cursor c = db.rawQuery(" SELECT * FROM pais WHERE paises='" + pais+"'", null);
        boolean flag = false;


        if (c.moveToFirst()) {
            flag = true;
        }
        return flag;
    }

    private ContentValues generarValorCiudad(Table_Ciudad a) {

        ContentValues valor = new ContentValues();
        valor.put(Table_Ciudad.CIUDAD, a.ciudad);

        return valor;
    }

    public void insertarCiudad(Table_Ciudad b) {
        db.insert(Table_Ciudad.TABLE_NAME, null, generarValorCiudad(b));

    }

    public boolean consultarCiudad(String ciudad){

        Cursor c = db.rawQuery(" SELECT * FROM ciudad WHERE ciudades='" + ciudad+"'", null);
        boolean flag = false;


        if (c.moveToFirst()) {
            flag = true;
        }
        return flag;
    }

    private ContentValues generarValorColor(Table_Color a) {

        ContentValues valor = new ContentValues();
        valor.put(Table_Color.COLOR, a.color);

        return valor;
    }

    public void insertarColor(Table_Color b) {
        db.insert(Table_Color.TABLE_NAME, null, generarValorColor(b));

    }

    public boolean consultarColor(String color){

        Cursor c = db.rawQuery(" SELECT * FROM color WHERE colores='" +color+"'", null);
        boolean flag = false;
        Log.d("de",c.getCount()+"");
    if(c.getCount()>=1){

        flag=true;
    }

      //  if (c.moveToFirst()) {
        //    flag = true;
        //}
        return flag;
    }

    private ContentValues generarValorProfesion(Table_Profesion a) {

        ContentValues valor = new ContentValues();
        valor.put(Table_Profesion.JOB, a.job);
        return valor;
    }

    public void insertarJob(Table_Profesion b) {
        db.insert(Table_Profesion.TABLE_NAME, null, generarValorProfesion(b));

    }

    public boolean consultarJob(String job){

        Cursor c = db.rawQuery(" SELECT * FROM job WHERE jobs='" + job+"'", null);
        boolean flag = false;


        if (c.moveToFirst()) {
            flag = true;
        }
        return flag;
    }


    public int cantidadM(){
        Cursor c = db.rawQuery(" SELECT * FROM accesos", null);
        int a=c.getCount();
        return a;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabla_Animal.CREATE_DB_TABLE);
        db.execSQL(Tabla_Apellido.CREATE_DB_TABLE);
        db.execSQL(Tabla_Comida.CREATE_DB_TABLE);
        db.execSQL(Tabla_Frutas.CREATE_DB_TABLE);
        db.execSQL(Tabla_Nombre.CREATE_DB_TABLE);
        db.execSQL(Tabla_Pais.CREATE_DB_TABLE);
        db.execSQL(Table_Ciudad.CREATE_DB_TABLE);
        db.execSQL(Table_Color.CREATE_DB_TABLE);
        db.execSQL(Table_Profesion.CREATE_DB_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
