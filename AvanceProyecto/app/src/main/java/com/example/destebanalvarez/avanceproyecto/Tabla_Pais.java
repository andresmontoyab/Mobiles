package com.example.destebanalvarez.avanceproyecto;

/**
 * Created by andres.montoyab on 5/04/16.
 */
public class Tabla_Pais {
    public int id;
    public String pais;


    public static final String TABLE_NAME = "pais";
    public static final String FIELD_ID = "_id";
    public static final String PAIS = "paises";


    public static final String CREATE_DB_TABLE = "create table " +  TABLE_NAME + "( " +
            FIELD_ID + " integer primary key autoincrement," +
            PAIS + " text"
            +" );";


}

