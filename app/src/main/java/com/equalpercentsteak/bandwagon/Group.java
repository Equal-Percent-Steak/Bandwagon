package com.equalpercentsteak.bandwagon;

import java.util.ArrayList;

public class Group {

    private ArrayList<User> users;
    private ArrayList<Assignment> assignments;
    private String name;

    public Group(String name, User user){
        users = new ArrayList<>();
        users.add(user);
        assignments = new ArrayList<>();
    }

    //TODO: Andrew Firebase integration for adding user and assignments
    public void addUser(User u){
        users.add(u);
    }

    public void addAssignment(Assignment a){
        assignments.add(a);
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }
}
