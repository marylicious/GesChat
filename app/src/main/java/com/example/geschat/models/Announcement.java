package com.example.geschat.models;

import java.util.ArrayList;

public class Announcement {

    private String title, author,body,date;

    public Announcement (String title, String author, String body, String date){
        this.title=title;
        this.author=author;
        this.body=body;
        this.date = date;
    }

    public String getTitle() {

        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    //Anuncios falsos. BORRAR LUEGO

    private static int lastId = 0;

    public static ArrayList<Announcement> createAnnList(int numAnn) {
        ArrayList<Announcement> ann = new ArrayList<Announcement>();

        for (int i = 1; i <= numAnn; i++) {
            ann.add(new Announcement("A default title " + ++lastId, "marylicious", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis viverra vehicula bibendum. Nunc vitae nisl eget ante sagittis facilisis a nec ex. Nulla finibus massa vel dui placerat, sit amet consectetur tortor ultrices. ", "22/06/2019"));
        }

        return ann;
    }
}
