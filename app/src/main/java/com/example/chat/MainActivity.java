package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chat.adaptadores.RecyclerAdapter;
import com.example.chat.models.Cliente;
import com.example.chat.models.PaqueteEnvio;
import com.example.chat.models.Servidor;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    RecyclerAdapter adapter ;
    RecyclerView recyclerView;
    //lista de paquetes recibe RecyclerView
    ArrayList<PaqueteEnvio> lista ;
    //Variables y boton
    TextView txtName,txtIp,txtMensaje;
    Button btnEnviar;
    //Hilos encargados de realizar tareas de conexion con la red
    Thread hiloServidor;
    Thread hiloEnvio;
    Thread hiloCliente;

    PaqueteEnvio paqueteRecibido;
    //Instancias de las clases
    public  Servidor servidor;
    public  Cliente cliente;

    String name,ip,mensaje = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializacion de los elementos
        txtName = findViewById(R.id.txtName);
        txtIp = findViewById(R.id.textoIP);
        txtMensaje = findViewById(R.id.txtMensaje);
        recyclerView = findViewById(R.id.recyclerChat);

        lista = new ArrayList<>();

        adapter = new RecyclerAdapter(lista);

        btnEnviar = findViewById(R.id.btnEnviar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        cliente = new Cliente();


        hiloServidor = new Thread(new Runnable() {
            @Override
            public void run() {
                if (servidor == null) {
                    servidor = new Servidor();
                }
            }
        });
        hiloServidor.start();


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensaje = txtMensaje.getText().toString().trim();
                name = txtName.getText().toString().trim();
                ip = txtIp.getText().toString().trim();
                //si el mensaje está vacío no se envía
                if (mensaje.isEmpty()) {
                    return;
                }
                paqueteRecibido = new PaqueteEnvio(name,ip,mensaje);
                    //Comienza un hiloEnvio es para ir mandando mensajes
                 hiloEnvio = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        name = paqueteRecibido.getNick();
                        ip = paqueteRecibido.getIp();
                        mensaje = paqueteRecibido.getMensaje();

                        //Llama al metodo enviar del Servidor
                        servidor.iniciar();
                        //Creacioón de un socket nuevo de cliente con IP destino
                        hiloCliente = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                cliente.Enviar(paqueteRecibido);
                            }
                        });

                    }
                });
                hiloEnvio.start();
                try {
                    hiloEnvio.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Añadimos a la lista
                lista.add(paqueteRecibido);
                //Llamamos a nuestro método para que almacene los mensajes
                avisarRVmensajes();
                //Comienza el hiloCliente
                hiloCliente.start();
                //Avisa de que no ha habido ninguna conexion
                if (cliente.clienteSocket == null) {
                    Log.d("AVISO","El destinatario no existe o se encuentra desconectado ");
                    lista.add(paqueteRecibido);
                    avisarRVmensajes();
                }
            }
        });

    }
    /**
     * Funciona como aviso al Recycler View para que almacene los nuevos mensajes q han llegado
     */
    private void avisarRVmensajes() {
        adapter.notifyItemInserted(lista.size() - 1);
        adapter.notifyItemRangeChanged(lista.size() - 1, lista.size());
        recyclerView.scrollToPosition(lista.size() - 1);
    }
}
