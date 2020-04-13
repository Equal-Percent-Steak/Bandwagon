package com.equalpercentsteak.bandwagon;

import java.util.ArrayList;

public class User {

    private String username;
    private ArrayList<String> classes;
    private String firstName;
    private String lastName;

    public User(){
        username="";
        classes = new ArrayList<>();
    }

    public User (String userName, String first, String last, boolean isAdmin){
        username = userName;
        firstName = first;
        lastName = last;
        classes = new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addClass(String c){
        classes.add(c);
    }

    public void removeClass(String c){
        classes.remove(c);
    }

    public String getUsername(){
        return username;
    }

    public void display(){
        System.out.println(username);
    }
}
