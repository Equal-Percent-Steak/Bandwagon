package com.equalpercentsteak.bandwagon;

import java.util.ArrayList;

public class User {

    private String username;
    private String id;
//    private ArrayList<String> classes;
//    private String name;

    public User(){
        username="";
//        classes = new ArrayList<>();
    }

    public User (String userName, String id){
        username = userName;
        this.id = id;
//        classes = new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public static ArrayList<Group> generateGroupList(){
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("Math"));
        groups.add(new Group("Science"));
        groups.add(new Group("CS"));

        return groups;
    }
}
