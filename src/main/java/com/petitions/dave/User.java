package com.petitions.dave;

public class User {
    private String name;
    private String email;

    // Default, no-argument constructor (required for instantiation)
    public User() {
    }

    // Constructor with parameters
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

}
