package com.equalpercentsteak.bandwagon;

public class Assignment {

    private String title, description, date;
    private Group group;

    public Assignment(){

    }

    public Assignment(String title, String description, String date, Group group){
        this.title= title;
        this.description = description;
        this.date = date;
        this.group = group;

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
}
