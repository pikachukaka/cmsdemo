package com.learning.cmsdemo.model;

public class Attendees {
    private String userID;
    private String conferenceID;
    private String conferenceName;
    private String name;
    private String id;
    private String workplace;
    private String telephone;
    private String time;
    private String house;

    public Attendees() {
    }

    public Attendees(String userID, String conferenceID, String conferenceName, String name, String id, String workplace, String telephone, String time, String house) {
        this.userID = userID;
        this.conferenceID = conferenceID;
        this.conferenceName = conferenceName;
        this.name = name;
        this.id = id;
        this.workplace = workplace;
        this.telephone = telephone;
        this.time = time;
        this.house = house;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(String conferenceID) {
        this.conferenceID = conferenceID;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
