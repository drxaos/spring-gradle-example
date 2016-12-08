package com.example.controller.api;


import com.example.db.Message;

public class ApiMessage {

    public String date;
    public String user;
    public String message;

    public ApiMessage(String date, String user, String message) {
        this.date = date;
        this.user = user;
        this.message = message;
    }

    public ApiMessage(Message message) {
        this.date = message.getDate().toString();
        this.user = message.getUser().getLogin();
        this.message = message.getMessage();
    }


}
