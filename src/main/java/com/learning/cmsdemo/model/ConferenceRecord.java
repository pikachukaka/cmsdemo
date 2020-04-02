package com.learning.cmsdemo.model;

import java.util.Objects;

public class ConferenceRecord {
    private String conferenceID;
    private String name;
    private String beginTime;
    private String endTime;
    private String place;
    private String participants;
    private String hotel;
    private String items;
    private String founder;

    public ConferenceRecord() {
    }


    public ConferenceRecord(String conferenceID, String name, String beginTime, String endTime, String place, String participants, String hotel, String items, String founder) {
        this.conferenceID = conferenceID;
        this.name = name;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.place = place;
        this.participants = participants;
        this.hotel = hotel;
        this.items = items;
        this.founder = founder;
    }

    public String getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(String conferenceID) {
        this.conferenceID = conferenceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String test(){
        return "test";
    }

    @Override
    public String toString() {
        return "ConferenceRecord{" +
                "conferenceID='" + conferenceID + '\'' +
                ", name='" + name + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", place='" + place + '\'' +
                ", participants='" + participants + '\'' +
                ", hotel='" + hotel + '\'' +
                ", items='" + items + '\'' +
                ", founder='" + founder + '\'' +
                '}';
    }
}
