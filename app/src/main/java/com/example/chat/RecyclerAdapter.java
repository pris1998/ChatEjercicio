package com.example.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>{
    ArrayList<PaqueteEnvio> listapaquete;

    public RecyclerAdapter(ArrayList<PaqueteEnvio> listapaquete){
        this.listapaquete = listapaquete;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_chat,parent,false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);

        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, int position) {
    holder.txtName.setText(listapaquete.get(position).getNick());
    holder.txtIp.setText( listapaquete.get(position).getIp());
    holder.txtMensaje.setText( listapaquete.get(position).getMensaje());
    }

    @Override
    public int getItemCount() {
        return listapaquete.size();
    }

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


