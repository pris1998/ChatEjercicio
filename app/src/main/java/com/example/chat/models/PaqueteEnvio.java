package com.example.chat.models;

import java.io.Serializable;
import java.util.Date;
//Clase que almacena el nombre, la ip y el mensaje
public class PaqueteEnvio implements Serializable {
     String nick;
     String ip;
     String mensaje;
   //Variable boleana para identificar que el usuario es el que está escribiendo
    boolean esUsuario;

    //Constructor de la clase
    public PaqueteEnvio(String nick, String ip, String mensaje) {
        this.nick = nick;
        this.ip = ip;
        this.mensaje = mensaje;

    }


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEsUsuario() {
        return esUsuario;
    }

    public void setEsUsuario(boolean esUsuario) {
        this.esUsuario = esUsuario;
    }
}
