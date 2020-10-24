package com.example.housseinabedelhoussein;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat {
    public int IdSender;
    public String Message;
    public String MessageDate;

    public Chat(int idSender, String message, String messageDate) {
        IdSender = idSender;
        Message = message;
        MessageDate = messageDate;
    }
}
