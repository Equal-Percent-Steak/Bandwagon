package com.equalpercentsteak.bandwagon;

import java.util.ArrayList;

public class Assignment {

    private String title;
    private String description;
    private String date;


    private String time;
    private Group group;
    private ArrayList<User> completedStudents;
    private int completedStudentsCount;
    private int totalStudentsCount;


    public Assignment(){
        title="Untitled";
        description="";
    }

    public Assignment(String title, String description, String date, Group group){
        this.title= title;
        this.description = description;
        this.date = date;
        completedStudents = new ArrayList<>();
        completedStudentsCount = 0;
    }

    public Assignment(String title, String description){
        this.title= title;
        this.description = description;
    }

    public void setTotalStudentsCount(int totalStudentsCount){
        this.totalStudentsCount = totalStudentsCount;
    }

    public String getDate() {
        return date;
    }

    public Group getGroup(){
        return group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setGroup(Group group){
        this.group = group;
    }

    public ArrayList<User> getCompletedStudents() {
        return completedStudents;
    }

    public void addCompletedStudents(User student){
        completedStudents.add(student);
    }

    public void setCompletedStudents(ArrayList<User> completedStudents) {
        this.completedStudents = completedStudents;
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
