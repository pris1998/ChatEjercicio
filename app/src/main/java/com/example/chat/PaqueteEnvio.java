package com.example.chat;

import java.io.Serializable;
import java.util.Date;

public class PaqueteEnvio implements Serializable {
     String nick;
     String ip;
     String mensaje;
    String horaEnvio;
    boolean esUsuario;


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

    public String getHoraEnvio() {
        return horaEnvio;
    }

    public boolean isEsUsuario() {
        return esUsuario;
    }

    public void setEsUsuario(boolean esUsuario) {
        this.esUsuario = esUsuario;
    }
}
