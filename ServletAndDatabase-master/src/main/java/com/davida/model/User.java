package com.davida.model;

/**
 * Created by davida on 11/22/16.
 */
public class User {

    //private instance variable - so we create getters and setters
    private String name;
    private int id;
    private String noteIn;
    private String noteOut;
    private String checkedIn;
    private String checkedOut;

    //default constructor
    public User() {
    }

    //overloading the constructor
    public User(String name, int id, String noteIn, String noteOut, String checkedIn, String checkedOut) {
        this.name = name;
        this.id = id;
        this.noteIn = noteIn;
        this.noteOut = noteOut;
        this.checkedIn = checkedIn;
        this.checkedOut = checkedOut;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteIn() {
        return noteIn;
    }

    public void setNoteIn(String noteIn) {
        this.noteIn = noteIn;
    }

    public String getNoteOut() {
        return noteOut;
    }

    public void setNoteOut(String noteOut) {
        this.noteOut = noteOut;
    }

    public String getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(String checkedIn) {
        this.checkedIn = checkedIn;
    }

    public String getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(String checkedOut) {
        this.checkedOut = checkedOut;
    }
}
