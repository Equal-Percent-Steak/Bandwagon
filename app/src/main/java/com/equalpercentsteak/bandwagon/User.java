package com.equalpercentsteak.bandwagon;

import java.util.ArrayList;

/**
 * This class creates a user with unique id and helps display and communicate its data.
 */
public class User {

    /**
     * A string username chosen by the user
     */
    private String username;
    /**
     * A string id that is individual to each user
     */
    private String id;

    /**
     * List of all classes that a user is in
     */
    private ArrayList<String> classes;
//    private String name;

    /**
     * Constructs a User with an empty string for a username
     */
    public User(){
        username="";
        classes = new ArrayList<>();
    }

    /**
     * Constructs a User with a given username and id
     * @param userName a string username
     * @param id a string id
     */
    public User (String userName, String id){
        username = userName;
        this.id = id;
        classes = new ArrayList<>();
    }

    /**
     * Sets the username to be a specific value
     * @param username the string that will be set as the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the id of the user
     * @return the string user id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of user
     * @param id the string user id to be set
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * Gets the string username of the user
     * @return the username of the user
     */
    public String getUsername(){
        return username;
    }

    /**
     * Gets the list of classes for the user
     * @return
     */
    public ArrayList<String> getClasses() {
        return classes;
    }

    /**
     * Sets the list of classes for the user
     * @param classes list of classes
     */
    public void setClasses(ArrayList<String> classes) {
        this.classes = classes;
    }

    /**
     * Adds classes to the class ArrayList
     * @param c class to be added
     */
    public void addClasses(String c) {
        this.classes.add(c);
    }
    /**
     * Generates an ArrayList of sample Groups
     * @return an ArrayList of groups with given names
     */
    public static ArrayList<Group> generateGroupList(){
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("Math"));
        groups.add(new Group("Science"));
        groups.add(new Group("CS"));

        return groups;
    }
}
