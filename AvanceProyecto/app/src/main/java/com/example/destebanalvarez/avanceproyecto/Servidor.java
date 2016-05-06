package com.example.destebanalvarez.avanceproyecto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Scanner;

public class Servidor extends AppCompatActivity {

    static Thread thread;//Hilo que crea los sockets
    ServerSocket serverSocket;
    Socket cliente = null;
    String IP;//Guarda la IP del dispositivo que ejecute esté actuando como servidor
    public static Activity actividad;//Referencia a la instancia actual de la actividad (para cerrarla desde otras actividades)
    public boolean abierta=false;
    private String[] items;
    ObjectOutputStream salida;
    String[] values;
    int score;
    boolean estado=true;
    String[] Completo;
    String letra;
    String mode;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidor);
        actividad=this;
        abierta = true;
        TextView textView = (TextView) findViewById(R.id.textView);
        Bundle datos = this.getIntent().getExtras();
        items=new String[5];
        Completo=new String[7];

        items = datos.getStringArray("items");
        IP = wifiIpAddress(this);
        mode=datos.getString("mode");
        textView.setText(textView.getText().toString()+IP);
        letra=datos.getString("letra");
        initThread();//Se inicia el hilo para crear los sockets con la IP proporcionada
    }

    //Cuando se para la actividad (finaliza la app), se cierra el socket servidor
   // @Override
    /*public void onStop() {
        super.onStop();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    private void initThread(){
        thread = new Thread (new Runnable() {
            @Override
            public void run() {

                serverSocket = null;
                String puntuacionCliente;

                try {


                    //Se crea el socket servidor
                    InetAddress inetAddress = InetAddress.getByName(IP);
                    serverSocket = new ServerSocket(4440, 50, inetAddress);

                    while(estado){

                    //Se acepta el cliente
                    cliente = serverSocket.accept();

                    Log.d("oeee", cliente.getInetAddress()+"");

                    //Se incializan las variables de entrada y salida
                    //Scanner entrada = new Scanner(cliente.getInputStream());

                    DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                    String texto=entrada.readUTF();

                    if(texto.equals("cerrar")){
                        int mipuntaje=0;
                        values=Game.obtener_resultados();

                        if(!values[0].equals("") && Game.validateAnswer(values[0])) {
                            mipuntaje += 20;
                        }
                        if(!values[1].equals("") && Game.validateAnswer(values[1])) {
                            mipuntaje += 20;
                        }
                        if(!values[2].equals("") && Game.validateAnswer(values[2])) {
                            mipuntaje += 20;
                        }
                        if(!values[3].equals("") && Game.validateAnswer(values[3])) {
                            mipuntaje += 20;
                        }
                        if(!values[4].equals("") && Game.validateAnswer(values[4])) {
                            mipuntaje += 20;
                        }





                        Log.d("oeee",mipuntaje+"");
                        Intent in=new Intent(Servidor.this,puntaje.class);
                        in.putExtra("punt",mipuntaje);
                        in.putExtra("funcion","cerrar");
                        in.putExtra("mode",mode);
                        startActivity(in);


                    }else if(texto.equals("conectar")){

                        Completo[0]=items[0];
                        Completo[1]=items[1];
                        Completo[2]=items[2];
                        Completo[3]=items[3];
                        Completo[4]=items[4];
                        Completo[5]=letra;
                        Completo[6]=mode;

                        salida = new ObjectOutputStream(cliente.getOutputStream());
                        salida.writeObject(Completo);


                      //  PrintWriter out = new PrintWriter(cliente.getOutputStream());

                        //Se inicia la actividad principal que muestra el menú del juego
                        Intent intent = new Intent(Servidor.this, Game.class);
                        intent.putExtra("items", items);
                        intent.putExtra("letra",letra);
                        intent.putExtra("actividad", "servidor");//Se manda el rol del jugador actual hasta la otra actividad
                        startActivity(intent);

                    }
                        cliente.close();
                        //thread.interrupt();
                    }



                //
                } catch (IOException x) {
                    x.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //Método que obtiene la IP del dispositivo desde el cual se está llamando
    protected String wifiIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        //Se obtiene la IP normalmente
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        // Se convierte de Little Endian a Big Endian si es necesario
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        //Se convierte esa IP a String
        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();
        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFIIP", "Unable to get host address.");
            ipAddressString = null;
        }

        return ipAddressString;
    }
}
