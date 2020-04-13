package com.equalpercentsteak.bandwagon;

public class User {

    private String username;

    public User(){
        username="";
    }

    public User (String userName){
        username = userName;
    }

    public String getUsername(){
        return username;
    }

    public void display(){
        System.out.println(username);
    }
}
