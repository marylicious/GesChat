package com.example.geschat.models;

public class FacChatCompletedModel {
    private String chatName;
    private String facName;
    private String nivel;
    private String fecha;

    public FacChatCompletedModel(String chatName, String facName, String nivel, String fecha) {
        this.chatName = chatName;
        this.facName = facName;
        this.nivel = nivel;
        this.fecha = fecha;
    }
    public FacChatCompletedModel(){}

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
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
