package lab1u.gr2.compumovil.udea.edu.co.lab1ui;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
    EditText nombre;
    EditText apellido;
    EditText texto;
    EditText pais;
    EditText telefono;
    EditText direccion;
    EditText email;
    RadioButton r1;
    RadioButton r2;
    CheckBox favorito;
    Spinner hobbies;

    RadioGroup rd;

    private TextView mDateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDisplay();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add(getResources().getString(R.string.musica));
        categories.add(getResources().getString(R.string.desarrollar));
        categories.add(getResources().getString(R.string.video));
        categories.add(getResources().getString(R.string.ejercicio));
        categories.add(getResources().getString(R.string.estudiar));
        categories.add( getResources().getString(R.string.viajar));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autopais);
        textView.setAdapter(adapter);


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), getResources().getString(R.string.select) + ": " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private final String[] COUNTRIES = new String[] {
            "France", "Italy", "Germany", "Spain", "Chile", "Ecuador", "Venezuela", "Argentina", "Mexico", "Colombia"
    };

    public void guardarDatos(View v){
        nombre=(EditText) findViewById(R.id.nombre);
        String name=nombre.getText().toString();

        apellido=(EditText) findViewById(R.id.apellidos);
        String lastname=apellido.getText().toString();

        pais=(EditText)findViewById(R.id.autopais);
        String country=pais.getText().toString();

        telefono=(EditText)findViewById(R.id.telefono);
        String phone=telefono.getText().toString();

        direccion=(EditText)findViewById(R.id.direccion);
        String adress=direccion.getText().toString();

        email=(EditText)findViewById(R.id.correo);
        String correo=email.getText().toString();

        r1=(RadioButton)findViewById(R.id.shombre);
        r2=(RadioButton)findViewById(R.id.smujer);

        favorito = (CheckBox)findViewById(R.id.cfavorito);


        hobbies = (Spinner)findViewById(R.id.spinner);
        String sp = hobbies.getSelectedItem().toString();

        String sex, fav;

        String todo;

        if(r1.isChecked()==true) {
            sex = getResources().getString(R.string.shombre);
        }
        else{
            sex = getResources().getString(R.string.smujer);
        }

        if(favorito.isChecked()) {
            fav = getResources().getString(R.string.favorito);
        } else {
            fav = getResources().getString(R.string.string8)+ " " + getResources().getString(R.string.favorito);
        }

        if(name.equals("") || lastname.equals("") || country.equals("") || phone.equals("") || adress.equals("") || correo.equals("")) {
            Context context = getApplicationContext();
            Toast.makeText(context, "Faltan campos por diligenciar ", Toast.LENGTH_LONG).show();
        }else {
            todo = getResources().getString(R.string.string1) + " " + name + " " + lastname
                    + " " + getResources().getString(R.string.string2) + " " + country
                    + " " + getResources().getString(R.string.string3) + " " + adress
                    + " " + getResources().getString(R.string.string4) + " " + phone + ","
                    + " " + getResources().getString(R.string.string5) + " " + correo + "."
                    + " " + getResources().getString(R.string.string6) + " " + sex
                    + " " + getResources().getString(R.string.string7) + " " + fav + "."
                    + " " + getResources().getString(R.string.string9) + " " + sp
                    + " " + getResources().getString(R.string.string10) + " "+  mDateDisplay.getText() + ".";

            texto = (EditText) findViewById(R.id.texto);
            texto.setText(todo);
        }


    }

    public void onClick(View v) {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
        updateDisplay();
    }

    private void updateDisplay() {
        mDateDisplay.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(mMonth + 1).append("-").append(mDay).append("-")
                .append(mYear).append(" "));
    }
}
