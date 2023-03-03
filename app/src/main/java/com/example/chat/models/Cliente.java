package com.example.chat.models;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente implements Runnable{

    public Socket clienteSocket;
    Thread hiloIniciarCliente;
    public static final int PUERTO = 9090;

    public PaqueteEnvio  datos;

    public String ipC;

    /**
     * Método que envia el paquete con la informacion mediante hilos
     * @param paqueteEnvio parametro que contiene la informacion (ip,nick,mensaje)
     */
    public void Enviar(PaqueteEnvio paqueteEnvio){
        try {
            if (clienteSocket != null) {
                hiloIniciarCliente = new Thread(this);
                datos = paqueteEnvio;
                hiloIniciarCliente.start();
                hiloIniciarCliente.join();  //  ESPERAR A QUE ACABE EL HILO
            }
               } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            clienteSocket = new Socket(ipC,PUERTO);

            ObjectOutputStream flujo_salida = new ObjectOutputStream(clienteSocket.getOutputStream());
            flujo_salida.writeObject(datos);
            flujo_salida.close();
            clienteSocket.close();
        } catch (IOException e) {
            Log.d("CLIENTE", "Error al crear el socket cliente");
        }

    }
}
