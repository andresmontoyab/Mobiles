package com.example.destebanalvarez.avanceproyecto;

/**
 * Created by andres.montoyab on 5/04/16.
 */
public class Table_Ciudad {
    public int id;
    public String ciudad;


    public static final String TABLE_NAME = "ciudad";
    public static final String FIELD_ID = "_id";
    public static final String CIUDAD = "ciudades";


    public static final String CREATE_DB_TABLE = "create table " +  TABLE_NAME + "( " +
            FIELD_ID + " integer primary key autoincrement," +
            CIUDAD + " text"
            +" );";


}
