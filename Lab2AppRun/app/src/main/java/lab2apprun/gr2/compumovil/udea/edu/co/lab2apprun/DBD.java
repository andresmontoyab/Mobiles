package lab2apprun.gr2.compumovil.udea.edu.co.lab2apprun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by andres.montoyab on 11/03/16.
 */
public class DBD extends SQLiteOpenHelper{

    private static final String DB_NAME = "comunica";
    private static final int SCHME_VERSION = 1;
    private SQLiteDatabase db;



    public DBD(Context context) {

        super(context, DB_NAME, null, SCHME_VERSION);
        db = this.getWritableDatabase();
    }

    private ContentValues generarValor(EUser a) {

        ContentValues valor = new ContentValues();
        valor.put(EUser.FIELD_USER, a.usuario);
        valor.put(EUser.FIELD_PASSWORD, a.contraseña);
        valor.put(EUser.FIELD_EMAIL, a.correo);
        valor.put(EUser.FIELD_URL, a.foto);
        return valor;
    }

    public void insertarUSER(EUser usuario) {
        db.insert(EUser.TABLE_NAME, null, generarValor(usuario));

    }

    public void insertarEVENT(EUser usuario) {
        db.insert(EEvents.TABLE_NAME, null, generarValor(usuario));

    }
    public String verificar(String user,String pass){

        Cursor c = db.rawQuery(" SELECT * FROM usuario WHERE user='" + user+"'", null);
        String a = "";


        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                a = c.getString(2);

            } while (c.moveToNext());
        }
        return a;
    }




    public ArrayList consultar() {

        Cursor c = db.rawQuery(" SELECT * FROM usuario", null);
        ArrayList<String> myList = new ArrayList();
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String a = new String();
                a += c.getString(1) + " " + c.getString(2) + " " + c.getString(3);
                myList.add(a);
            } while (c.moveToNext());
        }

        return myList;

    }

    public String buscarEstado(int id) {

        Cursor c = db.rawQuery(" SELECT * FROM preguntas WHERE _id=" + id, null);
        String a = "";
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                a = c.getString(7);

            } while (c.moveToNext());
        }
        return a;
    }

    public String buscarResp(int id) {

        Cursor c = db.rawQuery(" SELECT * FROM preguntas WHERE _id=" + id, null);
        String a = "";
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                a = c.getString(6);

            } while (c.moveToNext());
        }
        return a;
    }

    public String[] buscarId(String id) {

        Cursor c = db.rawQuery(" SELECT * FROM preguntas WHERE _id=" + id, null);
        String a[] = new String[8];
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                a[0] = c.getString(0);
                a[1] = c.getString(1);
                a[2] = c.getString(2);
                a[3] = c.getString(3);
                a[4] = c.getString(4);
                a[5] = c.getString(5);
                a[6] = c.getString(6);
                a[7] = c.getString(7);

            } while (c.moveToNext());
        }

        return a;
    }
    public String[] buscarPreg(int id) {

        Cursor c = db.rawQuery(" SELECT * FROM preguntas WHERE _id=" + id, null);
        String a[] = new String[6];
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                a[0] = c.getString(1);
                a[1] = c.getString(2);
                a[2] = c.getString(3);
                a[3] = c.getString(4);
                a[4] = c.getString(5);
                a[5] = c.getString(6);

            } while (c.moveToNext());
        }

        return a;
    }

    public String[] buscarOp1(String x) {

        Cursor c = db.rawQuery(" SELECT * FROM preguntas WHERE opcion1=" +x , null);
        String a[] = new String[7];
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                a[0] = c.getString(0);
                a[1] = c.getString(1);
                a[2] = c.getString(2);
                a[3] = c.getString(3);
                a[4] = c.getString(4);
                a[5] = c.getString(5);
                a[6] = c.getString(6);

            } while (c.moveToNext());
        }

        return a;
    }

    public void actualizar(String tel, String name, String direccion, String correo, int id) {


        db.execSQL("UPDATE agenda SET telefono='" + tel
                + "',nombre='" + name + "',direccion='" + direccion + "',correo='" + correo + "' WHERE _id=" + id);


    }

    public Integer Borrar(int id) {
        //db.execSQL(" DELETE FROM agenda WHERE _id="+id, null);
        Integer a;
        a = db.delete("preguntas", "_id=" + id + "", null);
        return a;
    }

    public int contador() {
        Cursor c = db.rawQuery(" SELECT *  FROM agenda", null);
        int cantidad = c.getCount();

        return cantidad;


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EUser.CREATE_DB_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

class SuperString{
    String[] id;
    String[] pregunta;
    String[] opcion1;
    String[] opcion2;
    String[] opcion3;
    String[] opcion4;
    String[] respuesta;
    String[] estado;

    public SuperString(int cantidad) {
        this.id = new  String[cantidad];
        this.pregunta = new  String[cantidad];
        this.opcion1 = new  String[cantidad];
        this.opcion2 = new  String[cantidad];
        this.opcion3 = new  String[cantidad];
        this.opcion4 = new  String[cantidad];
        this.respuesta = new  String[cantidad];
        this.estado = new  String[cantidad];
    }

    public void addContact(int i, String id, String preg, String op1, String op2, String op3, String op4, String respuesta, String estado){
        this.id[i] = id;
        this.pregunta[i] = preg;
        this.opcion1[i] = op1;
        this.opcion2[i] = op2;
        this.opcion3[i] = op3;
        this.opcion4[i] = op4;
        this.respuesta[i] = respuesta;
        this.estado[i] = estado;
    }


}
