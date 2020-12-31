package com.efrei.emergency.care.entities.resources;

import com.efrei.emergency.care.entities.resources.Resource;

import java.util.UUID;

public class Doctor extends Resource {
    public Doctor(String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String firstName;
    private String lastName;
}
