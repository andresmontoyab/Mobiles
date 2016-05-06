package com.example.destebanalvarez.avanceproyecto;

/**
 * Created by andres.montoyab on 5/04/16.
 */
public class Table_Profesion {
    public int id;
    public String job;


    public static final String TABLE_NAME = "job";
    public static final String FIELD_ID = "_id";
    public static final String JOB = "jobs";


    public static final String CREATE_DB_TABLE = "create table " +  TABLE_NAME + "( " +
            FIELD_ID + " integer primary key autoincrement," +
            JOB + " text"
            +" );";


}
