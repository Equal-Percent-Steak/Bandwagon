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

    }

    public Assignment(String title, String description, String date, Group group){
        this.title= title;
        this.description = description;
        this.date = date;
        completedStudents = new ArrayList<>();
        completedStudentsCount = 0;
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
}
