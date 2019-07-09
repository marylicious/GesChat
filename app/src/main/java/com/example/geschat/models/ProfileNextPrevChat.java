package com.example.geschat.models;

public class ProfileNextPrevChat {
    private String nombreChat;
    private String fecha;

    public ProfileNextPrevChat(String nombreChat, String fecha) {
        this.nombreChat = nombreChat;
        this.fecha = fecha;
    }
    public ProfileNextPrevChat(){}

    public String getNombreChat() {
        return nombreChat;
    }

    public void setNombreChat(String nombreChat) {
        this.nombreChat = nombreChat;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
