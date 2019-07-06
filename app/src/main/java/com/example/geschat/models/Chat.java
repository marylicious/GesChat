package com.example.geschat.models;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import static java.lang.Integer.parseInt;

//Adapter de RecyclerView del fragment de Chats

public class Chat {

    private String chatName,keyDB;
    private Boolean finished,proposalApproved, isFilled;
    private String level,facilitator,presentation,comments,dateEpoch;
    private Date date;
    int amountPeople;
    private ArrayList<String> waitList, assistanceList;
    private String day, startTime, endTime;


    public String getKeyDB() {
        return keyDB;
    }

    public void setKeyDB(String keyDB) {
        this.keyDB = keyDB;
    }

    public String getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(String dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public Chat(String chatName, Boolean finished){
        this.chatName = chatName;
        this.finished = finished;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }

    //Chat chat = new Chat(assistanceListKeys,approvedProposal,dateEpoch,facilitator,comments,finished,isFilled,presentation,chatName);
    public Chat(ArrayList<String> assistanceList, Boolean proposalApproved, String dateEpoch, String facilitator, String comments, Boolean finished, Boolean isFilled, String presentation, String chatName, String level, int amountPeople){
        this.assistanceList = assistanceList;
        this.proposalApproved = proposalApproved;
        this.dateEpoch = dateEpoch;
        this.facilitator = facilitator;
        this.comments = comments;
        this.finished = finished;
        this.isFilled = isFilled;
        this.presentation = presentation;
        this.chatName = chatName;
        this.level = level;
        this.amountPeople = amountPeople;
    }

    public Chat(String chatName, Boolean finished, Boolean proposalApproved, Boolean isFilled, String level, String facilitator, String presentation, String comments, Date date) {
        this.chatName = chatName;
        this.finished = finished;
        this.proposalApproved = proposalApproved;
        this.isFilled = isFilled;
        this.level = level;
        this.facilitator = facilitator;
        this.presentation = presentation;
        this.comments = comments;
        this.date = date;
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

    public Boolean getProposalApproved() {
        return proposalApproved;
    }

    public void setProposalApproved(Boolean proposalApproved) {
        this.proposalApproved = proposalApproved;
    }

    public Boolean getFilled() {
        return isFilled;
    }

    public void setFilled(Boolean filled) {
        isFilled = filled;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<String> getWaitList() {
        return waitList;
    }

    public void setWaitList(ArrayList<String> waitList) {
        this.waitList = waitList;
    }

    public ArrayList<String> getAssistanceList() {
        return assistanceList;
    }

    public void setAssistanceList(ArrayList<String> assistanceList) {
        this.assistanceList = assistanceList;
    }

    public String getChatName() {
        return chatName;
    }

    public boolean isFinished() {
        return finished;
    }

    //generar unos chats pa proba -- BORRAR despues

    private static int lastContactId =0;

    public static ArrayList<Chat> createChatList(int numChats) {
        ArrayList<Chat> chatList = new ArrayList<Chat>();

        for (int i = 1; i <= numChats; i++) {
            chatList.add(new Chat("Chat #" + ++lastContactId, i % 2 == 0 ));
        }

        return chatList;
    }

    public void convertToLegibleDate(){
        int hour = 3600*1000;

        int oldHour= Integer.parseInt(dateEpoch);

        int newHour = hour + oldHour;

        Date date = new Date(oldHour);
        DateFormat formatDay = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatHour =  new SimpleDateFormat("HH:mm:ss");
        Date newDate = new Date(newHour);


        formatDay.setTimeZone(TimeZone.getTimeZone("Venezuela Standard Time"));
        formatHour.setTimeZone(TimeZone.getTimeZone("Venezuela Standard Time"));
        day = formatDay.format(date);
        startTime= formatHour.format(date);
        endTime = formatHour.format(newDate);


        //formatDay.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        //formatted = formatDay.format(date);
        //System.out.println(formatted);
    }




}
