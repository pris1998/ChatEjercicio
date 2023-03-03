package com.example.chat.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.models.PaqueteEnvio;
import com.example.chat.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>{
    //Lista de mensajes
    ArrayList<PaqueteEnvio> listapaquete;

    public RecyclerAdapter(ArrayList<PaqueteEnvio> listapaquete){
        this.listapaquete = listapaquete;
    }
    //Creacion de la estructura de los componentes desde la lsita le layout
    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_chat,parent,false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);

        return recyclerHolder;
    }
    //Enlaza la informacion con cada una de las celdas ,una vez hecho
    //extra la informacion del elemento de la lista y le asigna la parte
    //gráfica que le corresponde
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, int position) {
        PaqueteEnvio pack = listapaquete.get(position);
        //Comprueba que el que está escribiendo es el Usuario
        //Una vez comprobado te escribe la informacion en la lista
        if (pack.isEsUsuario()) {
            holder.txtName.setText(pack.getNick());
            holder.txtIp.setText( pack.getIp());
            holder.txtMensaje.setText( pack.getMensaje());
        }else{
            holder.txtName.setText(pack.getNick());
            holder.txtIp.setText( pack.getIp());
            holder.txtMensaje.setText( pack.getMensaje());
        }

    }

    @Override
    public int getItemCount() {
        return listapaquete.size();
    }
    //Recreacion de los elementos de la vista
    public class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtIp;
        TextView txtMensaje;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtNameChat);
            txtIp = itemView.findViewById(R.id.txtIPChat);
            txtMensaje = itemView.findViewById(R.id.txtMensajeChat);

        }

    }
}


