package com.example.geschat.models;
import java.util.ArrayList;

//Adapter de RecyclerView del fragment de Chats

public class ChatReviewModel {

    private String chatName;
    private Boolean finished;
    private String facilitador;
    private String nivel;
    private String fecha;

    public ChatReviewModel(String chatName, Boolean finished, String facilitador, String nivel, String fecha ){
        this.chatName = chatName;
        this.finished = finished;
        this.facilitador = facilitador;
        this.nivel = nivel;
        this.fecha = fecha;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
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

    public String getChatName() {
        return chatName;
    }

    public boolean isFinished() {
        return finished;
    }


    //generar unos chats pa proba -- BORRAR despues

    private static int lastContactId =0;

    public static ArrayList<ChatReviewModel> createChatList(int numChats) {
        ArrayList<ChatReviewModel> chatList = new ArrayList<ChatReviewModel>();

        for (int i = 1; i <= numChats; i++) {
            chatList.add(new ChatReviewModel("Chat #" + ++lastContactId, i % 2 == 0 ,"Random", "HardCore","01/6/2079"));
        }

        return chatList;
    }



}
