package com.example.geschat.models;

public class FacChatRevisionStatusModel {
    private String ChatNombre;
    private String FacNombre;
    private String Fecha;
    private String Status;
    private String Nivel;

    public FacChatRevisionStatusModel(String chatNombre, String facNombre, String fecha, String status, String nivel) {
        ChatNombre = chatNombre;
        FacNombre = facNombre;
        Fecha = fecha;
        Status = status;
        Nivel = nivel;
    }
    public FacChatRevisionStatusModel(){}

    public String getChatNombre() {
        return ChatNombre;
    }

    public void setChatNombre(String chatNombre) {
        ChatNombre = chatNombre;
    }

    public String getFacNombre() {
        return FacNombre;
    }

    public void setFacNombre(String facNombre) {
        FacNombre = facNombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }
}
