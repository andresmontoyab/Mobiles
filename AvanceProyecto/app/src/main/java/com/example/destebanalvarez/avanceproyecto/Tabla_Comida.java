package com.example.destebanalvarez.avanceproyecto;

/**
 * Created by andres.montoyab on 5/04/16.
 */
public class Tabla_Comida {
    public int id;
    public String comida;


    public static final String TABLE_NAME = "comida";
    public static final String FIELD_ID = "_id";
    public static final String COMIDA = "comidas";


    public static final String CREATE_DB_TABLE = "create table " +  TABLE_NAME + "( " +
            FIELD_ID + " integer primary key autoincrement," +
            COMIDA+ " text"
            +" );";


}
