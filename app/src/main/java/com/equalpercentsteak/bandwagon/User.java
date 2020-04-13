package com.equalpercentsteak.bandwagon;

public class User {

    private String username;
    private boolean admin;

    public User(){
        username="";
        admin=false;
    }

    public User (String userName, boolean isAdmin){
        username = userName;
        admin = isAdmin;
    }

    public String getUsername(){
        return username;
    }

    public Boolean getAdminStatus(){
        return admin;
    }

    public void display(){
        System.out.println(username);
    }
}
