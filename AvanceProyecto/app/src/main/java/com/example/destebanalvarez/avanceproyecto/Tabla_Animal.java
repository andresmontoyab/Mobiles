package com.example.destebanalvarez.avanceproyecto;

/**
 * Created by andres.montoyab on 5/04/16.
 */
public class Tabla_Animal {
    public int id;
    public String animal;


    public static final String TABLE_NAME = "animal";
    public static final String FIELD_ID = "_id";
    public static final String ANIMAL = "animales";


    public static final String CREATE_DB_TABLE = "create table " +  TABLE_NAME + "( " +
            FIELD_ID + " integer primary key autoincrement," +
            ANIMAL + " text"
            +" );";


}
