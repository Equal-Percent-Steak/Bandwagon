package com.equalpercentsteak.bandwagon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Assignment {

    private String title;
    private String description;
    private String date;
    private String time;
    private Group group;
    private HashMap<String, Boolean> completedStudents;


    public Assignment(){
        this.title= "";
        this.description = "";
        this.date = "";
        this.time = "";
        completedStudents = new HashMap<>();
    }

    public Assignment(String title, String description, String date, String time, Group group){
        this.title= title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.group = group;
        completedStudents = new HashMap<>();
    }

    public Assignment(String title, String description){
        this.title= title;
        this.description = description;
    }

    /**
     * @return due date of assignment
     */
    public String getDate() {
        return date;
    }

    /**
     * @return group that the assignment belongs to
     */
    public Group getGroup(){
        return group;
    }

    /**
     * @return the title of the assignment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the assignment
     * @param title title of the assignment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description of the assignment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the assignment
     * @param description description of the assignment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the due date of the assignment
     * @param date due date of the assignment
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * Sets the group of the assignment
     * @param group group of the assignment
     */
    public void setGroup(Group group){
        this.group = group;
    }
    
    public HashMap<String, Boolean> getCompletedStudents() {
        return completedStudents;
    }

    public void addCompletedStudents(User student){
        completedStudents.put(student.getId(), true);
    }

    public void setCompletedStudents(HashMap<String, Boolean> completedStudents) {
        this.completedStudents = completedStudents;
    }

    public int getCompletedStudentsSize(){
        return completedStudents.size();
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public static ArrayList<Assignment> createAssignmentList() {
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        assignments.add(new Assignment("Exeter page 58", "Questions 1-9"));
        assignments.add(new Assignment("PCMI", "Day 3 #1-30"));
        assignments.add(new Assignment("Polynomials", "#1-8"));
        return assignments;
    }

    public static ArrayList<Assignment> getMyList(){

        ArrayList<Assignment> assignmentsList = new ArrayList<>();
        Assignment m = new Assignment();
        m.setTitle("News Feed");
        m.setDescription("This is a newsfeed Description");
        assignmentsList.add(m);

        Assignment t = new Assignment();
        t.setTitle("ASSIGNMENT TITLE");
        t.setDescription("This is a description (Add Date here?)");
        assignmentsList.add(t);

        Assignment q = new Assignment();
        q.setTitle("Another Example Title");
        q.setDescription("This is another Description");
        assignmentsList.add(q);

        Assignment a = new Assignment();
        a.setTitle("Another Example Title");
        a.setDescription("This is another Description");
        assignmentsList.add(a);

        Assignment b = new Assignment();
        b.setTitle("Another Example Title");
        b.setDescription("This is another Description");
        assignmentsList.add(b);

        return assignmentsList;

    }
}
