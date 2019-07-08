package com.example.geschat.models;

public class FacChatCompletedDetailsModel {
    private String id;
    private int foto;

    public FacChatCompletedDetailsModel(String id, int foto) {
        this.id = id;
        this.foto = foto;
    }

    public FacChatCompletedDetailsModel(){}

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
