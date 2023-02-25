package com.example.chat;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor  implements Runnable {

    public ServerSocket serverSocket;
    public static final int PUERTO = 9090;
    //PaqueteEnvio accede desde la vista para ver que tiene dentro el sevidor
    public PaqueteEnvio paquete_recibido;

    String ip,nick,mensaje;
    private Thread hilo ;

    //Constructor del Servidor
    public Servidor() {
        try {
            this.serverSocket = new ServerSocket(PUERTO);
        } catch (IOException e) {
            Log.d("SERVER", "No se ha podido crear el serverSocket en el constructor.");
        }
    }

    /**
     * Método que inicia el hilo del Servidor
     */
    public void iniciar(){
        hilo = new Thread(Servidor.this);
        hilo.start();
    }

    /**
     * Método run
     */
    @Override
    public void run() {
        try {

            while(true){
                // El Socket espera a que le llegue la conexión
                Socket misocket = serverSocket.accept();
                //recoge el paquete dle cliente aceptando mediante un flujo de entrada (input)
                ObjectInputStream entrada = new ObjectInputStream(misocket.getInputStream());
                //guarda en una variable pública
                paquete_recibido = (PaqueteEnvio) entrada.readObject();
                //se pasa el nick,ip y mensaje
                nick = paquete_recibido.getNick();
                ip = paquete_recibido.getIp();
                mensaje = paquete_recibido.getMensaje();
                Log.d("SERVER", "Recibido: " + nick + ip + mensaje);

                //Socket de enviar al Destinatario
                Socket enviarDestinatario = new Socket(ip,9999);
                ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviarDestinatario.getOutputStream());
                paqueteReenvio.writeObject(paquete_recibido);

                paqueteReenvio.close();

                entrada.close();
                misocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}