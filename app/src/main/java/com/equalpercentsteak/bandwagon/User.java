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

    public static ArrayList<User> createUserList() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("kayla"));
        users.add(new User("andrew"));
        users.add(new User("hazelyn"));
        return users;
    }

    public static ArrayList<Group> generateGroupList(){
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("Math"));
        groups.add(new Group("Science"));
        groups.add(new Group("CS"));

        return groups;
    }
}
