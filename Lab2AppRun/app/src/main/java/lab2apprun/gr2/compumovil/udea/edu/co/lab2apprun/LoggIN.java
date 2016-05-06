package lab2apprun.gr2.compumovil.udea.edu.co.lab2apprun;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoggIN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logg_in);


    }
    public void Verificar(View v){

        EditText luser=(EditText)findViewById(R.id.loguser);
        EditText lpass=(EditText)findViewById(R.id.logpass);

        DBD Manager=new DBD(this);
        String secondpass=Manager.verificar(luser.getText().toString(),lpass.getText().toString());

        luser.setText(secondpass);

        if(lpass.getText().toString().equals(secondpass)){
            Toast toast = Toast.makeText(this, "MUHAHAHAAH LOGGIN DO IT", Toast.LENGTH_SHORT);
            toast.show();


        }
    }
    public void goRegistrar(View v){
        Intent intent=new Intent(LoggIN.this,AdicionUser.class);
        startActivity(intent);

    }
}
