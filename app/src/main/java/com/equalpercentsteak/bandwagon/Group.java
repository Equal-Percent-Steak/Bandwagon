package com.equalpercentsteak.bandwagon;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Group {

    private ArrayList<User> users;
    private ArrayList<Assignment> assignments;
    private String name;

    public Group(){
        this.name = "";
        users = new ArrayList<>();
        assignments = new ArrayList<>();
    }

    public Group(String name, User user){
        this.name = name;
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

    public void updateAssignments(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference groups = database.getReference("groups").child(name);
        groups.child("assignments").setValue(getAssignments());
    }

    public void updateUsers(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference groups = database.getReference("groups").child(name);
        groups.child("members").setValue(getUsers());
    }
}
