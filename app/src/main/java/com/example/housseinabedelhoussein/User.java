package com.example.housseinabedelhoussein;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class User {
    public int Id;
    public String Name ;
    public String LastSendDate;
    public String LastMessage;
    public int ImageID;
    public User(){}
    public User(int id, String name, String lastSendDate, String lastMessage,int imageId) {
        Id = id;
        Name = name;
        LastSendDate = lastSendDate;
        LastMessage = lastMessage;
        ImageID=imageId;
    }



}
