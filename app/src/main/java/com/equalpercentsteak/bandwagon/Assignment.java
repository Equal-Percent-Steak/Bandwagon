package com.equalpercentsteak.bandwagon;

import java.util.ArrayList;
import java.util.HashMap;

public class Assignment {

    /**
     * The String title of the assignment
     */
    private String title;
    /**
     * The String description of the assignment
     */
    private String description;
    /**
     * The String due date of the assignment in the form yyyymmdd
     */
    private String date;
    /**
     * The String due time of the assignment in the form hhmm
     */
    private String time;
    /**
     * The Group that the assignment belongs to
     */
    private Group group;
    /**
     * The HashMap of users that records the students that have completed the assignment
     */
    private HashMap<String, Boolean> completedStudents;
    /**
     * The count of users that have completed an assignment
     */
    private long completedStudentsSize;


    /**
     * Constructs an Assignment object with an empty string for a title, description, date, time, and a new empty ArrayList of completed students
     */
    public Assignment(){
        this.title= "";
        this.description = "";
        this.date = "";
        this.time = "";
        completedStudents = new HashMap<>();
        completedStudentsSize = 0;
    }

    /**
     * Constructs an Assignment object with a given String title, description, date, and time, and a new ArrayList of completed students
     * @param title the String of the Assignment title
     * @param description the String of the description of the Assignment
     * @param date the String of the due date of the Assignment in the form yyyymmdd
     * @param time the String of the due time of the Assignment in the form hhmm
     */
    public Assignment(String title, String description, String date, String time, Group group){
        this.title= title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.group = group;
        completedStudents = new HashMap<>();
        completedStudentsSize = 0;
    }

    /**
     * Constructs an Assignment with a given String Title and String description, all other fields are empty
     * @param title the String title of the Assignment
     * @param description the String description of the Assignment
     */
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

    /**
     * Returns the ArrayList of users that have completed the Assignment
     * @return the ArrayList of users that have completed the Assignment
     */


    public HashMap<String, Boolean> getCompletedStudents() {
        return completedStudents;
    }

    /**
     * Adds a student to the HashMap of students that have completed the Assignment
     * @param student the Student that has completed the Assignment
     */
    public void addCompletedStudents(User student){
        completedStudents.put(student.getId(), true);
    }

    /**
     * Sets the ArrayList of completed Students
     * @param completedStudents the ArrayList of Users that have completed the Assignment
     */
    public void setCompletedStudents(HashMap<String, Boolean> completedStudents) {
        this.completedStudents = completedStudents;
    }

    /**
     * Gets the number of students that have completed the Assignment
     * @return the size of the HashMap of Users that have completed the Assignment
     */
    public int getCompletedStudentsSize(){
        return completedStudents.size();
    }

    /**
     * Gets the due time of the assignment
     * @return the due time of the assignment in the form hhmm
     */
    public String getTime(){
        return time;
    }

    /**
     * Sets the due time of the Assignment
     * @param time the new due time that the Assignment will have in the form hhmm
     */
    public void setTime(String time){
        this.time = time;
    }

    /**
     * Creates a sample ArrayList of Assignments with titles and descriptions
     * @return an ArrayList of Assignment objects
     */
    public static ArrayList<Assignment> createAssignmentList() {
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        assignments.add(new Assignment("Exeter page 58", "Questions 1-9"));
        assignments.add(new Assignment("PCMI", "Day 3 #1-30"));
        assignments.add(new Assignment("Polynomials", "#1-8"));
        return assignments;
    }

    /**
     * Creates a sample ArrayList of Assignments with titles and descriptions
     * @return the ArrayList of Assignment objects
     */
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
