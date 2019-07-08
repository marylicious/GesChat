package com.example.geschat.models;

public class ProfilePreviousChatModel {
    private String nombreChat;
    private String fecha;

    public ProfilePreviousChatModel(String nombreChat, String fecha) {
        this.nombreChat = nombreChat;
        this.fecha = fecha;
    }
    public ProfilePreviousChatModel(){}

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
