package com.example.geschat.models;

public class FinishedDetailsUserList {

    private String id;
    private int foto;

    public FinishedDetailsUserList(String id, int foto) {
        this.id = id;
        this.foto = foto;
    }

    public FinishedDetailsUserList(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
