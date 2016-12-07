package com.davida.model;

/**
 * Created by davida on 11/22/16.
 */
public class Admin {

    //private instance variable - so we create getters and setters
    private String name;
    private String password;

    //default constructor
    public Admin() {
    }

    //overloading the constructor
    public Admin(String name, String password) {
        setName(name);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return name;
    }

    public void setPassword(String name) {
        this.password = password;
    }


}
