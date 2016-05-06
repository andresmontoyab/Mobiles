package com.example.destebanalvarez.avanceproyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends AppCompatActivity {

    public static Activity actividad;//Referencia a la instancia actual de la actividad (para cerrarla desde otras actividades)
    static Thread thread;//Hilo que crea los sockets
    Socket cliente;
    public boolean abierta=false;
    EditText editText;
    ObjectInputStream datos_entrada;
    String funcion;
    String[] items=new String[5];
    String[] completo=new String[7];
    public static String IP;
    DataOutputStream datos_salida;
    String letra;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        actividad=this;
        abierta = true;
        Bundle bundle=getIntent().getExtras();
        funcion=bundle.getString("funcion");
        int puerto;

        //Se incializan variables de referencia a los componentes de la interfaz
        editText = (EditText) findViewById(R.id.editText2);
        editText.setText("10.0.0.");
        Button button = (Button) findViewById(R.id.button3);


    }

    public void onClick(View view) {
        final String ipServidor = editText.getText().toString();//Se obtiene la IP del servidor ingresada
        IP=ipServidor;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                cliente = null;
                String puntuacionServidor;


                //Conectarse como cliente a un puerto y una IP específica
                try {
                    //Se crea el socket cliente con la IP ingresada por el usuario
                    cliente = new Socket(ipServidor, 4440);




                    //Se incializan las variables de entrada y salida
                   // BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                   // PrintWriter salida = new PrintWriter(cliente.getOutputStream());

                    datos_salida=new DataOutputStream(cliente.getOutputStream());
                    datos_salida.writeUTF(funcion);


                    if(funcion.equals("conectar")) {


                        datos_entrada = new ObjectInputStream(cliente.getInputStream());
                        completo = (String[]) datos_entrada.readObject();
                        letra=(String)completo[5];
                        items[0]=completo[0];
                        items[1]=completo[1];
                        items[2]=completo[2];
                        items[3]=completo[3];
                        items[4]=completo[4];
                        mode = completo[6];
                        Log.d("prubeaa",letra+"");

                        //Se lanza la actividad que muestra el menú
                        Intent intent = new Intent(Cliente.this, Game.class);
                        intent.putExtra("items", items);
                        intent.putExtra("letra",letra);
                        intent.putExtra("actividad", "cliente");//Se manda el rol del jugador actual hasta la otra actividad
                        intent.putExtra("mode", mode);
                        startActivity(intent);
                    }

                    cliente.close();
                  //  datos_entrada.close();
                  //  datos_salida.close();
                  //  thread.interrupt();

                    //Espera a que se calcule su propia puntuación
                    while (true) {
                        if (((Aplicacion) getApplication()).getAvanzar()) {
                            //Se recibe la puntuación desde la clase Aplicacion
                            String puntuacion = ((Aplicacion) getApplication()).getMiPuntuacion();

                            //Escribe su puntuación al servidor
                           // salida.write(puntuacion);
                           // salida.flush();
                            //salida.close();MALO!!!!: CERRABA EL SOCKET TAMBIEN
                          //  cliente.shutdownOutput();

                            break;
                        }
                    }

                    //Espera a que el servidor envie la puntuación
                  /*  while (true) {
                        //puntuacionServidor = entrada.readLine();
                        //Si sí se recibió la puntuación del servidor se manda a la clase Aplicacion para que se lea en
                        //la actividad Perder y así proceder a abrir la actividad Puntuacion para mostrar las puntuaciones
                        if (!((puntuacionServidor.equals("")) || (puntuacionServidor == "") || (puntuacionServidor == null) || (puntuacionServidor.equals(null)))) {
                            //Cuando la envie se lleva a la clase Aplicacion
                            ((Aplicacion) getApplication()).setPuntuacionAmigo(puntuacionServidor);
                            break;
                        }
                    }
                    */

                }//Excepción para indicar que no hay servidores escuchando en ese puerto o en esa IP
                catch (UnknownHostException e) {
                    //Imprimir excepción en el output del IDE
                    e.printStackTrace();
                            /*//Dar a conocer el error al usuario poniendo el mensaje en el textView
                            textView.setText("Ingrese correctamente los datos");*/
                }//Excepcion que indica problemas al enviar o recibir datos
                catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public void iniciarCliente(final String ip,String fun){

        //final String ipServidor = editText.getText().toString();//Se obtiene la IP del servidor ingresada
        funcion=fun;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                cliente = null;
                String puntuacionServidor;



                //Conectarse como cliente a un puerto y una IP específica
                try {
                    //Se crea el socket cliente con la IP ingresada por el usuario
                    cliente = new Socket(ip, 4440);

                    datos_salida=new DataOutputStream(cliente.getOutputStream());
                    datos_salida.writeUTF(funcion);

                    cliente.close();


                }//Excepción para indicar que no hay servidores escuchando en ese puerto o en esa IP
                catch (UnknownHostException e) {
                    //Imprimir excepción en el output del IDE
                    e.printStackTrace();
                            /*//Dar a conocer el error al usuario poniendo el mensaje en el textView
                            textView.setText("Ingrese correctamente los datos");*/
                }//Excepcion que indica problemas al enviar o recibir datos
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();


    }
}
