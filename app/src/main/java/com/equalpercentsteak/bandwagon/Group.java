package com.equalpercentsteak.bandwagon;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Group {

    private ArrayList<User> users;
    private ArrayList<Assignment> assignments;
    private String name;

    /**
     * Creates a Group with an empty ArrayList of users and assignments and an empty string for a name.
     */
    public Group(){
        this.name = "";
        users = new ArrayList<>();
        assignments = new ArrayList<>();
    }

    /**
     * Constructs a group with a given name and adds a user
     * @param name the name of the group
     * @param user the user to add
     */
    public Group(String name, User user){
        this.name = name;
        users = new ArrayList<>();
        users.add(user);
        assignments = new ArrayList<>();
    }

    /**
     * Constructs a group with a given name
     * @param name the name of the group
     */
    public Group(String name){
        this.name=name;
    }

    /**
     * Adds a user to FireBase
     * @param u the user to add
     */
    //TODO: Andrew Firebase integration for adding user and assignments
    public void addUser(User u){
        users.add(u);
    }

    /**
     * Adds an assignment to the ArrayList of assignments
     * @param a the assignment to add
     */
    public void addAssignment(Assignment a){
        assignments.add(a);
    }

    /**
     * Returns the name of the group
     * @return the name of the group
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the group
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the ArrayList of Users
     * @return the ArrayList of Users
     */
    public ArrayList<User> getUsers(){
        return users;
    }

    /**
     * Gets the ArrayList of Assignments
     * @return the ArrayList of Assignments
     */
    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }

    /**
     * creates a list of the groups
     * @return the groups in a sample array
     */
    public static ArrayList<Group> createGroupsList(){
        ArrayList<Group> groups = new ArrayList<Group>();

        groups.add(new Group("Math"));
        groups.add(new Group("Spanish"));
        groups.add(new Group("STEM 3"));

        return groups;
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
