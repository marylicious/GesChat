package com.example.geschat.models;

public class Suggestion {
    private String book;
    private String phrase;
    private String word;

    public Suggestion() {
    }

    public Suggestion(String book, String phrase, String word) {
        this.book = book;
        this.phrase = phrase;
        this.word = word;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
