package com.example.geschat.models;

public class IncomingChatDetailsModel {

    private String idUser;
    private int img;

    public IncomingChatDetailsModel(String idUser, int img) {
        this.idUser = idUser;
        this.img = img;
    }
    public IncomingChatDetailsModel(){}

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
