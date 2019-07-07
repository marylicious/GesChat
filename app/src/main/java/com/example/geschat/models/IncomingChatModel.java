package com.example.geschat.models;

public class IncomingChatModel {

    private String inscritos;
    private String nombreChat;
    private String nombreFacilitador;
    private String nivel;
    private String fecha;

    public IncomingChatModel(String inscritos, String nombreChat, String nombreFacilitador, String nivel, String fecha) {
        this.inscritos = inscritos;
        this.nombreChat = nombreChat;
        this.nombreFacilitador = nombreFacilitador;
        this.nivel = nivel;
        this.fecha = fecha;
    }

    public IncomingChatModel(){}

    public String getInscritos() {
        return inscritos;
    }

    public void setInscritos(String inscritos) {
        this.inscritos = inscritos;
    }

    public String getNombreChat() {
        return nombreChat;
    }

    public void setNombreChat(String nombreChat) {
        this.nombreChat = nombreChat;
    }

    public String getNombreFacilitador() {
        return nombreFacilitador;
    }

    public void setNombreFacilitador(String nombreFacilitador) {
        this.nombreFacilitador = nombreFacilitador;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
