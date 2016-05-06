package lab2apprun.gr2.compumovil.udea.edu.co.lab2apprun;

/**
 * Created by andres.montoyab on 11/03/16.
 */
public class EEvents {
    public int id;
    public String pregunta;
    public String opcion1;
    public String opcion2;
    public String opcion3;
    public String opcion4;
    public String respuesta;
    public String estado;

    public static final String TABLE_NAME = "preguntas";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_PREGUNTA = "preg";
    public static final String FIELD_OP1 = "opcion1";
    public static final String FIELD_OP2 = "opcion2";
    public static final String FIELD_OP3 = "opcion3";
    public static final String FIELD_OP4 = "opcion4";
    public static final String RESPUESTA = "respuesta";
    public static final String ESTADO = "estado";

    public static final String CREATE_DB_TABLE = "create table " +  TABLE_NAME + "( " +
            FIELD_ID + " integer primary key autoincrement," +
            FIELD_PREGUNTA + " text," +
            FIELD_OP1 + " text," +
            FIELD_OP2 + " text," +
            FIELD_OP3 + " text," +
            FIELD_OP4 + " text," +
            RESPUESTA + " text," +
            ESTADO + " text"
            +" );";


}
