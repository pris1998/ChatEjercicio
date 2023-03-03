package com.example.chat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>{
    ArrayList<PaqueteEnvio> listapaquete;

    public RecyclerAdapter(ArrayList<PaqueteEnvio> listapaquete){
        this.listapaquete = listapaquete;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //CAMBIAR LA VISTA CUSTOM:ITEM
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_chat,parent,false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);

        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, int position) {
    PaqueteEnvio pack = listapaquete.get(position);
        if (pack.isEsUsuario()) {
            Log.d("Mensaje","El usuario sale " +pack.getNick());
            holder.txtName.setText(pack.getNick());
            holder.txtIp.setText( pack.getIp());
            holder.txtMensaje.setText( pack.getMensaje());
        }else{
            Log.d("Mensaje","El usuario no sale " + pack.getNick());
            holder.txtName.setText(pack.getNick());
            holder.txtIp.setText( pack.getIp());
            holder.txtMensaje.setText( pack.getMensaje());
        }
        /*if (pack.isEsUsuario()) {
            holder.hideMR();
            holder.NombreUser.setText(pack.getNick());
            holder.Mensaje.setText(pack.getMensaje());
        }else{
            holder.hideMD();
            holder.NombreUser.setText(pack.getNick());
            holder.Mensaje.setText(pack.getMensaje());
        }*/

    }

    @Override
    public int getItemCount() {
        return listapaquete.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtIp;
        TextView txtMensaje;

        public View viewMD;
        public View viewMR;

        TextView NombreUser ;
        TextView Mensaje;
        TextView Name ;
        TextView Mensaje2;


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtNameChat);
            txtIp = itemView.findViewById(R.id.txtIPChat);
            txtMensaje = itemView.findViewById(R.id.txtMensajeChat);

            viewMD = itemView.findViewById(R.id.viewMR);
            viewMR = itemView.findViewById(R.id.viewMD);


            NombreUser = itemView.findViewById(R.id.NombreUser);
            Mensaje = itemView.findViewById(R.id.Mensaje);
            Name = itemView.findViewById(R.id.Name);
            Mensaje2 = itemView.findViewById(R.id.Mensaje2);
        }
        public void hideMD(){
            viewMD.setVisibility(View.INVISIBLE);
            Name.setVisibility(View.INVISIBLE);
            Mensaje2.setVisibility(View.INVISIBLE);
            viewMR.setVisibility(View.VISIBLE);
            NombreUser.setVisibility(View.VISIBLE);
            Mensaje.setVisibility(View.VISIBLE);

        }
        public void hideMR(){
            viewMR.setVisibility(View.INVISIBLE);
            NombreUser.setVisibility(View.INVISIBLE);
            Mensaje.setVisibility(View.INVISIBLE);
            viewMD.setVisibility(View.VISIBLE);
            Name.setVisibility(View.VISIBLE);
            Mensaje2.setVisibility(View.VISIBLE);
        }
    }
}


