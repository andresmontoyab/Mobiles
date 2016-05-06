package lab2apprun.gr2.compumovil.udea.edu.co.lab2apprun;

/**
 * Created by andres.montoyab on 11/03/16.
 */
public class EUser {

    public int id;
    public String usuario;
    public String contraseña;
    public String correo;
    public String foto;

    public static final String TABLE_NAME = "usuario";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_USER = "user";
    public static final String FIELD_PASSWORD = "pass";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_URL = "url";



    public static final String CREATE_DB_TABLE = "create table " +  TABLE_NAME + "( " +
            FIELD_ID + " integer primary key autoincrement," +
            FIELD_USER + " text," +
            FIELD_PASSWORD + " text," +
            FIELD_EMAIL + " text," +
            FIELD_URL + " text"
            +" );";


}
