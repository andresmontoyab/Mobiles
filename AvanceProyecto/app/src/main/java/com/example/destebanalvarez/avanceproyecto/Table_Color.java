package com.example.destebanalvarez.avanceproyecto;

/**
 * Created by andres.montoyab on 5/04/16.
 */
public class Table_Color {
    public int id;
    public String color;


    public static final String TABLE_NAME = "color";
    public static final String FIELD_ID = "_id";
    public static final String COLOR = "colores";


    public static final String CREATE_DB_TABLE = "create table " +  TABLE_NAME + "( " +
            FIELD_ID + " integer primary key autoincrement," +
            COLOR + " text"
            +" );";


}


