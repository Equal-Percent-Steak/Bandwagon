package com.equalpercentsteak.bandwagon;

import java.util.ArrayList;

public class User {

    private String username;
//    private ArrayList<String> classes;
//    private String name;

    public User(){
        username="";
//        classes = new ArrayList<>();
    }

    public User (String userName){
        username = userName;
//        classes = new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }
//
//    public void addClass(String c){
//        classes.add(c);
//    }
//
//    public void removeClass(String c){
//        classes.remove(c);
//    }

    public String getUsername(){
        return username;
    }

    public void display(){
        System.out.println(username);
    }
}
