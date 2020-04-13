package com.equalpercentsteak.bandwagon;

public class Assignment {

    private String title, description, date, group;
    private int img;


    public String getDate() {
        return date;
    }
    public String getGroup(){
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

    public int getImg() {
        return img;
    }

    public void setImg(int imm) {
        this.img = imm;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setGroup(String group){
        this.group = group;
    }
}
