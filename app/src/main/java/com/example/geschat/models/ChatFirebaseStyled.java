package com.example.geschat.models;

public class ChatFirebaseStyled {
    Boolean approvedProposal,finished,isFilled;
    String comments,date,endHour,facilitator,level,presentation,startHour,title;

    public ChatFirebaseStyled() {
    }

    public ChatFirebaseStyled(Boolean approvedProposal, Boolean finished, Boolean isFilled, String comments, String date, String endHour, String facilitator, String level, String presentation, String startHour, String title) {
        this.approvedProposal = approvedProposal;
        this.finished = finished;
        this.isFilled = isFilled;
        this.comments = comments;
        this.date = date;
        this.endHour = endHour;
        this.facilitator = facilitator;
        this.level = level;
        this.presentation = presentation;
        this.startHour = startHour;
        this.title = title;
    }

    public Boolean getApprovedProposal() {
        return approvedProposal;
    }

    public void setApprovedProposal(Boolean approvedProposal) {
        this.approvedProposal = approvedProposal;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean getFilled() {
        return isFilled;
    }

    public void setFilled(Boolean filled) {
        isFilled = filled;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
