package lab2apprun.gr2.compumovil.udea.edu.co.lab2apprun;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicion_user);





    }

    public void InsertarUser(View v) {
        EditText suser = (EditText) findViewById(R.id.user);
        EditText scontra = (EditText) findViewById(R.id.pass);
        EditText semail = (EditText) findViewById(R.id.email);

        EUser a = new EUser();
        a.usuario = suser.getText().toString();
        a.contraseña = scontra.getText().toString();
        a.correo = semail.getText().toString();

        DBD Manager=new DBD(this);
        Manager.insertarUSER(a);

        Toast toast = Toast.makeText(this, "Datos Ingresados", Toast.LENGTH_SHORT);
        toast.show();

    }
    public void goIngreso(View v){
        Intent intent=new Intent(AdicionUser.this,LoggIN.class);
        startActivity(intent);

    }
    }

