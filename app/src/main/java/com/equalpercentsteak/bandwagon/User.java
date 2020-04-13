package com.equalpercentsteak.bandwagon;

import java.util.ArrayList;

public class User {

    private String username;
    private String userID;
    private boolean admin;
    private ArrayList<String> classes;

    public User(){
        userID = "";
        username="";
        admin=false;
        classes = new ArrayList<>();
    }

    public User (String userName, String id, boolean isAdmin){
        userID = id;
        username = userName;
        admin = isAdmin;
        classes = new ArrayList<>();
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

    public void setAdminStatus(boolean isAdmin){
        admin = isAdmin;
    }

    public Boolean getAdminStatus(){
        return admin;
    }

    public void display(){
        System.out.println(username);
    }
}
