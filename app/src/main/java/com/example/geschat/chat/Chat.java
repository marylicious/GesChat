package com.example.geschat.chat;
import java.util.ArrayList;

//Adapter de RecyclerView del fragment de Chats

public class Chat {

    private String chatName;
    private Boolean finished;

    public Chat(String chatName, Boolean finished ){
        this.chatName = chatName;
        this.finished = finished;
    }

    public String getChatName() {
        return chatName;
    }

    public boolean isFinished() {
        return finished;
    }


    //generar unos chats pa proba

    private static int lastContactId =0;

    public static ArrayList<Chat> createChatList(int numChats) {
        ArrayList<Chat> chatList = new ArrayList<Chat>();

        for (int i = 1; i <= numChats; i++) {
            chatList.add(new Chat("Chat #" + ++lastContactId, i <= numChats / 2));
        }

        return chatList;
    }



}
